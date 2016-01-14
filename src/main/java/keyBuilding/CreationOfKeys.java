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
	
	public static DoubleKey<SimpleKey> BuildDoubleKeyS(){
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> key=new DoubleKey<SimpleKey>(key1, key2);
		return key;
	}
	
	public static DoubleKey<MultiplyKey> BuildDoubleKeyM(){
		MultiplyKey key1=new MultiplyKey();
		MultiplyKey key2=new MultiplyKey();
		DoubleKey<MultiplyKey> key=new DoubleKey<MultiplyKey>(key1, key2);
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
