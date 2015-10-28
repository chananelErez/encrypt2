import java.util.Scanner;

public class GeneralEncryption implements EncryptionAlgorithm{

	private String plainText;
	private String cipherText;
	private int key;
	MathOperation encryptMethod=new MyAddition();
	MathOperation decryptMethod=new MySubstraction();
	
	public void setPlainText(String plaintext) {
		plainText=plaintext;
		
	}
	
	public String getPlainText(){
		return plainText;
	}
	
	public void setKey(int newKey) {
		key=newKey;
		
	}
	
	public int getKey(){
		return key;
	}
	
	public String getCipher() {
		
		return cipherText;
	}
	public void Encrypt() {
		char[] buffer = plainText.toCharArray();

		// Loop over characters.
		for (int i = 0; i < buffer.length; i++) {

		    char letter = buffer[i];
		    int asciiCode=(int)(letter);
		    int change=encryptMethod.Operate(asciiCode, key);
		    letter = (char) (change);
		    
		    buffer[i] = letter;
		}
		// Return final string.
		cipherText=new String(buffer);
		
	}
	public void Decrypt() {
		char[] buffer = cipherText.toCharArray();

		// Loop over characters.
		for (int i = 0; i < buffer.length; i++) {

		    
		    char letter = buffer[i];
		    int asciiCode=(int)(letter);
		    int change=decryptMethod.Operate(asciiCode, key);
		    letter = (char) (change);
		    
		    buffer[i] = letter;
		}
		// Return final string.
		plainText=new String(buffer);
		
	}
	
	public void setDecryptMethod(MathOperation DecryptMethod) {
		decryptMethod = DecryptMethod;
	}
	
	public MathOperation getDecryptMethod( ) {
		return this.decryptMethod;
	}
	public MathOperation getEncryptMethod() {
		return encryptMethod;
	}
	
	public void setEncryptMethod(MathOperation EncryptMethod) {
		encryptMethod = EncryptMethod;
	}
	public void setCipher(String newcypher) {
		cipherText=newcypher;
		
	}

	@Override
	public void generateKey() {
		int Key=(int)(Math.random()*13)+1;
		setKey(Key);
	}

	@Override
	public void printKeyToFile() {
		FileEncryptor.writeFile(String.valueOf(key), "key", "C:\\Users\\user\\key.txt");
		
	}

	@Override
	public void setUserKey() {
		Scanner user_input=new Scanner(System.in);
		System.out.println("please insert the key: ");
		int Key=Integer.parseInt(user_input.next());
		setKey(Key);
		user_input.close();
		
	}
	
	

}





