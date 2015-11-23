package encryption;
import keyBuilding.SimpleKey;
import mathOperation.MyXor;


public class XorEncryption extends GeneralEncryption<SimpleKey> 
implements EncryptionAlgorithm<SimpleKey>{
	public XorEncryption(){
		encryptMethod=new MyXor<SimpleKey>();
		decryptMethod=new MyXor<SimpleKey>();
	}
	@Override
	public String toString(){
		return "Xor Encryption";
	}

}
