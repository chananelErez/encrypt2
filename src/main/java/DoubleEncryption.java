import java.util.Scanner;

public class DoubleEncryption extends GeneralEncryption implements EncryptionAlgorithm {
	EncryptionAlgorithm Algo;
	private int anotherKey;
	private String plainText1;
	private String cipherText1;
	
	public DoubleEncryption(EncryptionAlgorithm algo) {
		Algo=algo;
		encryptMethod=Algo.getEncryptMethod();
		decryptMethod=Algo.getDecryptMethod();
		
	}

	public int getAnotherKey() {
		return anotherKey;
	}

	public void setAnotherKey(int anotherKey) {
		this.anotherKey = anotherKey;
	}

	public String getPlainText1() {
		return plainText1;
	}

	public void setPlainText1(String plainText1) {
		this.plainText1 = plainText1;
	}

	public String getCipherText1() {
		return cipherText1;
	}

	public void setCipherText1(String cypherText1) {
		this.cipherText1 = cypherText1;
	}
	
	
	public void generateKey() {
		int Key=(int)(Math.random()*13)+2;
		setKey(Key);
		int AnotherKey=(int)(Math.random()*13)+2;
		setAnotherKey(AnotherKey);
	}

	
	public void printKeyToFile() {
		int key1=getKey();
		FileEncryptor.writeFile(String.valueOf(key1), "key1", "C:\\Users\\user\\key1.txt");
		FileEncryptor.writeFile(String.valueOf(anotherKey), "key2", "C:\\Users\\user\\key2.txt");
		
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
	
	public void Encrypt() {
		String plain=getPlainText();
		Algo.setPlainText(plain);
		Algo.setKey(getKey());
		Algo.Encrypt();
		setPlainText1(Algo.getCipher());
		Algo.setPlainText(getPlainText1());
		Algo.setKey(getAnotherKey());
		Algo.Encrypt();
		setCipher(Algo.getCipher());
		
	}
	
	public void Decrypt() {
		String cipher=getCipher();
		Algo.setCipher(cipher);
		Algo.setKey(getAnotherKey());
		Algo.Decrypt();
		setCipherText1(Algo.getPlainText());
		Algo.setCipher(getCipherText1());
		Algo.setKey(getKey());
		Algo.Decrypt();
		setPlainText(Algo.getPlainText());
		
	}

	
	
	
	
}
