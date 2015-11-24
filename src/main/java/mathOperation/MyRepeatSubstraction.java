package mathOperation;

import keyBuilding.SimpleKey;

public class MyRepeatSubstraction<E> extends MathOperation<SimpleKey>{

	public int n;
	
	public MyRepeatSubstraction(int N){
		n=N;
	}

	@Override
	public int Operate(int a) {
		// TODO Auto-generated method stub
		return a-n*getKey().getKey();
	}
	



}
