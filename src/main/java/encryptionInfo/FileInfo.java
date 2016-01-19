package encryptionInfo;

public class FileInfo implements GeneralInfo {
	private String ford;
	private String fileN;
	private String DireN;
	private String keyPath;
	
	public String getFord() {
		return ford;
	}
	
	public void setFord(String ford) {
		this.ford = ford;
	}
	
	public String getFileN() {
		return fileN;
	}
	
	public void setFileN(String fileN) {
		this.fileN = fileN;
	}
	
	public String getDireN() {
		return DireN;
	}
	
	public void setDireN(String direN) {
		DireN = direN;
	}
	
	public String getKeyPath() {
		return keyPath;
	}
	
	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

}
