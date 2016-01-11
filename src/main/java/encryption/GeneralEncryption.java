package encryption;
import mathOperation.MathOperation;
import keyBuilding.KeyType;


public class GeneralEncryption<E extends KeyType> 
implements EncryptionAlgorithm<E>{

	private E key;
	MathOperation<E> encryptMethod;
	MathOperation<E> decryptMethod;

	public String Encrypt(String plain) {
		char[] buffer = plain.toCharArray();

		// Loop over characters.
		for (int i = 0; i < buffer.length; i++) {

		    char letter = buffer[i];
		    int asciiCode=(int)(letter);
		    int change=encryptMethod.Operate(asciiCode)%256;
		    letter = (char) (change);
		    
		    buffer[i] = letter;
		}
		// Return final string.
		String cipher=new String(buffer);
		return cipher;
	}
	
	public String Decrypt(String cipher) {
		char[] buffer = cipher.toCharArray();

		// Loop over characters.
		for (int i = 0; i < buffer.length; i++) {

		    
		    char letter = buffer[i];
		    int asciiCode=(int)(letter);
		    int change=(decryptMethod.Operate(asciiCode)+256)%256;
		    letter = (char) (change);
		    
		    buffer[i] = letter;
		}
		// Return final string.
		String plain=new String(buffer);
		return plain;
	}

	@Override
	public MathOperation<E> getEncryptMethod() {
		return encryptMethod;
	}

	@Override
	public int getKeySrength() {
		return 3;
	}
	
	

	@Override
	public E getKey() {
		return key;
	}

	@Override
	public void setKey(E key) {
		this.key=key;
		encryptMethod.setKey(key);
		decryptMethod.setKey(key);
	}

	@Override
	public MathOperation<E> getDecryptMethod() {
		return decryptMethod;
	}

	@Override
	public void restartKet(String target) {
		key.generateKey();
		encryptMethod.setKey(key);
		decryptMethod.setKey(key);
		key.printKeyToFile(target);
		
	}
	
	


		

}





