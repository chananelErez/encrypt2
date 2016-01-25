package encryption;
import java.lang.reflect.Method;
import java.util.ArrayList;

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

	@Override
	public ArrayList<String> methodsNames() {
		Method[] methods = XorEncryption.class.getMethods();

		ArrayList<String> list=new ArrayList<String>();
		for(Method method : methods){
		    list.add(method.getName());
		    
		}
		return list;
	}
}
