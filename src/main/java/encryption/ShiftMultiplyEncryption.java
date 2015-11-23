package encryption;
import keyBuilding.MultiplyKey;
import mathOperation.MyMultiply;
public class ShiftMultiplyEncryption extends GeneralEncryption<MultiplyKey>
implements EncryptionAlgorithm<MultiplyKey>{
	
	private MultiplyKey decryptKey=new MultiplyKey();
	
	public ShiftMultiplyEncryption(){
		
		encryptMethod=new MyMultiply<MultiplyKey>();
		decryptMethod=new MyMultiply<MultiplyKey>();
		
	}
	@Override
	public String Decrypt(String cipher) {
		int key=this.getKey().getKey();
		for(int i=0;i<256;i++){
			if((key*i)%256==1){
				decryptKey.setKey(i);
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
	public String toString(){
		return "Shift Multiply Encryption";
	}
	
	
}
