package injection;

import encryption.DoubleEncryption;
import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import observer.EncryptionObserver;

public class FileProcessorInjection {
	
	public FileEncryptor<DoubleKey<SimpleKey>> getFileEncryptor(){
		DoubleEncryptionInjection inj=new DoubleEncryptionInjection();
		DoubleEncryption<SimpleKey> internalAlgo=inj.getDouble();
		
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		internalAlgo.setKey(k);
		
		FileEncryptor<DoubleKey<SimpleKey>> Code=
				new FileEncryptor<DoubleKey<SimpleKey>>(internalAlgo);
		
		EncryptionObserver ob=new EncryptionObserver();
		Code.addEncryptionObserver(ob);
		
		return Code;
	}

}
