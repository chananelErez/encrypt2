package mathOperation;

import keyBuilding.SimpleKey;

public class MyDivision<E> extends MathOperation<SimpleKey> {

	@Override
	public int Operate(int a, SimpleKey key) {
		// TODO Auto-generated method stub
		return (int)(a/key.getKey());
	}

}
