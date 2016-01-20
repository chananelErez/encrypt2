package fileOperation;

import java.io.File;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import encryption.EncryptionAlgorithm;
import keyBuilding.KeyType;
import logging.EncryptionLog4JLogger;
import writingFormat.Directoryformat;

/*We want to encrypt entire folder so we have to know how to
get the names of the folder's files.
Then we know how to open them ,and we should take care of the writing to new folder. 
*/
public class FolderEncryption <E extends KeyType> {
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	private String folderName;
	
	public EncryptionAlgorithm<E> Algo;
	
	public FolderEncryption(EncryptionAlgorithm<E> algo){
		Algo=algo;
	}
	
	public void createNewFolder(Directoryformat folderName){
		Boolean success;
		String w=new String();
		if(folderName.getEord().equals("Encryption")){
			w="\\Encrypted";
		}else if(folderName.getEord().equals("Decryption")){
			w="\\Decrypted";
		}
		success = (new File(this.getFolderName()+w)).mkdirs();
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

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	

}
