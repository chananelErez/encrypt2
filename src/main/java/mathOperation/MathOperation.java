package mathOperation;

import keyBuilding.KeyType;

public abstract class MathOperation<E extends KeyType> {
	int a;
	E key;
	public abstract int Operate(int a,E key);

}
