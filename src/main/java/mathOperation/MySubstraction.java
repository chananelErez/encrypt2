package mathOperation;

import keyBuilding.SimpleKey;

public class MySubstraction<E> extends MathOperation<SimpleKey> {

	@Override
	public int Operate(int a) {
		return (a-getKey().getKey())%256;
	}

}
