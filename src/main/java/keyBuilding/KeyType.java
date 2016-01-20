package keyBuilding;

import encryption.invalidEncryptionKeyException;

public interface KeyType {
	
	void generateKey();

	void printKeyToFile(String file);

	void setUserKey() throws invalidEncryptionKeyException;
	
	void getKeyFromFile(String KeyPath) throws invalidEncryptionKeyException;
	
	
}
