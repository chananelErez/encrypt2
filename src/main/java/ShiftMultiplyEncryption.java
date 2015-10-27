
public class ShiftMultiplyEncryption extends GeneralEncryption
implements EncryptionAlgorithm{
	
	
	public ShiftMultiplyEncryption(){
		
		setEncryptMethod(new MyMultiply());
		setDecryptMethod(new MyDivision());
		
	}
	
	@Override
	public void generateKey() {
		int Key=(int)(Math.random()*13)+2;
		setKey(Key);
	}

}
