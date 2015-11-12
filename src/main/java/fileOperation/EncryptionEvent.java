package fileOperation;
public class EncryptionEvent {
	
	private String eORd;/*Encryption or decryption */
	private String file;/*where is the plain text file */
	private String algorithm;/*Which algorithm we used*/
	private String outputFile;/*Where is the output file */
	private long currentTime;
	
	public EncryptionEvent(String eORd,String file,String algorithm,
			String outputFile,long currentTime){
		this.eORd=eORd;
		this.file=file;
		this.algorithm=algorithm;
		this.outputFile=outputFile;
		this.currentTime=currentTime;
		
	}

	public String geteORd() {
		return eORd;
	}

	public String getFile() {
		return file;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public long getCurrentTime() {
		return currentTime;
	}
	

}
