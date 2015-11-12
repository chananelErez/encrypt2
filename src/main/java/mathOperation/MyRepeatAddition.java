package mathOperation;
public class MyRepeatAddition extends MathOperation {
	public int n;
	
	public MyRepeatAddition(int N){
		n=N;
	}
	@Override
	public int Operate(int a, int b) {
		
		return a+n*b;
	}
	

}
