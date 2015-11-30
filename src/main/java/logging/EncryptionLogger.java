package logging;

import java.io.IOException;

import org.apache.log4j.Logger;

import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import observer.EncryptionObserver;

public class EncryptionLogger {
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	
	public static void main(String Args[]) throws IOException{
		EncryptionObserver obs=new EncryptionObserver();
		logger.debug("New observer was built.");
		FileEncryptor<DoubleKey<SimpleKey>> Code =FileEncryptor.buildOne();
		logger.debug("New File Encryptor was built.");
		Code.addEncryptionObserver(obs);
		logger.debug("The observer was attached to the File Encryptor.");
		logger.debug("Entering the encryption process.");
		Code.EncryptionMenu(Code);
		
	}

}
