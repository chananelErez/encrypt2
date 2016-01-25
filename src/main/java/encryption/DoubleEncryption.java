package encryption;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import encryption.EncryptionAlgorithm;
import encryption.GeneralEncryption;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import mathOperation.DoubleOperation;


public class DoubleEncryption<T extends SimpleKey> extends 
GeneralEncryption<DoubleKey> 
implements EncryptionAlgorithm<DoubleKey> {
	EncryptionAlgorithm<T> Algo;
	
	@Inject
	public DoubleEncryption(@Named("simple") EncryptionAlgorithm<T> algo) {
		Algo=algo;
		encryptMethod=new DoubleOperation<T>(Algo.getEncryptMethod());
		decryptMethod=new DoubleOperation<T>(Algo.getDecryptMethod());
		
	}
	
	@Override
	public String toString(){
		return "Double "+Algo.toString()+" Encryption";
	}
	
	@Override
	public ArrayList<String> methodsNames() {
		Method[] methods = DoubleEncryption.class.getMethods();

		ArrayList<String> list=new ArrayList<String>();
		for(Method method : methods){
		    list.add(method.getName());
		    
		}
		return list;
	}
	


	
	
	
	
}
