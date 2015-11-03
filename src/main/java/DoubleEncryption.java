import java.util.Scanner;

public class DoubleEncryption extends GeneralEncryption implements EncryptionAlgorithm {
	EncryptionAlgorithm Algo;
	private int anotherKey;
	
	public DoubleEncryption(EncryptionAlgorithm algo) {
		Algo=algo;
		
	}

	public int getAnotherKey() {
		return anotherKey;
	}

	public void setAnotherKey(int anotherKey) {
		this.anotherKey = anotherKey;
	}
	
	public void generateKey() {
		int Key=(int)(Math.random()*13)+2;
		setKey(Key);
		int AnotherKey=(int)(Math.random()*13)+2;
		setAnotherKey(AnotherKey);
	}

	
	public void printKeyToFile() {
		int key1=getKey();
		FileEncryptor.writeFile(String.valueOf(key1), "key1", 
				"C:\\Users\\user\\key1.txt");
		FileEncryptor.writeFile(String.valueOf(anotherKey), "key2",
				"C:\\Users\\user\\key2.txt");
		
	}
	
	
	public void setUserKey() throws invalidEncryptionKeyException {
		Scanner user_input=new Scanner(System.in);
		System.out.println("please insert the first key: ");
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
		System.out.println("please insert the second key: ");
		if(!user_input.hasNextInt()){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		int AnotherKey=Integer.parseInt(user_input.next());
		if(Key<=0){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		setAnotherKey(AnotherKey);
		user_input.close();
		
	}
	
	public String Encrypt(String plain) {
		Algo.setKey(Algo.getEncryptMethod().Operate(getKey(), anotherKey));
		String cipher=Algo.Encrypt(plain);
		return cipher;
		
	}
	
	public String Decrypt(String cipher) {
		Algo.setKey(Algo.getEncryptMethod().Operate(getKey(), anotherKey));
		String plain=Algo.Decrypt(cipher);
		return plain;
		
	}


	
	
	
	
}
