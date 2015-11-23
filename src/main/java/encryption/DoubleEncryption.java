package encryption;
import java.util.Scanner;
import encryption.EncryptionAlgorithm;
import encryption.GeneralEncryption;
import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.KeyType;
import keyBuilding.SimpleKey;
import mathOperation.DoubleOperation;
import mathOperation.MyAddition;
import mathOperation.MySubstraction;


public class DoubleEncryption<T extends SimpleKey> extends 
GeneralEncryption<DoubleKey<T>> 
implements EncryptionAlgorithm<DoubleKey<T>> {
	EncryptionAlgorithm<T> Algo;
	
	private DoubleKey<T> key;
	
	public DoubleEncryption(EncryptionAlgorithm<T> algo) {
		Algo=algo;
		encryptMethod=new DoubleOperation<T>(Algo.getEncryptMethod());
		decryptMethod=new DoubleOperation<T>(Algo.getDecryptMethod());
		
	}
	
	
	


	
	
	
	
}
