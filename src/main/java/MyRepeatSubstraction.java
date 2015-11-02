
public class MyRepeatSubstraction extends MathOperation{

	public int n;
	
	public MyRepeatSubstraction(int N){
		n=N;
	}
	@Override
	public int Operate(int a, int b) {
		
		return a-n*b;
	}
	



}
