package encryptionInfo;

public class EncryptionKind implements GeneralInfo{
	private String algorithm;
	private String keyType;
	private int repeat;
	private boolean isDouble;
	
	public String getAlgorithm() {
		return algorithm;
	}
	
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String getKeyType() {
		return keyType;
	}
	
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	
	public int getRepeat() {
		return repeat;
	}
	
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	
	public boolean isDouble() {
		return isDouble;
	}
	
	public void setDouble(boolean isDouble) {
		this.isDouble = isDouble;
	}
	

}
