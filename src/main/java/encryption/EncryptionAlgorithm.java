package encryption;
import mathOperation.MathOperation;
import java.util.Scanner;

public interface EncryptionAlgorithm {
	
	void setKey(int newKey);
	
	String Encrypt(String plain);
	
	String Decrypt(String cipher);

	void generateKey();

	void printKeyToFile();

	void setUserKey() throws invalidEncryptionKeyException;
	
	int getKey();
	
	void setUser_input(Scanner user_input);
	
	MathOperation getEncryptMethod();
	
	int getKeySrength();
	
	String toString();
	
	

}
