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
				"C:\\key1.txt");
		FileEncryptor.writeFile(String.valueOf(anotherKey), "key2",
				"C:\\key2.txt");
		
	}
	
	
	public void setUserKey() {
		Scanner user_input=new Scanner(System.in);
		System.out.println("please insert the first key: ");
		int Key=Integer.parseInt(user_input.next());
		setKey(Key);
		System.out.println("please insert the second key: ");
		int AnotherKey=Integer.parseInt(user_input.next());
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
