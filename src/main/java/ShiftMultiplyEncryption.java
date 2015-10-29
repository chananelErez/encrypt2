
public class ShiftMultiplyEncryption extends GeneralEncryption
implements EncryptionAlgorithm{
	
	
	public ShiftMultiplyEncryption(){
		
		encryptMethod=new MyMultiply();
		decryptMethod=new MyDivision();
		
	}
	
	@Override
	public void generateKey() {
		int Key=(int)(Math.random()*13)+2;
		setKey(Key);
	}

}
