package encryption;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.google.inject.Singleton;

import keyBuilding.SimpleKey;
import mathOperation.MyAddition;
import mathOperation.MySubstraction;


@Singleton
public class ShiftUpEncryption extends GeneralEncryption<SimpleKey> 
                               implements EncryptionAlgorithm<SimpleKey> {
	public ShiftUpEncryption(){
		encryptMethod=new MyAddition<SimpleKey>();
		decryptMethod=new MySubstraction<SimpleKey>();
	}
	
	@Override
	public String toString(){
		return "Shift Up Encryption";
	}
	
	@Override
	public ArrayList<String> methodsNames() {
		Method[] methods = ShiftUpEncryption.class.getMethods();
		ArrayList<String> list=new ArrayList<String>();
		for(Method method : methods){
		    list.add(method.getName());
		    
		}
		return list;
	}
}
