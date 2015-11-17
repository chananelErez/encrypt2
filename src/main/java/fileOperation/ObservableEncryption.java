package fileOperation;

import observer.EncryptionObserver;


public interface ObservableEncryption {
	void addEncryptionObserver(EncryptionObserver observer);
    void removeEncryptionbserver(EncryptionObserver observer);
    void notifyObserver(EncryptionEvent event);
    EncryptionEvent EncryptionEnded(String file);
    EncryptionEvent DecryptionStarted(String file);
    EncryptionEvent EncryptionStarted(String file);
    EncryptionEvent DecryptionEnded(String file);

}
