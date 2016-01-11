package fileOperation;

public class ErrorEvent implements GeneralEvent {
	private String errorKind;
	private String file;
	private String eORd;

	
	
	public String getErrorKind() {
		return errorKind;
	}

	public void setErrorKind(String errorKind) {
		this.errorKind = errorKind;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String geteORd() {
		return eORd;
	}

	public void seteORd(String eORd) {
		this.eORd = eORd;
	}
	

}
