package algoBuilder;



public class EncryptByBuilder {
	
	public static void Encrypt(BuildEncryptor encrypt){
		String s=encrypt.KeyType;
		
		if(s.equals("SimpleKey")){
			SimpleKeyAlgorithmBuilder Bu=
					new SimpleKeyAlgorithmBuilder();
			Bu.SimpleEncryptorBuilder(encrypt);
		}
		if(s.equals("MultiplyKey")){
			MultiplyKeyAlgorithmBuilder Bu=
					new MultiplyKeyAlgorithmBuilder();
			Bu.MultiplyEncryptorBuilder(encrypt);
			
		}
		if(s.equals("DoubleKey<SimpleKey>")){
			DoubleSimpleKeyAlgorithmBuilder Bu=
					new DoubleSimpleKeyAlgorithmBuilder();
			Bu.DoubleSimpleEncryptorBuilder(encrypt);
		}
		if(s.equals("DoubleKey<MultiplyKey>")){
			DoubleMultiplyKeyAlgorithmBuilder Bu= 
					new DoubleMultiplyKeyAlgorithmBuilder();
			Bu.DoubleMultiplyEncryptorBuilder(encrypt);
		}
	}

}
