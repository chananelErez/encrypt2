package writingFormat;

import org.apache.log4j.Logger;

import algoBuilder.BuildEncryptor;
import logging.EncryptionLog4JLogger;

public class Directoryformat implements Generalformat {
	
final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);

	
	private String input;
	private String output;
	private String keyPath;
	private String algoName;
	private String eord;
	
	public Directoryformat(BuildEncryptor b) {
		this.algoName=b.getAlgorithm();
		this.eord=b.getEDOperation();
		this.keyPath=b.getKeyPath();
		this.input=b.getSourceDirectory();
		this.output=NameConvert(input, eord);
	}
	
	public String getInput() {
		return input;
	}
	
	public String getOutput() {
		return output;
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

	@Override
	public String NameConvert(String fileName, String eORd) {
		String output=new String();
		if(eORd.charAt(0)=='E'){
			output=fileName+"\\encrypted";
		}else if(eORd.charAt(0)=='D'){
			output=fileName+"\\decrypted";
		}
		return output;
	}



}
