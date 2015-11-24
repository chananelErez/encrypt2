package mathOperation;

import keyBuilding.SimpleKey;

public class MySubstraction<E> extends MathOperation<SimpleKey> {

	@Override
	public int Operate(int a) {
		// TODO Auto-generated method stub
		return (a-getKey().getKey())%256;
	}

}
