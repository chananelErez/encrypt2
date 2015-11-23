package observer;

import fileOperation.EncryptionEvent;

public class EncryptionObserver implements IObserver {

	
	EncryptionLogEventArgs Args=new EncryptionLogEventArgs();
	
	
	@Override
	public void update(EncryptionEvent event) {
		Args.printMessege(event);
		
	}

}
