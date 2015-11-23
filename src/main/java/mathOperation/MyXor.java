package mathOperation;

import keyBuilding.SimpleKey;

public class MyXor<E> extends MathOperation<SimpleKey> {

	@Override
	public int Operate(int a, SimpleKey key) {
		// TODO Auto-generated method stub
		return a^key.getKey();
	}

}
