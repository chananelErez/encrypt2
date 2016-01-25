package encryption;
import java.lang.reflect.Method;
import java.util.ArrayList;

import keyBuilding.MultiplyKey;
import mathOperation.MyModDivision;
import mathOperation.MyMultiply;
public class ShiftMultiplyEncryption extends GeneralEncryption<MultiplyKey>
implements EncryptionAlgorithm<MultiplyKey>{

	
	public ShiftMultiplyEncryption(){
		
		encryptMethod=new MyMultiply<MultiplyKey>();
		decryptMethod=new MyModDivision<MultiplyKey>();
		
	}
		
	@Override
	public String toString(){
		return "Shift Multiply Encryption";
	}
	
	@Override
	public ArrayList<String> methodsNames() {
		Method[] methods = ShiftMultiplyEncryption.class.getMethods();
		ArrayList<String> list=new ArrayList<String>();
		for(Method method : methods){
		    list.add(method.getName());
		    
		}
		return list;
	}
	
	
}
