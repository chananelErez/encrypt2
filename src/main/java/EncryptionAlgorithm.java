
interface EncryptionAlgorithm {

	void setPlainText(String plaintext);
	
	void setKey(int newKey);
	
	String getCipher();
	
	void setCipher(String newcypher);
	
	void Encrypt();
	
	void Decrypt();
	
	String getPlainText();

	void generateKey();

	void printKeyToFile();

	void setUserKey();
	
	int getKey();
	
	MathOperation getEncryptMethod();
	
	

}
