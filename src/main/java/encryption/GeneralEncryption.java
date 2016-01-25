package encryption;
import mathOperation.MathOperation;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.google.inject.Inject;

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

	@Inject
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
	public void restartKeyByRandom(String target) {
		key.generateKey();
		encryptMethod.setKey(key);
		decryptMethod.setKey(key);
		key.printKeyToFile(target);
		
	}
	
	
	
	

	@Override
	public int hashForKey(ArrayList<String> names) {
		int result=17;
		
		for(String n:names){
			result=31*result+n.hashCode();
		}
		
		return result%256;
		
	}

	@Override
	public ArrayList<String> methodsNames() {
		Method[] methods = GeneralEncryption.class.getMethods();

		for(Method method : methods){
		    System.out.println("method = " + method.getName());
		}
		return null;
	}

	@Override
	public void restartKeyByHash(String target) {
		ArrayList<String> list=this.methodsNames();
		int k=this.hashForKey(list);
		key.generateKeyFromHash(k);
		encryptMethod.setKey(key);
		decryptMethod.setKey(key);
		key.printKeyToFile(target);
		
	}

	@Override
	public void restartKey(String target) {
		restartKeyByHash(target);
		
	}
	
	
	
	


		

}





