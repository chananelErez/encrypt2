
public class ShiftMultiplyEncryption extends GeneralEncryption
implements EncryptionAlgorithm{
	public void ShiftMulitiplyEncryption(){
		encryptMethod=new MyMultiply();
		decryptMethod=new MyDivision();
	}

}
