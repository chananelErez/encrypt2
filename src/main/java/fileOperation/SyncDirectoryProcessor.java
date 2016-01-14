package fileOperation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import encryption.invalidEncryptionKeyException;
import keyBuilding.DoubleKey;
import keyBuilding.KeyType;
import keyBuilding.SimpleKey;

public class SyncDirectoryProcessor<E extends KeyType> extends FolderEncryption<E> 
implements ObservableEncryption, IDirectoryProcessor {

	public SyncDirectoryProcessor(EncryptionAlgorithm<E> algo) {
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
			logger.debug("Encryption of the file "+fileEntry+" starts.");
			String content;
			String originalFilePath=folderName+"\\"+fileEntry;
			String outputFilePath=folderName+"\\encrypted\\"+fileEntry;
			try {
				content = FileEncryptor.readFile(originalFilePath, StandardCharsets.UTF_8);
				this.notifyObserver(this.EncryptionStarted(originalFilePath));
				String cipher=Algo.Encrypt(content);
				FileEncryptor.writeFile(cipher,"encrypted" ,outputFilePath);
				this.notifyObserver(this.EncryptionEnded(originalFilePath));
			} catch (IOException e) {
				logger.error("The file "+fileEntry+" does not found.");
				System.out.println("Path to "+fileEntry+" not founded");
				this.notifyObserver(this.PathNotFound(originalFilePath, "Encryption"));
			}
			logger.debug("Encryption of the file "+fileEntry+" ends.");
		}
		this.notifyObserver(this.EncryptionFolderEnded(folderName));
		logger.debug("Encryption of folder ends.");
	}
	@Override
	public void decryptDirectory(String folderName,String KeyPath){
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
			logger.debug("Decryption of the file "+fileEntry+" starts.");
			String content;
			String encryptedFilePath=folderName+"\\"+fileEntry;
			String outputFilePath=folderName+"\\decrypted\\"+fileEntry;
			try {				
				content = FileEncryptor.readFile(encryptedFilePath, StandardCharsets.UTF_8);
				this.notifyObserver(this.DecryptionStarted(encryptedFilePath));
				String plain=Algo.Decrypt(content);
				FileEncryptor.writeFile(plain,"decrypted" ,outputFilePath);
				this.notifyObserver(this.DecryptionEnded(encryptedFilePath));
			} catch (IOException e) {
				logger.error("The file does not found.");
				System.out.println("Path not founded");
				this.notifyObserver(this.PathNotFound(encryptedFilePath, "Decryption"));
			}
			logger.debug("Decryption of the file "+fileEntry+" ends.");
		}
		logger.debug("Decryption of folder ends.");

		this.notifyObserver(this.DecryptionFolderEnded(folderName));
	}

	public void EncryptionMenu(SyncDirectoryProcessor<DoubleKey<SimpleKey>> Code) 
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
		
	}
	

}
