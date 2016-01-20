package fileOperation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import logging.EncryptionLog4JLogger;

public class Analphabet {

	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);

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

}
