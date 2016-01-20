package listenersEvents;


import writingFormat.Generalformat;

public class ErrorEvent implements GeneralEvent {
	private String errorKind;
	private Generalformat file;
	

	
	
	public String getErrorKind() {
		return errorKind;
	}

	public void setErrorKind(String errorKind) {
		this.errorKind = errorKind;
	}

	public Generalformat getFile() {
		return file;
	}

	public void setFile(Generalformat file) {
		this.file = file;
	}
	

}
