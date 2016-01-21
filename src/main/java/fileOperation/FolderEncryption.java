package fileOperation;

import java.io.File;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import algoBuilder.BuildEncryptor;
import encryption.EncryptionAlgorithm;
import keyBuilding.KeyType;
import logging.EncryptionLog4JLogger;
import writingFormat.Directoryformat;
import writingFormat.IntFileformat;

/*We want to encrypt entire folder so we have to know how to
get the names of the folder's files.
Then we know how to open them ,and we should take care of the writing to new folder. 
*/
public class FolderEncryption <E extends KeyType>  {
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
			w=folderName.getInput()+"\\Encrypted";
		}else if(folderName.getEord().equals("Decryption")){
			w=folderName.getInput()+"\\Decrypted";
		}
		success = (new File(w)).mkdirs();
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
	
	
	public ArrayList<IntFileformat> CreateFileFormat(Directoryformat folderName) {
		this.createNewFolder(folderName);
		final File folder = new File(folderName.getInput());
		ArrayList<String> files=this.listFilesForFolder(folder);
		ArrayList<IntFileformat> formats=new ArrayList<IntFileformat>();
		
		for(final String file:files){
			BuildEncryptor b=new BuildEncryptor();
			b.setAlgorithm(folderName.getAlgoName());
			b.setEDOperation(folderName.getEord());
			b.setFileName(file);
			b.setSourceDirectory(folderName.getInput());
			b.setIsDouble(true);
			formats.add(new IntFileformat(b));
			
		}
		
		
		return formats;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	

	

}
