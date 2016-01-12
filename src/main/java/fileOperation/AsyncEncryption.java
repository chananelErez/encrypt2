package fileOperation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

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
	
	private LinkedList<EncryptionObserver> list =
			                          new LinkedList<EncryptionObserver>();
	private boolean EorD;
	public EncryptionAlgorithm<E> Algo;	
	
	public AsyncEncryption(SynchronizedCounter c,
			String filename, boolean eord,String fName
			,EncryptionAlgorithm<E> algo,
			LinkedList<EncryptionObserver> list){
		this.c=c;
		this.fileName=filename;
		this.Algo=algo;
		this.EorD=eord;
		this.folderName=fName;
		this.list=list;
		for(EncryptionObserver ob:list){
			this.addEncryptionObserver(ob);
		}
		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(EorD){
			this.encryptFile();
		}else{
			this.decryptFile();
		}
		c.increment();
		 notifyAll();

	}
	
	public void encryptFile() {
		logger.debug("Encryption of the file "+fileName+" starts.");
		String content;
		String originalFilePath=folderName+"\\"+fileName;
		String outputFilePath=folderName+"\\encrypted\\"+fileName;
		try {
			content = FileEncryptor.readFile(originalFilePath, StandardCharsets.UTF_8);
			this.notifyObserver(this.EncryptionStarted(originalFilePath));
			String cipher=Algo.Encrypt(content);
			FileEncryptor.writeFile(cipher,"encrypted" ,outputFilePath);
			this.notifyObserver(this.EncryptionEnded(originalFilePath));
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
			this.notifyObserver(this.DecryptionStarted(encryptedFilePath));
			String plain=Algo.Decrypt(content);
			FileEncryptor.writeFile(plain,"decrypted" ,outputFilePath);
			this.notifyObserver(this.DecryptionEnded(encryptedFilePath));
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

}
