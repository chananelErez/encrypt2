package fileOperation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;

import adService.FilePublisher;
import encryption.EncryptionAlgorithm;
import encryption.invalidEncryptionKeyException;
import keyBuilding.KeyType;
import logging.EncryptionLog4JLogger;
import writingFormat.Fileformat;

public class FileEncryptor<E extends KeyType> {
	
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	
	private FilePublisher pub =new FilePublisher();
	
	EncryptionAlgorithm<E> Algo;
	
	public FileEncryptor(EncryptionAlgorithm<E> algo){
		Algo=algo;
	}
	
	public void encrtptFile(Fileformat file) {
		logger.debug("Encryption method start.");
		
		try {
			String content = Analphabet.readFile(file.getInput(), StandardCharsets.UTF_8);
			pub.notifyObserver(pub.EncryptionStarted(file));
			Algo.restartKey(file.getKeyPath());
			String cipher=Algo.Encrypt(content);
			Analphabet.writeFile(cipher,"encrypted" ,file.getOutput());
			pub.notifyObserver(pub.EncryptionEnded(file));
		} catch (IOException e) {
			
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			pub.notifyObserver(pub.PathNotFound(file));
		}
		logger.debug("Encryption method end.");
		
	}
	
	public void decryptFile(Fileformat file){
		logger.debug("Decryption method start.");

		try {
			String content = Analphabet.readFile(file.getInput(), StandardCharsets.UTF_8);
			pub.notifyObserver(pub.DecryptionStarted(file));

			Algo.getKey().getKeyFromFile(file.getKeyPath());
			Algo.setKey(Algo.getKey());


			String plain=Algo.Decrypt(content);
			Analphabet.writeFile(plain,"decrypted" ,file.getOutput());
			pub.notifyObserver(pub.DecryptionEnded(file));
		} catch (IOException e) {
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			pub.notifyObserver(pub.PathNotFound(file));
		} catch (invalidEncryptionKeyException e) {
			logger.error("The inserted key was wrong.");
			pub.notifyObserver(pub.InvalidKey(file));
			System.out.println("Invalid key type (it is not a number"
					+ "or it is negative");
		}
		logger.debug("Decryption method end.");

		
	}
	

/*	
	public void EncryptionMenu(FileEncryptor<DoubleKey<SimpleKey>> Code) 
			throws IOException{
		BuildEncryptor Bu=new BuildEncryptor();
		Bu.setFileORDirec("File");
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
		
		System.out.println("please insert the file source path ");
		String fileName=user_input.next();
		Bu.setFileName(fileName.substring(fileName.
				lastIndexOf('\\'),fileName.length()));
		Bu.setSourceDirectory(fileName.substring(0, fileName.
				lastIndexOf('\\')));
		String outputPath=Code.NameConvert(fileName, eORd);
		if (eORd.charAt(0)=='E'){
			Bu.setEDOperation("Encryption");
			WriteXml.WriteXmlWithJaxb(Bu, 
					"C:\\Users\\Public\\Documents"
					+ "\\openingexperiment\\justfolder\\gamma.xml");
			String keypath="C:\\Users\\Public\\Documents\\openingexperiment";
			Code.encrtptFile(fileName, outputPath,keypath);
		}
		else if (eORd.charAt(0)=='D'){
			Bu.setEDOperation("Decryption");
			WriteXml.WriteXmlWithJaxb(Bu, 
					"C:\\Users\\Public\\Documents"
					+ "\\openingexperiment\\justfolder\\gamma.xml");
			Code.decryptFile(fileName, outputPath,null);
		}else{
			logger.error("Wrong type of operation was chosen.");
		}
		logger.debug("Closing menu.");
		user_input.close();
	}
	

	public static FileEncryptor<DoubleKey<SimpleKey>> buildOne(){
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		DoubleEncryption<SimpleKey> internalAlgo=
				                    new DoubleEncryption<SimpleKey>(algo);
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		internalAlgo.setKey(k);
		FileEncryptor<DoubleKey<SimpleKey>> Code=
				new FileEncryptor<DoubleKey<SimpleKey>>(internalAlgo);
		return Code;
		
	}*/

	public FilePublisher getPub() {
		return pub;
	}

	public void setPub(FilePublisher pub) {
		this.pub = pub;
	}


	
}

