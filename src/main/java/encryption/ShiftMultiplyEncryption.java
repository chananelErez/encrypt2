package encryption;
import mathOperation.MyMultiply;
public class ShiftMultiplyEncryption extends GeneralEncryption
implements EncryptionAlgorithm{
	
	private int decryptKey;
	
	public ShiftMultiplyEncryption(){
		
		encryptMethod=new MyMultiply();
		decryptMethod=new MyMultiply();
		
	}
	@Override
	public String Decrypt(String cipher) {
		int key=getKey();
		for(int i=0;i<256;i++){
			if((key*i)%256==1){
				decryptKey=i;
			}
		}
		char[] buffer = cipher.toCharArray();

		// Loop over characters.
		for (int i = 0; i < buffer.length; i++) {

		    
		    char letter = buffer[i];
		    int asciiCode=(int)(letter);
		    int change=decryptMethod.Operate(asciiCode, decryptKey)%256;
		    letter = (char) (change);
		    
		    buffer[i] = letter;
		}
		// Return final string.
		String plain=new String(buffer);
		return plain;
	}
	
	@Override
	public void generateKey() {
		boolean check=false;
		int Key=2;
		while(!check){
			Key=(int)(Math.random()*13)+3;
			if(GCD(256,Key)==1){
				check=true;
			}
		}
		setKey(Key);
	}

	public int GCD(int a, int b) {
		   if (b==0) return a;
		   return GCD(b,a%b);
	}
	
	@Override
	public String toString(){
		return "Shift Multiply Encryption";
	}
	
	
}
