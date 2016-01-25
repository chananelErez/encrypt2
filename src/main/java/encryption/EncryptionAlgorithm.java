package encryption;
import mathOperation.MathOperation;

import java.util.ArrayList;

import keyBuilding.KeyType;

public interface EncryptionAlgorithm<E extends KeyType> {
	
	String Encrypt(String plain);
	
	String Decrypt(String cipher);
	
	MathOperation<E> getEncryptMethod();
	
	MathOperation<E> getDecryptMethod();
	
	int getKeySrength();
	
	String toString();
	
	E getKey();
	
	void setKey(E key);
	
	void restartKey(String target);
	
	int hashForKey(ArrayList<String> names);
	
	ArrayList<String> methodsNames();
	
	void restartKeyByHash(String target);

	void restartKeyByRandom(String target);

}
