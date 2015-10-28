
interface EncryptionAlgorithm {
	
	void setPlainText(String plaintext);
	
	void setKey(int newKey);
	
	String getCipher();
	
	void setCipher(String newcypher);
	
	void Encrypt();
	
	void Decrypt();
	
	void setDecryptMethod(MathOperation decryptMethod);
	
	void setEncryptMethod(MathOperation encryptMethod);
	
	MathOperation getEncryptMethod();
	
	MathOperation getDecryptMethod();

	String getPlainText();

	void generateKey();

	void printKeyToFile();

	void setUserKey();
	
	int getKey();
	
	

}
