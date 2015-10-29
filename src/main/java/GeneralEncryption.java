import java.util.Scanner;

public class GeneralEncryption implements EncryptionAlgorithm{

	private String plainText;
	private String cypherText;
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
	
	public String getCypher() {
		
		return cypherText;
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
		cypherText=new String(buffer);
		
	}
	public void Decrypt() {
		char[] buffer = cypherText.toCharArray();

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
	
	public void setDecryptMethod(MathOperation decryptMethod) {
		this.decryptMethod = decryptMethod;
	}
	
	public void setEncryptMethod(MathOperation encryptMethod) {
		this.encryptMethod = encryptMethod;
	}
	public void setCypher(String newcypher) {
		cypherText=newcypher;
		
	}

	@Override
	public void generateKey() {
		int Key=(int)(Math.random()*13)+1;
		setKey(Key);
	}

	@Override
	public void printKeyToFile() {
		FileEncryptor.writeFile(String.valueOf(key), "key", "C:/key.txt");
		
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





