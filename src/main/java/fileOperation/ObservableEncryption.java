package fileOperation;

import listenersEvents.GeneralEvent;
import observer.EncryptionObserver;


public interface ObservableEncryption {
	void addEncryptionObserver(EncryptionObserver observer);
    void removeEncryptionbserver(EncryptionObserver observer);
    void notifyObserver(GeneralEvent event);
   
}
