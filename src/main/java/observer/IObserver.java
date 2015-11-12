package observer;

import fileOperation.EncryptionEvent;

public interface IObserver {
	void update(EncryptionEvent event);

}
