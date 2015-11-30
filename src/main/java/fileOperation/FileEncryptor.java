package fileOperation;

import encryption.ShiftUpEncryption;
import encryption.EncryptionAlgorithm;
import encryption.DoubleEncryption;
import encryption.invalidEncryptionKeyException;
import keyBuilding.DoubleKey;
import keyBuilding.KeyType;
import keyBuilding.SimpleKey;
import logging.EncryptionLog4JLogger;
import observer.EncryptionObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class FileEncryptor<E extends KeyType> implements ObservableEncryption{
	
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	
	private LinkedList<EncryptionObserver> list =
			                          new LinkedList<EncryptionObserver>();
	
	EncryptionAlgorithm<E> Algo;
	
	public FileEncryptor(EncryptionAlgorithm<E> algo){
		Algo=algo;
	}
	
	public void encrtptFile(String originalFilePath,String outputFilePath) {
		logger.debug("Encryption method start.");
		String content;
		try {
			content = readFile(originalFilePath, StandardCharsets.UTF_8);
			this.notifyObserver(this.EncryptionStarted(originalFilePath));
			Algo.restartKet();
			String cipher=Algo.Encrypt(content);
			writeFile(cipher,"encrypted" ,outputFilePath);
			this.notifyObserver(this.EncryptionEnded(originalFilePath));
		} catch (IOException e) {
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			this.notifyObserver(this.PathNotFound(originalFilePath, "Encryption"));
		}
		logger.debug("Encryption method end.");
		
	}
	
	public void decryptFile(String encryptedFilePath,String outputFilePath){
		logger.debug("Decryption method start.");
		String content;
		try {
			content = readFile(encryptedFilePath, StandardCharsets.UTF_8);
			this.notifyObserver(this.DecryptionStarted(encryptedFilePath));
			Algo.getKey().setUserKey();
			Algo.setKey(Algo.getKey());
			String plain=Algo.Decrypt(content);
			writeFile(plain,"decrypted" ,outputFilePath);
			this.notifyObserver(this.DecryptionEnded(encryptedFilePath));
		} catch (IOException e) {
			logger.error("The file does not found.");
			System.out.println("Path not founded");
			this.notifyObserver(this.PathNotFound(encryptedFilePath, "Decryption"));
		} catch (invalidEncryptionKeyException e) {
			logger.error("The inserted key was wrong.");
			this.notifyObserver(this.InvalidKey(encryptedFilePath, "Decryption"));
			System.out.println("Invalid key type (it is not a number"
					+ "or it is negative");
		}
		logger.debug("Decryption method end.");

		
	}
	
	public String NameConvert(String fileName,String eORd){
		logger.debug("Converting the file name to the output file.");
		String convertName="";
		for(int i=0;i<fileName.length();i++){
			if (fileName.charAt(i)=='.'){
				if(eORd.charAt(0)=='E'){
					convertName+="_encrypted.";
				}
				if(eORd.charAt(0)=='D'){
					convertName+="_decrypted.";
				}
				
			}
			else{
				convertName+=String.valueOf(fileName.charAt(i));
			}
			
		} 
		logger.debug("The convert process finished.");
		return convertName;
		
	}
	// from http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	// from http://www.mkyong.com/java/how-to-write-to-file-in-java-fileoutputstream-example/
	public static void writeFile(String content,String name,String path) {
		logger.debug("Writing content to file.");
		FileOutputStream fop = null;
		File file;
		try {
			
			file = new File(path);
			fop = new FileOutputStream(file);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println("The "+name+" file "+"created in "+path);

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.debug("The writing process finished.");
	}
	
	public void EncryptionMenu(FileEncryptor<DoubleKey<SimpleKey>> Code) 
			throws IOException{
		
		logger.debug("Opening menu.");
		Scanner user_input=new Scanner(System.in);
		System.out.println("It is encryption algorithm please insert E for "
				+ "encryption and D for decryption ");
		String eORd=user_input.next();
		
		System.out.println("please insert the file source path ");
		String fileName=user_input.next();
		String outputPath=Code.NameConvert(fileName, eORd);
		if (eORd.charAt(0)=='E'){
			Code.encrtptFile(fileName, outputPath);
		}
		else if (eORd.charAt(0)=='D'){
			
			Code.decryptFile(fileName, outputPath);
		}else{
			logger.error("Wrong type of operation was chosen.");
		}
		logger.debug("Closing menu.");
		user_input.close();
	}
	
	public static FileEncryptor<DoubleKey<SimpleKey>> buildOne(){
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		DoubleEncryption<SimpleKey> internalAlgo=
				                    new DoubleEncryption<SimpleKey>(algo);
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		internalAlgo.setKey(k);
		FileEncryptor<DoubleKey<SimpleKey>> Code=
				new FileEncryptor<DoubleKey<SimpleKey>>(internalAlgo);
		return Code;
		
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
		String output=NameConvert(file, "E");
		EncryptionEvent eventES=new EncryptionEvent("Encryption",
				                    file,Algo.toString(),output,
				                    System.currentTimeMillis());
		return eventES;
	}

	@Override
	public EncryptionEvent DecryptionStarted(String file) {
		String output=NameConvert(file, "D");
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
		PNF.setErrorkind("The Path was not found");
		PNF.setFile(file);
		PNF.seteORd(eORd);
		return PNF;
	}

	@Override
	public ErrorEvent InvalidKey(String file,String eORd) {
		ErrorEvent IK=new ErrorEvent();
		IK.setErrorkind("The Key was not valid");
		IK.setFile(file);
		IK.seteORd(eORd);
		return IK;
	}
	
}

