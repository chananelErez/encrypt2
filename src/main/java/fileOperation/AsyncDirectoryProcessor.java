package fileOperation;

import java.io.File;
import java.util.ArrayList;
import encryption.EncryptionAlgorithm;
import encryption.invalidEncryptionKeyException;
import fileOperation.FolderEncryption;
import fileOperation.IDirectoryProcessor;
import fileOperation.ObservableEncryption;
import keyBuilding.KeyType;
import synchronizedStaff.SynchronizedCounter;

/*The idea of doing things simultaneously maybe something like this:
 * -getting the names of which files we want to encrypt.
 * -Splitting the encryption to this number of threads .
 * -Summing it all up. */

public class AsyncDirectoryProcessor<E extends KeyType> extends FolderEncryption<E>
implements IDirectoryProcessor, ObservableEncryption {

	private SynchronizedCounter c;
	
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
		while(c.value()<this.getList().size()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyObserver(this.EncryptionFolderEnded(folderName));
		logger.debug("Encryption of folder ends.");
		
	}

	@Override
	public void decryptDirectory(String folderName) {
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
			Thread t = new Thread(new AsyncEncryption<E>
			(c, fileEntry, false, this.getFolderName(), Algo, this.getList()));
			t.start();
		}
		while(c.value()<this.getList().size()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyObserver(this.DecryptionFolderEnded(folderName));
		logger.debug("Decryption of folder ends.");

		
	}


}
