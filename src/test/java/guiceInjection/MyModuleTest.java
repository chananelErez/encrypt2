package guiceInjection;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import encryption.DoubleEncryption;
import fileOperation.AsyncDirectoryProcessor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;

public class MyModuleTest {

	@Test
	public void test() {
		Injector injector = Guice.createInjector( new MyModule() );
		DoubleEncryption<SimpleKey> d=
				injector.getInstance(Key.get(new TypeLiteral<DoubleEncryption<SimpleKey>>(){}));
		assertEquals("Double Shift Up Encryption Encryption", d.toString());
	}
	
	@Test
	public void anotherTest() {
		Injector injector = Guice.createInjector(new MyModule());
		DoubleKey k=injector.getInstance(DoubleKey.class);
		k.setKey(5, 4);
	}
	
	@Test
	public void oneMoreTest() {
		Injector injector = Guice.createInjector(new MyModule());
		AsyncDirectoryProcessor<DoubleKey> Code=
				injector.getInstance(Key.get(new TypeLiteral<AsyncDirectoryProcessor<DoubleKey>>(){}));
		assertEquals("Double Shift Up Encryption Encryption", Code.Algo.toString());
		assertEquals(Code.getObserversList().size() , 1);
	}
	

}
