import static org.junit.Assert.*;

import org.junit.Test;

import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;
import encryption.ShiftUpEncryption;
import keyBuilding.DoubleKey;
import keyBuilding.MultiplyKey;
import keyBuilding.SimpleKey;

public class DoubleEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		DoubleEncryption<SimpleKey> tester=new DoubleEncryption<SimpleKey>(algo);
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		k.setKey(1, 2);
		tester.setKey(k);
		assertEquals(tester.Encrypt("ABCDE"),"DEFGH");
		
		EncryptionAlgorithm<MultiplyKey> algor=new ShiftMultiplyEncryption();
		DoubleEncryption<MultiplyKey> tester2=new DoubleEncryption<MultiplyKey>(algor);
		MultiplyKey key11=new MultiplyKey();
		MultiplyKey key21=new MultiplyKey();
		DoubleKey<MultiplyKey> k1= new DoubleKey<MultiplyKey>(key11,key21);
		k1.setKey(3, 5);
		tester2.setKey(k1);
		assertEquals(tester2.Encrypt("'()*"),"IXgv");
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		DoubleEncryption<SimpleKey> tester=new DoubleEncryption<SimpleKey>(algo);
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		k.setKey(1, 2);
		tester.setKey(k);
		assertEquals(tester.Decrypt("DEFGH"),"ABCDE");
		
		EncryptionAlgorithm<MultiplyKey> algor=new ShiftMultiplyEncryption();
		DoubleEncryption<MultiplyKey> tester2=new DoubleEncryption<MultiplyKey>(algor);
		MultiplyKey key11=new MultiplyKey();
		MultiplyKey key21=new MultiplyKey();
		DoubleKey<MultiplyKey> k1= new DoubleKey<MultiplyKey>(key11,key21);
		k1.setKey(3,5);
		tester2.setKey(k1);
		assertEquals(tester2.Decrypt("IXgv"),"'()*");
	}
/*
	@Test
	public void testGenerateKey() {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		DoubleEncryption tester=new DoubleEncryption(algo);
		tester.generateKey();
		boolean check=false;
		if(tester.getKey()>0 && tester.getAnotherKey()>0){
			check=true;
		}
		assertTrue(check);
		
	    check=false;
		if(tester.getKey()<20 && tester.getAnotherKey()<20){
			check=true;
		}
		assertTrue(check);
	}

	@Test
	public void testPrintKeyToFile() throws IOException {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		DoubleEncryption tester=new DoubleEncryption(algo);
		tester.setAnotherKey(5);
		tester.setKey(2);
		tester.printKeyToFile();
		String k1=FileEncryptor.readFile("C:\\Users\\user\\key1.txt",StandardCharsets.UTF_8);
		String k2=FileEncryptor.readFile("C:\\Users\\user\\key2.txt",StandardCharsets.UTF_8);
		assertEquals(k1,"2");
		assertEquals(k2,"5");
	}*/

}
