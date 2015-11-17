package observer;

import fileOperation.EncryptionEvent;

public class EncryptionObserver implements IObserver {

	public EncryptionLogEventArgs Args;
	
	@Override
	public void update(EncryptionEvent event) {
		Args.printMessege(event);
		
	}

}
