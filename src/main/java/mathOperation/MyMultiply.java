package mathOperation;

import keyBuilding.MultiplyKey;

public class MyMultiply<E> extends MathOperation<MultiplyKey>{

	@Override
	public int Operate(int a, MultiplyKey key) {
		// TODO Auto-generated method stub
		return a*key.getKey();
	}
	
}
