package encryption;
import mathOperation.MyXor;


public class XorEncryption extends GeneralEncryption implements EncryptionAlgorithm{
	public XorEncryption(){
		encryptMethod=new MyXor();
		decryptMethod=new MyXor();
	}

}
