package fileOperation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import encryption.invalidEncryptionKeyException;
import fileOperation.FolderEncryption;
import fileOperation.IDirectoryProcessor;
import fileOperation.ObservableEncryption;
import keyBuilding.DoubleKey;
import keyBuilding.KeyType;
import keyBuilding.SimpleKey;
import synchronizedStaff.SynchronizedCounter;

/*The idea of doing things simultaneously maybe something like this:
 * -getting the names of which files we want to encrypt.
 * -Splitting the encryption to this number of threads .
 * -Summing it all up. */

public class AsyncDirectoryProcessor<E extends KeyType> extends FolderEncryption<E>
implements IDirectoryProcessor, ObservableEncryption {

	private SynchronizedCounter c=new SynchronizedCounter();
	
	public AsyncDirectoryProcessor(EncryptionAlgorithm<E> algo) {
		super(algo);

	}

	@Override
	public void encryptDirectory(String folderName) {
		logger.debug("Encryption of folder starts.");
		this.createNewFolder("\\Encrypted");
		this.notifyObserver(this.EncryptionFolderStarted(folderName));
		final File folder = new File(folderName);
		ArrayList<String> files=this.listFilesForFolder(folder);
		Algo.restartKey(folderName+"\\Encrypted");
		for (final String fileEntry : files){
			Thread t = new Thread(new AsyncEncryption<E>
			(c, fileEntry, true, this.getFolderName(), Algo, this.getList()));
			t.start();
		}
		logger.debug("The counter should get to the value "+String.valueOf(files.size()));
		while(c.value()<files.size()){
			
		}
		this.notifyObserver(this.EncryptionFolderEnded(folderName));
		logger.debug("Encryption of folder ends.");
		
	}

	@Override
	public void decryptDirectory(String folderName,String KeyPath) {
		logger.debug("Decryption of folder starts.");
		this.createNewFolder("\\Decrypted");
		this.notifyObserver(this.DecryptionFolderStarted(folderName));
		final File folder = new File(folderName);
		ArrayList<String> files=this.listFilesForFolder(folder);
		try {
			if(KeyPath==null){
			Algo.getKey().setUserKey();
			}
			else{
				Algo.getKey().getKeyFromFile(KeyPath);
			}
		} catch (invalidEncryptionKeyException e1) {
			logger.error("The inserted key was wrong.");
			this.notifyObserver(this.InvalidKey(folderName, "Decryption"));
			System.out.println("Invalid key type (it is not a number"
					+ "or it is negative");
		}
		Algo.setKey(Algo.getKey());
		for (final String fileEntry : files){
			Thread t = new Thread(new AsyncEncryption<E>
			(c, fileEntry, false, this.getFolderName(), Algo, this.getList()));
			t.start();
		}
		while(c.value()<files.size()){
			
		}
		this.notifyObserver(this.DecryptionFolderEnded(folderName));
		logger.debug("Decryption of folder ends.");

		
	}
	public static AsyncDirectoryProcessor<DoubleKey<SimpleKey>> buildOne(){
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		DoubleEncryption<SimpleKey> internalAlgo=
				                    new DoubleEncryption<SimpleKey>(algo);
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		internalAlgo.setKey(k);
		AsyncDirectoryProcessor<DoubleKey<SimpleKey>> Code=
				new AsyncDirectoryProcessor<DoubleKey<SimpleKey>>(internalAlgo);
		return Code;
		
	}

	public void EncryptionMenu(AsyncDirectoryProcessor<DoubleKey<SimpleKey>> Code) 
			throws IOException{
		
		logger.debug("Opening menu.");
		Scanner user_input=new Scanner(System.in);
		System.out.println("It is encryption algorithm please insert E for "
				+ "encryption and D for decryption ");
		String eORd=user_input.next();
		
		System.out.println("please insert the folder source path ");
		String fileName=user_input.next();
		Code.setFolderName(fileName);
		if (eORd.charAt(0)=='E'){
			Code.encryptDirectory(fileName);
		}
		else if (eORd.charAt(0)=='D'){
			
			Code.decryptDirectory(fileName,null);
		}else{
			logger.error("Wrong type of operation was chosen.");
		}
		logger.debug("Closing menu.");
		user_input.close();
	}

}
