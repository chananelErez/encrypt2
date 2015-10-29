import java.util.Scanner;

public class DoubleEncryption extends GeneralEncryption implements EncryptionAlgorithm {
	EncryptionAlgorithm Algo=new ShiftUpEncryption();
	private int anotherKey;
	private String plainText1;
	private String cypherText1;
	
	public DoubleEncryption(EncryptionAlgorithm algo) {
		Algo=algo;
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

	public String getCypherText1() {
		return cypherText1;
	}

	public void setCypherText1(String cypherText1) {
		this.cypherText1 = cypherText1;
	}
	
	
	public void generateKey() {
		int Key=(int)(Math.random()*13)+1;
		setKey(Key);
		int AnotherKey=(int)(Math.random()*13)+1;
		setAnotherKey(AnotherKey);
	}

	
	public void printKeyToFile() {
		int key1=getKey();
		FileEncryptor.writeFile(String.valueOf(key1), "key1", "C:/key1.txt");
		FileEncryptor.writeFile(String.valueOf(anotherKey), "key2", "C:/key2.txt");
		
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
		setPlainText1(Algo.getCypher());
		Algo.setPlainText(getPlainText1());
		Algo.setKey(getAnotherKey());
		Algo.Encrypt();
		setCypher(Algo.getCypher());
		
	}
	
	public void Decrypt() {
		String cypher=getCypher();
		Algo.setCypher(cypher);
		Algo.setKey(getAnotherKey());
		Algo.Decrypt();
		setCypherText1(Algo.getPlainText());
		Algo.setCypher(getCypherText1());
		Algo.setKey(getKey());
		Algo.Decrypt();
		setPlainText(Algo.getPlainText());
		
	}

	
	
	
	
}
