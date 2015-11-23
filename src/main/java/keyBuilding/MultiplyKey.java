package keyBuilding;

public class MultiplyKey extends SimpleKey implements KeyType{
	
	@Override
	public void generateKey() {
		boolean check=false;
		int Key=2;
		while(!check){
			Key=(int)(Math.random()*13)+3;
			if(GCD(256,Key)==1){
				check=true;
			}
		}
		setKey(Key);
	}
	
	public int GCD(int a, int b) {
		   if (b==0) return a;
		   return GCD(b,a%b);
	}

}
