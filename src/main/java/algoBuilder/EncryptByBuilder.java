package algoBuilder;

import adService.GeneralPublisher;

public class EncryptByBuilder {
	
	public static void Encrypt(BuildEncryptor encrypt
			,GeneralPublisher pub){
		String s=encrypt.KeyType;
		
		if(s.equals("SimpleKey")){
			SimpleKeyAlgorithmBuilder Bu=
					new SimpleKeyAlgorithmBuilder(pub);
			Bu.SimpleEncryptorBuilder(encrypt);
		}
		if(s.equals("MultiplyKey")){
			MultiplyKeyAlgorithmBuilder Bu=
					new MultiplyKeyAlgorithmBuilder(pub);
			Bu.MultiplyEncryptorBuilder(encrypt);
			
		}
		if(s.equals("DoubleKey<SimpleKey>")){
			DoubleSimpleKeyAlgorithmBuilder Bu=
					new DoubleSimpleKeyAlgorithmBuilder(pub);
			Bu.DoubleSimpleEncryptorBuilder(encrypt);
		}
		if(s.equals("DoubleKey<MultiplyKey>")){
			DoubleMultiplyKeyAlgorithmBuilder Bu= 
					new DoubleMultiplyKeyAlgorithmBuilder(pub);
			Bu.DoubleMultiplyEncryptorBuilder(encrypt);
		}
	}

}
