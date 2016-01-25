package injection;

import encryption.DoubleEncryption;
import fileOperation.AsyncDirectoryProcessor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import observer.EncryptionObserver;

public class DirectoryProcessorInjection {
	
	public AsyncDirectoryProcessor<DoubleKey> getDirectoryEncryptor(){
		DoubleEncryptionInjection inj=new DoubleEncryptionInjection();
		DoubleEncryption<SimpleKey> internalAlgo=inj.getDouble();
		
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey k= new DoubleKey(key1,key2);
		internalAlgo.setKey(k);
		
		AsyncDirectoryProcessor<DoubleKey> Code=
				new AsyncDirectoryProcessor<DoubleKey>(internalAlgo);
		
		EncryptionObserver ob=new EncryptionObserver();
		Code.addEncryptionObserver(ob);
	
		return Code;
	}

}
