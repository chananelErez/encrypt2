package mathOperation;

import keyBuilding.MultiplyKey;

public class MyMultiply<E> extends MathOperation<MultiplyKey>{

	@Override
	public int Operate(int a) {
		// TODO Auto-generated method stub
		return a*getKey().getKey();
	}
	
}
