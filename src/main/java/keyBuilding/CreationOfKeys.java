package keyBuilding;

public class CreationOfKeys {
	public static SimpleKey BuildSimple(){
		SimpleKey key=new SimpleKey();
		return key;
	}
	
	public static MultiplyKey BuildMultiply(){
		MultiplyKey key=new MultiplyKey();
		return key;
	}
	
	public static DoubleKey BuildDoubleKeyS(){
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey key=new DoubleKey(key1, key2);
		return key;
	}
	
	public static DoubleKey BuildDoubleKeyM(){
		MultiplyKey key1=new MultiplyKey();
		MultiplyKey key2=new MultiplyKey();
		DoubleKey key=new DoubleKey(key1, key2);
		return key;
	}
	
	public static KeyType Creation(Boolean IsDouble,String Type){
		if (IsDouble){
			if (Type=="SimpleKey"){
				return BuildSimple();
			}else{
				return BuildMultiply();
			}
			
		}else{
			if (Type=="SimpleKey"){
				return BuildDoubleKeyS();
			}else{
				return BuildDoubleKeyM();
			}
		}
	}
	

}
