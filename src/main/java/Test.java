
public class Test {
	
	public static void main(String[] args){
		doing(seti());
	}
	
	public static MathOperation seti(){
		MathOperation a=new MyMultiply();
		return a ;
		
	}
	
	public static void doing(MathOperation nath){
		System.out.print(nath.Operate(5, 9));
		
	}

}
