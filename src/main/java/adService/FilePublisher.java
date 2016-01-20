package adService;

import java.util.LinkedList;

import listenersEvents.EncryptionEvent;
import listenersEvents.ErrorEvent;
import listenersEvents.GeneralEvent;
import observer.EncryptionObserver;
import writingFormat.IntFormat;

public class FilePublisher implements GeneralPublisher,PopularEvents {

	
	private LinkedList<EncryptionObserver> list =
            new LinkedList<EncryptionObserver>();
	
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

	@Override
	public EncryptionEvent EncryptionStarted(IntFormat file) {
		EncryptionEvent eventES=new EncryptionEvent(file,
				                    System.currentTimeMillis());
		return eventES;
	}

	@Override
	public EncryptionEvent DecryptionStarted(IntFormat file) {
		EncryptionEvent eventDS=new EncryptionEvent(file,
				                    System.currentTimeMillis());
		return eventDS;
	}

	@Override
	public EncryptionEvent EncryptionEnded(IntFormat file) {
		EncryptionEvent eventEE=new EncryptionEvent(file,
                System.currentTimeMillis());
		return eventEE;
	}

	@Override
	public EncryptionEvent DecryptionEnded(IntFormat file) {
		EncryptionEvent eventDE=new EncryptionEvent(file,
                System.currentTimeMillis());
		return eventDE;
	}
	
	public LinkedList<EncryptionObserver> getList(){
		return list;
		
	}

	@Override
	public ErrorEvent PathNotFound(IntFormat file) {
		ErrorEvent PNF=new ErrorEvent();
		PNF.setErrorKind("The Path was not found");
		PNF.setFile(file);
		return PNF;
	}

	@Override
	public ErrorEvent InvalidKey(IntFormat file) {
		ErrorEvent IK=new ErrorEvent();
		IK.setErrorKind("The Key was not valid");
		IK.setFile(file);
		return IK;
	}


}
