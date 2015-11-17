package encryption;
import mathOperation.MyAddition;
import mathOperation.MySubstraction;

public class ShiftUpEncryption extends GeneralEncryption implements EncryptionAlgorithm {
	public ShiftUpEncryption(){
		encryptMethod=new MyAddition();
		decryptMethod=new MySubstraction();
	}
	
	@Override
	public String toString(){
		return "Shift Up Encryption";
	}
}
