package writingFormat;

import org.apache.log4j.Logger;

import algoBuilder.BuildEncryptor;
import logging.EncryptionLog4JLogger;

public class IntFileformat implements Generalformat,IntFormat {

final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);

	
	private String inputFile;
	private String outputFile;
	private String keyPath;
	private String algoName;
	private String eord;
	private String FileName;
	
	public IntFileformat(BuildEncryptor b){
		this.algoName=b.getAlgorithm();
		this.eord=b.getEDOperation();
		this.keyPath=b.getKeyPath();
		this.inputFile=b.getSourceDirectory()+"\\"+b.getFileName();
		this.outputFile=b.getSourceDirectory()+
				NameConvert(inputFile, eord)+b.getFileName();
		this.FileName=b.getFileName();
	}
	
	public String getInput() {
		return inputFile;
	}
	
	public String getOutput() {
		return outputFile;
	}
	
	public String getKeyPath() {
		return keyPath;
	}

	public String getAlgoName() {
		return algoName;
	}


	public String getEord() {
		return eord;
	}

	
	public String NameConvert(String fileName,String eORd){
		logger.debug("Converting the file name to the output file.");
		String convertName = new String();
		
		
		if(eORd.charAt(0)=='E'){
			convertName="\\encrypted\\";
		}
		if(eORd.charAt(0)=='D'){
			convertName="\\decrypted\\";
		}

		logger.debug("The convert process finished.");
		return convertName;
		
	}

	public String getFileName() {
		return FileName;
	}
	
	

}