package keyBuilding;

import encryption.invalidEncryptionKeyException;

public interface KeyType {
	
	void generateKey();

	void printKeyToFile();

	void setUserKey() throws invalidEncryptionKeyException;
	
	
}
