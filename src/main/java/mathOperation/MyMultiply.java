package mathOperation;

import keyBuilding.MultiplyKey;

public class MyMultiply<E> extends MathOperation<MultiplyKey>{

	@Override
	public int Operate(int a) {
		return a*getKey().getKey();
	}
	
}
