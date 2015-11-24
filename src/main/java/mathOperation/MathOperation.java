package mathOperation;

import keyBuilding.KeyType;

public abstract class MathOperation<E extends KeyType> {
	private E key;
	public abstract int Operate(int a);
	
	public E getKey() {
		return key;
	}
	public void setKey(E key) {
		this.key = key;
	}

}
