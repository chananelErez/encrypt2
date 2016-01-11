package encryption;
import mathOperation.MathOperation;
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
	
	void restartKet(String target);
	
	

}
