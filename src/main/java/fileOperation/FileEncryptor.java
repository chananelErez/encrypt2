package fileOperation;

import encryption.ShiftUpEncryption;
import encryption.EncryptionAlgorithm;
import encryption.DoubleEncryption;
import encryption.invalidEncryptionKeyException;
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

public class FileEncryptor implements ObservableEncryption{
	
	private LinkedList<EncryptionObserver> list =
			                          new LinkedList<EncryptionObserver>();
	
	EncryptionAlgorithm Algo=new ShiftUpEncryption();
	
	public FileEncryptor(EncryptionAlgorithm algo){
		Algo=algo;
	}
	
	public void encrtptFile(String originalFilePath,String outputFilePath) {
		String content;
		try {
			content = readFile(originalFilePath, StandardCharsets.UTF_8);
			Algo.generateKey();
			Algo.printKeyToFile();
			String cipher=Algo.Encrypt(content);
			writeFile(cipher,"encrypted" ,outputFilePath);
		} catch (IOException e) {
			System.out.println("Path not founded");
		}
		
	}
	
	public void decryptFile(String encryptedFilePath,String outputFilePath)
			{
		String content;
		try {
			content = readFile(encryptedFilePath, StandardCharsets.UTF_8);
			Algo.setUserKey();
			String plain=Algo.Decrypt(content);
			writeFile(plain,"decrypted" ,outputFilePath);
		} catch (IOException e) {
			
			System.out.println("Path not founded");
		} catch (invalidEncryptionKeyException e) {
			
			System.out.println("Invalid key type (it is not a number"
					+ "or it is negative");
		}
		
		
	}
	
	public String NameConvert(String fileName,String eORd){
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
	}
	
	public static void main(String[] args) throws IOException {
		
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		EncryptionAlgorithm internalAlgo=new DoubleEncryption(algo);
		FileEncryptor Code= new FileEncryptor(internalAlgo);
		
		Scanner user_input=new Scanner(System.in);
		System.out.println("It is encryption algorithm please insert E for "
				+ "encryption and D for decryption ");
		String eORd=user_input.next();
		
		System.out.println("please insert the file source path ");
		String fileName=user_input.next();
		String outputPath=Code.NameConvert(fileName, eORd);
		if (eORd.charAt(0)=='E'){
			Code.notifyObserver(Code.EncryptionStarted(fileName));
			Code.encrtptFile(fileName, outputPath);
			Code.notifyObserver(Code.EncryptionEnded(fileName));
		}
		if (eORd.charAt(0)=='D'){
			Code.notifyObserver(Code.DecryptionStarted(fileName));
			Code.decryptFile(fileName, outputPath);
			Code.notifyObserver(Code.DecryptionEnded(fileName));
		}
		user_input.close();
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
	public void notifyObserver(EncryptionEvent event) {
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
}

