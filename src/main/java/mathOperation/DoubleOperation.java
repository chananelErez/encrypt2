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
	public int Operate(int a) {
		List<T> keys=getKey().getKey();
		T key1=keys.get(0);
		T key2=keys.get(1);
		this.operator.setKey(key1);
		int tmp=this.operator.Operate(a);
		this.operator.setKey(key2);
		return this.operator.Operate(tmp) ;
	}

	
	
	

}
