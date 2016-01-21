package fileOperation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import adService.FilePublisher;
import encryption.EncryptionAlgorithm;
import keyBuilding.KeyType;
import logging.EncryptionLog4JLogger;
import observer.EncryptionObserver;
import synchronizedStaff.SynchronizedCounter;
import writingFormat.IntFileformat;

public class AsyncEncryption<E extends KeyType>
implements  Runnable {
	
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	private FilePublisher pub ;

	private IntFileformat fileName;
	private SynchronizedCounter c;
	
	private ConcurrentLinkedQueue<EncryptionObserver> list =
			                          new ConcurrentLinkedQueue<EncryptionObserver>();

	public EncryptionAlgorithm<E> Algo;	
	
	public AsyncEncryption(SynchronizedCounter c,
			IntFileformat form
			,EncryptionAlgorithm<E> algo,
			FilePublisher pub){
		logger.debug("Thread was created.");
		this.c=c;
		this.fileName=form;
		this.Algo=algo;
		this.pub=pub;
		logger.debug("Thread creation was over.");
		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(this.fileName.equals("Encryption")){
			this.encryptFile();
		}else if(this.fileName.equals("Decryption")){
			this.decryptFile();
		}
		c.increment();
		logger.debug("The counter value is "+String.valueOf(c.value()));
		

	}
	
	public void encryptFile() {
		logger.debug("Encryption of the file "+fileName.getFileName()+" starts.");
		
		try {
			String content = Analphabet.readFile(this.fileName.getInput(),
					StandardCharsets.UTF_8);
			pub.notifyObserver(pub.EncryptionStarted(fileName));
			String cipher=Algo.Encrypt(content);
			Analphabet.writeFile(cipher,"encrypted" ,this.fileName.getOutput());
			pub.notifyObserver(pub.EncryptionEnded(fileName));
		} catch (IOException e) {
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			pub.notifyObserver(pub.PathNotFound(this.fileName));
		}
		logger.debug("Encryption of the file "+fileName.getFileName()+"  end.");
		
	}
	
	public void decryptFile(){
		logger.debug("Decryption of the file "+fileName.getFileName()+" start.");
		
		try {
			String content = Analphabet.readFile(this.fileName.getInput()
					, StandardCharsets.UTF_8);
			pub.notifyObserver(pub.DecryptionStarted(fileName));
			String plain=Algo.Decrypt(content);
			Analphabet.writeFile(plain,"decrypted" ,this.fileName.getOutput());
			pub.notifyObserver(pub.DecryptionEnded(fileName));
		} catch (IOException e) {
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			pub.notifyObserver(pub.PathNotFound(this.fileName));
		}
		logger.debug("Decryption of the file "+fileName.getFileName()+" end.");

		
	}
	
	public ConcurrentLinkedQueue<EncryptionObserver> getList(){
		return list;
		
	}

	public FilePublisher getPub() {
		return pub;
	}

	public void setPub(FilePublisher pub) {
		this.pub = pub;
	}

}
