package encryption;
import keyBuilding.SimpleKey;
import mathOperation.MyAddition;
import mathOperation.MySubstraction;

public class ShiftUpEncryption extends GeneralEncryption<SimpleKey> 
                               implements EncryptionAlgorithm<SimpleKey> {
	public ShiftUpEncryption(){
		encryptMethod=new MyAddition<SimpleKey>();
		decryptMethod=new MySubstraction<SimpleKey>();
	}
	
	@Override
	public String toString(){
		return "Shift Up Encryption";
	}
}
