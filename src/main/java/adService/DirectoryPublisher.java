package adService;

import java.util.concurrent.ConcurrentLinkedQueue;

import listenersEvents.EncryptionEvent;
import listenersEvents.ErrorEvent;
import listenersEvents.GeneralEvent;
import observer.EncryptionObserver;
import writingFormat.Directoryformat;

public class DirectoryPublisher implements GeneralPublisher {
	
	
	private ConcurrentLinkedQueue<EncryptionObserver> list =
            new ConcurrentLinkedQueue<EncryptionObserver>();
	
	private FilePublisher intP;

	public EncryptionEvent EncryptionFolderStarted(Directoryformat folder){
		EncryptionEvent eventEFS=new EncryptionEvent(folder,
				System.currentTimeMillis());
		return eventEFS;
		
	}
	

	public EncryptionEvent EncryptionFolderEnded(Directoryformat folder){
		EncryptionEvent eventEFE=new EncryptionEvent(folder,
				System.currentTimeMillis());
		return eventEFE;
	}
	

	public EncryptionEvent DecryptionFolderStarted(Directoryformat folder){
		EncryptionEvent eventEFS=new EncryptionEvent(folder,
				System.currentTimeMillis());
		return eventEFS;
		
	}
	

	public EncryptionEvent DecryptionFolderEnded(Directoryformat folder){
		EncryptionEvent eventEFE=new EncryptionEvent(folder,
				System.currentTimeMillis());
		return eventEFE;
	}

	@Override
	public void addEncryptionObserver(EncryptionObserver observer) {
		list.add(observer);
		
	}

	@Override
	public void removeEncryptionbserver(EncryptionObserver observer) {
		list.remove(observer);
		
	}

	@Override
	public void notifyObserver(GeneralEvent event) {
		for(EncryptionObserver observer : list)
        {
            observer.update(event);
        }
		
	}
	
	public ErrorEvent PathNotFound(Directoryformat file) {
		ErrorEvent PNF=new ErrorEvent();
		PNF.setErrorKind("The Path was not found");
		PNF.setFile(file);
		return PNF;
	}

	public ErrorEvent InvalidKey(Directoryformat file) {
		ErrorEvent IK=new ErrorEvent();
		IK.setErrorKind("The Key was not valid");
		IK.setFile(file);
		return IK;
	}
	public ConcurrentLinkedQueue<EncryptionObserver> getList(){
		return list;
		
	}


	public FilePublisher getIntP() {
		return intP;
	}


	public void setIntP(FilePublisher intP) {
		this.intP = intP;
	}


}
