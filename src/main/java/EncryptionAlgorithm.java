
interface EncryptionAlgorithm {
	
	void setPlainText(String plaintext);
	
	void setKey(int newKey);
	
	String getCypher();
	
	void setCypher(String newcypher);
	
	void Encrypt();
	
	void Decrypt();
	
	void setDecryptMethod(MathOperation decryptMethod);
	
	void setEncryptMethod(MathOperation encryptMethod);

	String getPlainText();

	void generateKey();

	void printKeyToFile();

	void setUserKey();
	
	

}
