package adService;

import listenersEvents.GeneralEvent;
import observer.EncryptionObserver;

public interface GeneralPublisher {
	void addEncryptionObserver(EncryptionObserver observer);
    void removeEncryptionbserver(EncryptionObserver observer);
    void notifyObserver(GeneralEvent event);
    

}
