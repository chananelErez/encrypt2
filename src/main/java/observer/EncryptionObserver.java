package observer;

import listenersEvents.GeneralEvent;

public class EncryptionObserver implements IObserver {

	
	EncryptionLogEventArgs Args=new EncryptionLogEventArgs();
	
	
	@Override
	public void update(GeneralEvent event) {
		Args.printMessage(event);
		
	}

}
