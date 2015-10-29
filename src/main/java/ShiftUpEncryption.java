
public class ShiftUpEncryption extends GeneralEncryption implements EncryptionAlgorithm {
	public ShiftUpEncryption(){
		encryptMethod=new MyAddition();
		decryptMethod=new MySubstraction();
	}
}
