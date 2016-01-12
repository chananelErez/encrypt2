package fileOperation;

import listenersEvents.EncryptionEvent;
import listenersEvents.ErrorEvent;
import listenersEvents.GeneralEvent;
import observer.EncryptionObserver;


public interface ObservableEncryption {
	void addEncryptionObserver(EncryptionObserver observer);
    void removeEncryptionbserver(EncryptionObserver observer);
    void notifyObserver(GeneralEvent event);
    EncryptionEvent EncryptionEnded(String file);
    EncryptionEvent DecryptionStarted(String file);
    EncryptionEvent EncryptionStarted(String file);
    EncryptionEvent DecryptionEnded(String file);
    ErrorEvent PathNotFound(String file,String eORd);
    ErrorEvent InvalidKey(String file,String eORd);

}
