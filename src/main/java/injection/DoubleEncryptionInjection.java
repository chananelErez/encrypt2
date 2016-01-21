package injection;

import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import keyBuilding.SimpleKey;

public class DoubleEncryptionInjection {
	
	public DoubleEncryption<SimpleKey> getDouble(){
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		DoubleEncryption<SimpleKey> internalAlgo=
				                    new DoubleEncryption<SimpleKey>(algo);
		
		return internalAlgo;
	}

}
