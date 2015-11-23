package mathOperation;

import keyBuilding.SimpleKey;

public class MyAddition<E> extends MathOperation<SimpleKey>{

	@Override
	public int Operate(int a, SimpleKey key) {
		
		return a+key.getKey();
	}
	
}
