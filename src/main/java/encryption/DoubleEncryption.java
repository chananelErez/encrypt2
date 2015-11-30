package encryption;
import encryption.EncryptionAlgorithm;
import encryption.GeneralEncryption;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import mathOperation.DoubleOperation;


public class DoubleEncryption<T extends SimpleKey> extends 
GeneralEncryption<DoubleKey<T>> 
implements EncryptionAlgorithm<DoubleKey<T>> {
	EncryptionAlgorithm<T> Algo;
	
	public DoubleEncryption(EncryptionAlgorithm<T> algo) {
		Algo=algo;
		encryptMethod=new DoubleOperation<T>(Algo.getEncryptMethod());
		decryptMethod=new DoubleOperation<T>(Algo.getDecryptMethod());
		
	}
	
	@Override
	public String toString(){
		return "Double "+Algo.toString()+" Encryption";
	}
	
	
	


	
	
	
	
}
