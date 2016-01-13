package logging;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import observer.EncryptionObserver;

public class EncryptionLogger {
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	
	public static void main(String Args[]) throws IOException{
		EncryptionObserver obs=new EncryptionObserver();
		logger.debug("New observer was built.");
		logger.debug("Opening menu.");
		Scanner user_input=new Scanner(System.in);
		System.out.println("It is encryption algorithm please insert E for "
				+ "operation one single file and F for operation on whole folder: ");
		String ForE=user_input.next();
		if(ForE.charAt(0)=='F'){
			AsyncDirectoryProcessor<DoubleKey<SimpleKey>> Code=AsyncDirectoryProcessor.buildOne();
			logger.debug("New File Encryptor was built.");
			Code.addEncryptionObserver(obs);
			logger.debug("The observer was attached to the Folder Encryptor.");
			logger.debug("Entering the encryption process.");
			Code.EncryptionMenu(Code);
		}
		else if(ForE.charAt(0)=='E'){
			FileEncryptor<DoubleKey<SimpleKey>> Code =FileEncryptor.buildOne();
			logger.debug("New File Encryptor was built.");
			Code.addEncryptionObserver(obs);
			logger.debug("The observer was attached to the File Encryptor.");
			logger.debug("Entering the encryption process.");
			Code.EncryptionMenu(Code);
		}
		else{
			logger.error("Wrong type of operation was chosen.");
		}
		user_input.close();
	}

}
