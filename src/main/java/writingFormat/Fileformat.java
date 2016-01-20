package writingFormat;

import org.apache.log4j.Logger;

import algoBuilder.BuildEncryptor;
import logging.EncryptionLog4JLogger;

public class Fileformat implements Generalformat,IntFormat{
	
	final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);

	
	private String inputFile;
	private String outputFile;
	private String keyPath;
	private String algoName;
	private String eord;
	
	public Fileformat(BuildEncryptor b){
		this.algoName=b.getAlgorithm();
		this.eord=b.getEDOperation();
		this.inputFile=b.getSourceDirectory()+"\\"+b.getFileName();
		this.outputFile=NameConvert(inputFile, eord);
		
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
	
	

}
