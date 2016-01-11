package fileOperation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import encryption.invalidEncryptionKeyException;
import keyBuilding.KeyType;
import keyBuilding.SimpleKey;
import logging.EncryptionLog4JLogger;
import observer.EncryptionObserver;

/*We want to encrypt entire folder so we have to know how to
get the names of the folder's files.
Then we know how to open them ,and we should take care of the writing to new folder. 
*/
public class FolderEncryption <E extends KeyType> implements ObservableEncryption{
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	private String folderName;
	
	
	private LinkedList<EncryptionObserver> list =
			                          new LinkedList<EncryptionObserver>();
	
	public EncryptionAlgorithm<E> Algo;
	
	public FolderEncryption(EncryptionAlgorithm<E> algo){
		Algo=algo;
	}
	
	public void encrtptFolder(String folderName) {
		logger.debug("Encryption of folder starts.");
		this.createNewFolder("\\Encrypted");
		this.notifyObserver(this.EncryptionFolderStarted(folderName));
		final File folder = new File(folderName);
		ArrayList<String> files=this.listFilesForFolder(folder);
		Algo.restartKet(folderName+"\\Encrypted");
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
	
	public void decryptFolder(String folderName){
		logger.debug("Decryption of folder starts.");
		this.createNewFolder("\\Decrypted");
		this.notifyObserver(this.DecryptionFolderStarted(folderName));
		final File folder = new File(folderName);
		ArrayList<String> files=this.listFilesForFolder(folder);
		try {
			Algo.getKey().setUserKey();
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

		
	}
	
	public void createNewFolder(String eORd){
		Boolean success;
		success = (new File(this.getFolderName()+eORd)).mkdirs();
		if (!success) {
		    logger.error("failed to build subdirectory");
		}
	}
	
	public static void main(String Args[]){
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		SimpleKey key1=new SimpleKey();
		algo.setKey(key1);
		FolderEncryption<SimpleKey> Code=new FolderEncryption<SimpleKey>(algo);
		Code.setFolderName("C:\\Users\\Public\\Documents\\openingexperiment\\Encrypted");
		Code.decryptFolder(Code.getFolderName());
	}
	
	public ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> files= new ArrayList<String>(); 
	    for (final File fileEntry : folder.listFiles()) {
	    	if(fileEntry.getName().toLowerCase().endsWith(".txt")){
	    		files.add(fileEntry.getName());
	    	}
	        
	    }
		return files;
	}
	
	@Override
	public void addEncryptionObserver(EncryptionObserver observer) {
		list.add(observer);
		
	}

	@Override
	public void removeEncryptionbserver(EncryptionObserver observer) {
		list.remove(observer);
		
	}

	@Override
	public void notifyObserver(GeneralEvent event) {
		for(EncryptionObserver observer : list)
        {
            observer.update(event);
        }
		
	}

	public EncryptionEvent EncryptionFolderStarted(String folder){
		String output=folder+"\\encrypted";
		EncryptionEvent eventEFS=new EncryptionEvent("Encryption",
				folder,Algo.toString(),output,
				System.currentTimeMillis());
		return eventEFS;
		
	}
	
	public EncryptionEvent EncryptionFolderEnded(String folder){
		EncryptionEvent eventEFE=new EncryptionEvent("Encryption",
				folder,null,null,
				System.currentTimeMillis());
		return eventEFE;
	}
	
	public EncryptionEvent DecryptionFolderStarted(String folder){
		String output=folder+"\\decrypted";
		EncryptionEvent eventEFS=new EncryptionEvent("Decryption",
				folder,Algo.toString(),output,
				System.currentTimeMillis());
		return eventEFS;
		
	}
	
	public EncryptionEvent DecryptionFolderEnded(String folder){
		EncryptionEvent eventEFE=new EncryptionEvent("Decryption",
				folder,null,null,
				System.currentTimeMillis());
		return eventEFE;
	}
	@Override
	public EncryptionEvent EncryptionStarted(String file) {
		String output=folderName+"\\encrypted"+file;
		EncryptionEvent eventES=new EncryptionEvent("Encryption",
				                    file,Algo.toString(),output,
				                    System.currentTimeMillis());
		return eventES;
	}

	@Override
	public EncryptionEvent DecryptionStarted(String file) {
		String output=folderName+"\\decrypted"+file;
		EncryptionEvent eventDS=new EncryptionEvent("Decryption",
				                    file,Algo.toString(),output,
				                    System.currentTimeMillis());
		return eventDS;
	}

	@Override
	public EncryptionEvent EncryptionEnded(String file) {
		EncryptionEvent eventEE=new EncryptionEvent("Encryption",
                file,null,null,
                System.currentTimeMillis());
		return eventEE;
	}

	@Override
	public EncryptionEvent DecryptionEnded(String file) {
		EncryptionEvent eventDE=new EncryptionEvent("Decryption",
                file,null,null,
                System.currentTimeMillis());
		return eventDE;
	}
	
	public LinkedList<EncryptionObserver> getList(){
		return list;
		
	}

	@Override
	public ErrorEvent PathNotFound(String file,String eORd) {
		ErrorEvent PNF=new ErrorEvent();
		PNF.setErrorKind("The Path was not found");
		PNF.setFile(file);
		PNF.seteORd(eORd);
		return PNF;
	}

	@Override
	public ErrorEvent InvalidKey(String file,String eORd) {
		ErrorEvent IK=new ErrorEvent();
		IK.setErrorKind("The Key was not valid");
		IK.setFile(file);
		IK.seteORd(eORd);
		return IK;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	

}
