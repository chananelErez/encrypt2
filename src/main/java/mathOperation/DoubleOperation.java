package mathOperation;

import java.util.List;

import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;

public class DoubleOperation<T extends SimpleKey> extends MathOperation<DoubleKey<T>> {
	
	public MathOperation<T> operator;
	public DoubleOperation(MathOperation<T> oper){
		operator=oper;
		
	}

	@Override
	public int Operate(int a, DoubleKey<T> key) {
		List<T> keys=key.getKey();
		T key1=keys.get(0);
		T key2=keys.get(1);
		
		return this.operator.Operate(this.operator.Operate(a, key1),key2) ;
	}

	
	
	

}
