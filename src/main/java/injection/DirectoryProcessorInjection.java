package injection;

import encryption.DoubleEncryption;
import fileOperation.AsyncDirectoryProcessor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import observer.EncryptionObserver;

public class DirectoryProcessorInjection {
	
	public AsyncDirectoryProcessor<DoubleKey<SimpleKey>> getDirectoryEncryptor(){
		DoubleEncryptionInjection inj=new DoubleEncryptionInjection();
		DoubleEncryption<SimpleKey> internalAlgo=inj.getDouble();
		
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		internalAlgo.setKey(k);
		
		AsyncDirectoryProcessor<DoubleKey<SimpleKey>> Code=
				new AsyncDirectoryProcessor<DoubleKey<SimpleKey>>(internalAlgo);
		
		EncryptionObserver ob=new EncryptionObserver();
		Code.addEncryptionObserver(ob);
	
		return Code;
	}

}
