package encryption;
import java.lang.reflect.Method;
import java.util.ArrayList;

import keyBuilding.SimpleKey;
import mathOperation.MyRepeatAddition;
import mathOperation.MyRepeatSubstraction;

public class RepeatEncryption extends GeneralEncryption<SimpleKey>
implements EncryptionAlgorithm<SimpleKey>{
	public int n;
	
	public RepeatEncryption(int N){
		n=N;
	
	encryptMethod=new MyRepeatAddition<SimpleKey>(n);
	decryptMethod=new MyRepeatSubstraction<SimpleKey>(n);
	}
	
	@Override
	public int getKeySrength() {
		return (int)Math.floor(Math.log10(Math.floorDiv(256, n)))+1;
	}
	
	@Override
	public String toString(){
		return "Repeat Encryption";
	}
	
	@Override
	public ArrayList<String> methodsNames() {
		Method[] methods = RepeatEncryption.class.getMethods();

		ArrayList<String> list=new ArrayList<String>();
		for(Method method : methods){
		    list.add(method.getName());
		    
		}
		return list;
	}
}