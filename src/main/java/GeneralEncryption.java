import java.util.Scanner;

public class GeneralEncryption implements EncryptionAlgorithm{

	private int key;
	private Scanner user_input;
	MathOperation encryptMethod=new MyAddition();
	MathOperation decryptMethod=new MySubstraction();
	
	public void setKey(int newKey) {
		key=newKey;
		
	}
	
	public int getKey(){
		return key;
	}
	

	public String Encrypt(String plain) {
		char[] buffer = plain.toCharArray();

		// Loop over characters.
		for (int i = 0; i < buffer.length; i++) {

		    char letter = buffer[i];
		    int asciiCode=(int)(letter);
		    int change=encryptMethod.Operate(asciiCode, key)%256;
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
		    int change=(decryptMethod.Operate(asciiCode, key)+256)%256;
		    letter = (char) (change);
		    
		    buffer[i] = letter;
		}
		// Return final string.
		String plain=new String(buffer);
		return plain;
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
	public void setUserKey() throws invalidEncryptionKeyException {
		/*Scanner user_input=new Scanner(System.in);*/
		System.out.println("please insert the key: ");
		if(!user_input.hasNextInt()){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		int Key=Integer.parseInt(user_input.next());
		if(Key<=0){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		setKey(Key);
		user_input.close();
		
	}

	@Override
	public MathOperation getEncryptMethod() {
		// TODO Auto-generated method stub
		return encryptMethod;
	}

	public void setUser_input(Scanner user_input) {
		this.user_input = user_input;
	}

	@Override
	public int getKeySrength() {
		return 3;
	}


		

}





