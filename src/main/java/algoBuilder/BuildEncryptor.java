package algoBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import encryptionInfo.GeneralInfo;

@XmlRootElement
public class BuildEncryptor implements GeneralInfo{
	String EDOperation;
	String FileORDirec;
	String SourceDirectory;
	String KeyPath;
	String FileName;
	String Algorithm;
	String KeyType;
	Boolean IsDouble;
	int Repeat;
	
	public String getEDOperation() {
		return EDOperation;
	}

	@XmlElement
	public void setEDOperation(String eord) {
		this.EDOperation = eord;
	}
	
	public int getRepeat() {
		return Repeat;
	}

	@XmlElement
	public void setRepeat(int Repeat) {
		this.Repeat = Repeat;
	}
	
	public String getFileORDirec() {
		return FileORDirec;
	}

	@XmlElement
	public void setFileORDirec(String FileORDirec) {
		this.FileORDirec = FileORDirec;
	}
	public String getSourceDirectory() {
		return SourceDirectory;
	}

	@XmlElement
	public void setSourceDirectory(String SourceDirectory) {
		this.SourceDirectory = SourceDirectory;
	}
	
	public String getKeyPath() {
		return KeyPath;
	}

	@XmlElement
	public void setKeyPath(String KeyPath) {
		this.KeyPath = KeyPath ;
	}
	
	public String getFileName() {
		return FileName;
	}

	@XmlElement
	public void setFileName(String FileName) {
		this.FileName = FileName;
		
	}
	
	public String getAlgorithm() {
		return Algorithm;
	}

	@XmlElement
	public void setAlgorithm(String Algorithm) {
		this.Algorithm = Algorithm;
	}
	
	public String getKeyType() {
		return KeyType;
	}

	@XmlElement
	public void setKeyType(String KeyType) {
		this.KeyType = KeyType;
	}
	
	public Boolean getIsDouble() {
		return IsDouble;
	}

	@XmlElement
	public void setIsDouble(Boolean IsDouble) {
		this.IsDouble = IsDouble;
	}

}
