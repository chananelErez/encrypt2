package fileOperation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import adService.DirectoryPublisher;
import encryption.EncryptionAlgorithm;
import encryption.invalidEncryptionKeyException;
import keyBuilding.KeyType;
import listenersEvents.GeneralEvent;
import logging.EncryptionLog4JLogger;
import observer.EncryptionObserver;
import writingFormat.Directoryformat;
import writingFormat.IntFileformat;

public class SyncDirectoryProcessor<E extends KeyType> extends FolderEncryption<E> 
implements IDirectoryProcessor,ObservableEncryption {
	
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	
	private DirectoryPublisher pub =new DirectoryPublisher();

	public SyncDirectoryProcessor(EncryptionAlgorithm<E> algo) {
		super(algo);
	}
	
	@Override
	public void encryptDirectory(Directoryformat folderName) {
		logger.debug("Encryption of folder starts.");
		
		pub.notifyObserver(pub.EncryptionFolderStarted(folderName));
		ArrayList<IntFileformat> formats=this.CreateFileFormat(folderName);
		Algo.restartKey(folderName.getKeyPath());
		for (final IntFileformat fileEntry : formats){
			logger.debug("Encryption of the file "+fileEntry.getInput()+" starts.");
			try {
				String content = Analphabet.readFile(fileEntry.getInput(), StandardCharsets.UTF_8);
				pub.getIntP().notifyObserver(
						pub.getIntP().EncryptionStarted(fileEntry));
				
				String cipher=Algo.Encrypt(content);
				Analphabet.writeFile(cipher,"encrypted" ,fileEntry.getOutput());
				
				pub.getIntP().notifyObserver(
						pub.getIntP().EncryptionEnded(fileEntry));
				
			} catch (IOException e) {
				logger.error("The file "+fileEntry.getInput()+" does not found.");
				System.out.println("Path to "+fileEntry.getInput()+" not founded");
				pub.getIntP().notifyObserver(
						pub.getIntP().PathNotFound(fileEntry));
			}
			logger.debug("Encryption of the file "+fileEntry.getInput()+" ends.");
		}
		pub.notifyObserver(pub.EncryptionFolderEnded(folderName));
		logger.debug("Encryption of folder ends.");
	}
	


	@Override
	public void decryptDirectory(Directoryformat folderName){
		logger.debug("Decryption of folder starts.");
		
		pub.notifyObserver(pub.DecryptionFolderStarted(folderName));
		
		ArrayList<IntFileformat> files=this.CreateFileFormat(folderName);
		try {
			Algo.getKey().getKeyFromFile(folderName.getKeyPath());
		} catch (invalidEncryptionKeyException e1) {
			logger.error("The inserted key was wrong.");
			pub.notifyObserver(pub.InvalidKey(folderName));
			System.out.println("Invalid key type (it is not a number"
					+ "or it is negative");
		}
		
		Algo.setKey(Algo.getKey());
		
		for (final IntFileformat fileEntry : files){
			logger.debug("Decryption of the file "+fileEntry.getInput()+" starts.");
			String content;
			try {				
				content = Analphabet.readFile(fileEntry.getInput(), StandardCharsets.UTF_8);
				pub.getIntP().notifyObserver(
						pub.getIntP().DecryptionStarted(fileEntry));
				String plain=Algo.Decrypt(content);
				Analphabet.writeFile(plain,"decrypted" ,fileEntry.getOutput());
				pub.getIntP().notifyObserver(
						pub.getIntP().DecryptionEnded(fileEntry));
			} catch (IOException e) {
				logger.error("The file does not found.");
				System.out.println("Path not founded");
				pub.getIntP().notifyObserver(
						pub.getIntP().PathNotFound(fileEntry));
			}
			logger.debug("Decryption of the file "+fileEntry.getInput()+" ends.");
		}
		logger.debug("Decryption of folder ends.");

		pub.notifyObserver(pub.DecryptionFolderEnded(folderName));
	}
	
	@Override
	public void addEncryptionObserver(EncryptionObserver observer) {
		pub.addEncryptionObserver(observer);
		
	}

	@Override
	public void removeEncryptionbserver(EncryptionObserver observer) {
		pub.removeEncryptionbserver(observer);
		
	}

	@Override
	public void notifyObserver(GeneralEvent event) {
		pub.notifyObserver(event);
		
	}

/*	public void EncryptionMenu(SyncDirectoryProcessor<DoubleKey<SimpleKey>> Code) 
			throws IOException{
		BuildEncryptor Bu=new BuildEncryptor();
		Bu.setFileORDirec("Directory");
		Bu.setAlgorithm("ShiftUpEncryption");
		Bu.setKeyType("DoubleKey<SimpleKey>");
		Bu.setIsDouble(true);
		Bu.setRepeat(0);
		Bu.setKeyPath("C:\\Users\\Public\\Documents\\openingexperiment");
		
		logger.debug("Opening menu.");
		Scanner user_input=new Scanner(System.in);
		System.out.println("It is encryption algorithm please insert E for "
				+ "encryption and D for decryption ");
		String eORd=user_input.next();
		
		System.out.println("please insert the folder source path ");
		String fileName=user_input.next();
		Bu.setFileName(null);
		Bu.setSourceDirectory(fileName);
		Code.setFolderName(fileName);
		if (eORd.charAt(0)=='E'){
			Bu.setEDOperation("Encryption");
			WriteXml.WriteXmlWithJaxb(Bu, 
					"C:\\Users\\Public\\Documents"
					+ "\\openingexperiment\\justfolder\\omikoron.xml");
			Code.encryptDirectory(fileName);
		}
		else if (eORd.charAt(0)=='D'){
			Bu.setEDOperation("Decryption");
			WriteXml.WriteXmlWithJaxb(Bu, 
					"C:\\Users\\Public\\Documents"
					+ "\\openingexperiment\\justfolder\\alpha.xml");
			Code.decryptDirectory(fileName,null);
		}else{
			logger.error("Wrong type of operation was chosen.");
		}
		logger.debug("Closing menu.");
		user_input.close();
	}
	
	public static SyncDirectoryProcessor<DoubleKey<SimpleKey>> buildOne(){
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		DoubleEncryption<SimpleKey> internalAlgo=
				                    new DoubleEncryption<SimpleKey>(algo);
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		internalAlgo.setKey(k);
		SyncDirectoryProcessor<DoubleKey<SimpleKey>> Code=
				new SyncDirectoryProcessor<DoubleKey<SimpleKey>>(internalAlgo);
		return Code;
		
	}*/
	public DirectoryPublisher getPub() {
		return pub;
	}
	public void setPub(DirectoryPublisher pub) {
		this.pub = pub;
	}
	

}
