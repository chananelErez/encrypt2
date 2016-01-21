package fileOperation;

import java.util.ArrayList;
import adService.DirectoryPublisher;
import encryption.EncryptionAlgorithm;
import encryption.invalidEncryptionKeyException;
import keyBuilding.KeyType;
import listenersEvents.GeneralEvent;
import observer.EncryptionObserver;
import synchronizedStaff.SynchronizedCounter;
import writingFormat.Directoryformat;
import writingFormat.IntFileformat;

/*The idea of doing things simultaneously maybe something like this:
 * -getting the names of which files we want to encrypt.
 * -Splitting the encryption to this number of threads .
 * -Summing it all up. */

public class AsyncDirectoryProcessor<E extends KeyType> extends FolderEncryption<E>
implements IDirectoryProcessor,ObservableEncryption {

	private DirectoryPublisher pub =new DirectoryPublisher();
	private SynchronizedCounter c=new SynchronizedCounter();
	
	public AsyncDirectoryProcessor(EncryptionAlgorithm<E> algo) {
		super(algo);

	}

	@Override
	public void encryptDirectory(Directoryformat folderName) {
		logger.debug("Encryption of folder starts.");
		
		ArrayList<IntFileformat> formats=this.CreateFileFormat(folderName);
		pub.notifyObserver(pub.EncryptionFolderStarted(folderName));
		
		Algo.restartKey(folderName.getKeyPath());
		
		
		for (final IntFileformat fileEntry : formats){
			Thread t = new Thread(new AsyncEncryption<E>
			(c, fileEntry, Algo, pub.getIntP()));
			t.start();
		}
		logger.debug("The counter should get to the value "+String.valueOf(formats.size()));
		while(c.value()<formats.size()){
			
		}
		pub.notifyObserver(pub.EncryptionFolderEnded(folderName));
		logger.debug("Encryption of folder ends.");
		
	}

	@Override
	public void decryptDirectory(Directoryformat folderName) {
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
			Thread t = new Thread(new AsyncEncryption<E>
			(c, fileEntry, Algo, pub.getIntP()));
			t.start();
		}
		while(c.value()<files.size()){
			
		}
		pub.notifyObserver(pub.DecryptionFolderEnded(folderName));
		logger.debug("Decryption of folder ends.");

		
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
	
/*	
	public void EncryptionMenu(AsyncDirectoryProcessor<DoubleKey<SimpleKey>> Code) 
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
					+ "\\openingexperiment\\justfolder\\zetha.xml");
			Code.encryptDirectory(fileName);
		}
		else if (eORd.charAt(0)=='D'){
			Bu.setEDOperation("Decryption");
			WriteXml.WriteXmlWithJaxb(Bu, 
					"C:\\Users\\Public\\Documents"
					+ "\\openingexperiment\\justfolder\\omega.xml");
			Code.decryptDirectory(fileName,null);
		}else{
			logger.error("Wrong type of operation was chosen.");
		}
		logger.debug("Closing menu.");
		user_input.close();
	}*/


}
