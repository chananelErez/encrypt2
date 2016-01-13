package fileOperation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import encryption.EncryptionAlgorithm;
import keyBuilding.KeyType;
import listenersEvents.EncryptionEvent;
import listenersEvents.ErrorEvent;
import listenersEvents.GeneralEvent;
import logging.EncryptionLog4JLogger;
import observer.EncryptionObserver;
import synchronizedStaff.SynchronizedCounter;

public class AsyncEncryption<E extends KeyType>
implements ObservableEncryption, Runnable {
	
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	private String folderName;
	private String fileName;
	private SynchronizedCounter c;
	
	private ConcurrentLinkedQueue<EncryptionObserver> list =
			                          new ConcurrentLinkedQueue<EncryptionObserver>();
	private boolean EorD;
	public EncryptionAlgorithm<E> Algo;	
	
	public AsyncEncryption(SynchronizedCounter c,
			String filename, boolean eord,String fName
			,EncryptionAlgorithm<E> algo,
			ConcurrentLinkedQueue<EncryptionObserver> list){
		logger.debug("Thread was created.");
		this.c=c;
		this.fileName=filename;
		this.Algo=algo;
		this.EorD=eord;
		this.folderName=fName;
		this.list=list;
		logger.debug("Thread creation was over.");
		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(EorD){
			this.encryptFile();
		}else{
			this.decryptFile();
		}
		c.increment();
		logger.debug("The counter value is "+String.valueOf(c.value()));
		

	}
	
	public void encryptFile() {
		logger.debug("Encryption of the file "+fileName+" starts.");
		String content;
		String originalFilePath=folderName+"\\"+fileName;
		String outputFilePath=folderName+"\\encrypted\\"+fileName;
		try {
			content = FileEncryptor.readFile(originalFilePath, StandardCharsets.UTF_8);
			this.notifyObserver(this.EncryptionStarted(fileName));
			String cipher=Algo.Encrypt(content);
			FileEncryptor.writeFile(cipher,"encrypted" ,outputFilePath);
			this.notifyObserver(this.EncryptionEnded(fileName));
		} catch (IOException e) {
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			this.notifyObserver(this.PathNotFound(originalFilePath, "Encryption"));
		}
		logger.debug("Encryption of the file "+fileName+"  end.");
		
	}
	
	public void decryptFile(){
		logger.debug("Decryption of the file "+fileName+" start.");
		String content;
		String encryptedFilePath=folderName+"\\"+fileName;
		String outputFilePath=folderName+"\\decrypted\\"+fileName;
		try {
			content = FileEncryptor.readFile(encryptedFilePath, StandardCharsets.UTF_8);
			this.notifyObserver(this.DecryptionStarted(fileName));
			String plain=Algo.Decrypt(content);
			FileEncryptor.writeFile(plain,"decrypted" ,outputFilePath);
			this.notifyObserver(this.DecryptionEnded(fileName));
		} catch (IOException e) {
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			this.notifyObserver(this.PathNotFound(encryptedFilePath, "Decryption"));
		}
		logger.debug("Decryption of the file "+fileName+" end.");

		
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

}
