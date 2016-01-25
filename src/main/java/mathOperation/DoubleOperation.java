package mathOperation;

import java.util.ArrayList;
import keyBuilding.DoubleKey;
import keyBuilding.KeyType;
import keyBuilding.SimpleKey;

public class DoubleOperation<T extends KeyType> extends MathOperation<DoubleKey> {
	
	public MathOperation<T> operator;
	public DoubleOperation(MathOperation<T> oper){
		operator=oper;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public int Operate(int a) {
		ArrayList<SimpleKey> keys=getKey().getKey();
		T key1=(T) keys.get(0);
		T key2=(T) keys.get(1);
		this.operator.setKey(key1);
		int tmp=this.operator.Operate(a);
		this.operator.setKey(key2);
		return this.operator.Operate(tmp) ;
	}

	
	
	

}
