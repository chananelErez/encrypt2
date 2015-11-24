package mathOperation;

import keyBuilding.MultiplyKey;

public class MyModDivision<E> extends MathOperation<MultiplyKey> {

	private int inver;
	
	@Override
	public int Operate(int a) {
		
		return (a*inver)%256;
	}
	
	@Override
	public void setKey(MultiplyKey key){
		super.setKey(key);
		int k=key.getKey();
		for(int i=0;i<256;i++){
			if((k*i)%256==1){
				inver=i;
			}
	    }
	}
}
