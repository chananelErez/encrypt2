package encryption;
import mathOperation.MyRepeatAddition;
import mathOperation.MyRepeatSubstraction;

public class RepeatEncryption extends GeneralEncryption
implements EncryptionAlgorithm{
	public int n;
	
	public RepeatEncryption(int N){
		n=N;
	
	encryptMethod=new MyRepeatAddition(n);
	decryptMethod=new MyRepeatSubstraction(n);
	}
	
	@Override
	public int getKeySrength() {
		return (int)Math.floor(Math.log10(Math.floorDiv(256, n)))+1;
	}
	
	@Override
	public String toString(){
		return "Repeat Encryption";
	}
}