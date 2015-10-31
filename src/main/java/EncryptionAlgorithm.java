
interface EncryptionAlgorithm {
	
	void setKey(int newKey);
	
	String Encrypt(String plain);
	
	String Decrypt(String cipher);

	void generateKey();

	void printKeyToFile();

	void setUserKey();
	
	int getKey();
	
	MathOperation getEncryptMethod();
	
	

}
