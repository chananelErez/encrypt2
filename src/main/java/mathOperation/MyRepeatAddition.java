package mathOperation;

import keyBuilding.SimpleKey;

public class MyRepeatAddition<E> extends MathOperation<SimpleKey> {
	public int n;
	
	public MyRepeatAddition(int N){
		n=N;
	}
	
	@Override
	public int Operate(int a) {
		// TODO Auto-generated method stub
		return a+n*getKey().getKey();
	}
	

}
