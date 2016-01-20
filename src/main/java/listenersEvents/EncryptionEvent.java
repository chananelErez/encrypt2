package listenersEvents;

import writingFormat.Generalformat;

public class EncryptionEvent implements GeneralEvent {
	
	private String eORd;/*Encryption or decryption */
	private String file;/*where is the plain text file */
	private String algorithm;/*Which algorithm we used*/
	private String outputFile;/*Where is the output file */
	private long currentTime;
	
	public EncryptionEvent(Generalformat file,long currentTime){
		this.eORd=file.getEord();
		this.file=file.getInput();
		this.algorithm=file.getAlgoName();
		this.outputFile=file.getOutput();
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
	@Override
	public boolean equals(Object o){
		if(!(o instanceof EncryptionEvent)){
			return false;
		}
		EncryptionEvent e=(EncryptionEvent)o;
		
		return e.algorithm==this.algorithm && e.currentTime==this.currentTime&&
				e.eORd==this.eORd&&e.file==this.file;
		
	}
	@Override
	public int hashCode(){
		int result=17;
		result=31*result+this.eORd.hashCode();
		result=31*result+this.file.hashCode();
		result=31*result+this.algorithm.hashCode();
		result=31*result+(int)(this.currentTime^(this.currentTime>>>32));
		return result;
		
		
	}
	

}
