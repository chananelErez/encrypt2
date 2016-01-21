package logging;
import java.io.IOException;

import org.apache.log4j.Logger;

public class EncryptionLog4JLogger {
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);
	
	public static void main(String args[]) throws IOException{
		logger.info("This is logging message before the process begins.");
		
		logger.info("This is logging message after the process ends.");
	}
	

}
