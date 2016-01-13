package fileOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import encryption.EncryptionAlgorithm;
import keyBuilding.KeyType;
import listenersEvents.EncryptionEvent;
import listenersEvents.ErrorEvent;
import listenersEvents.GeneralEvent;
import logging.EncryptionLog4JLogger;
import observer.EncryptionObserver;

/*We want to encrypt entire folder so we have to know how to
get the names of the folder's files.
Then we know how to open them ,and we should take care of the writing to new folder. 
*/
public class FolderEncryption <E extends KeyType> implements ObservableEncryption{
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	private String folderName;
	
	
	private ConcurrentLinkedQueue<EncryptionObserver> list =
			                          new ConcurrentLinkedQueue<EncryptionObserver>();
	
	public EncryptionAlgorithm<E> Algo;
	
	public FolderEncryption(EncryptionAlgorithm<E> algo){
		Algo=algo;
	}
	
	public void createNewFolder(String eORd){
		Boolean success;
		success = (new File(this.getFolderName()+eORd)).mkdirs();
		if (!success) {
		    logger.error("failed to build subdirectory");
		}
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
	
	public ConcurrentLinkedQueue<EncryptionObserver> getList(){
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
