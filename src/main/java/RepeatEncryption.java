
public class RepeatEncryption extends GeneralEncryption
implements EncryptionAlgorithm{
	public int n;
	
	public RepeatEncryption(int N){
		n=N;
	
	encryptMethod=new MyRepeatAddition(n);
	decryptMethod=new MyRepeatSubstraction(n);
	}
}