import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class DoubleEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		DoubleEncryption tester=new DoubleEncryption(algo);
		tester.setKey(1);
		tester.setAnotherKey(2);
		assertEquals(tester.Encrypt("ABCDE"),"DEFGH");
		
		EncryptionAlgorithm algor=new ShiftMultiplyEncryption();
		DoubleEncryption tester2=new DoubleEncryption(algor);
		tester2.setKey(2);
		tester2.setAnotherKey(1);
		assertEquals(tester2.Encrypt("!$%&"),"BHJL");
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		DoubleEncryption tester=new DoubleEncryption(algo);
		tester.setKey(1);
		tester.setAnotherKey(2);
		assertEquals(tester.Decrypt("DEFGH"),"ABCDE");
		
		EncryptionAlgorithm algor=new ShiftMultiplyEncryption();
		DoubleEncryption tester2=new DoubleEncryption(algor);
		tester2.setKey(2);
		tester2.setAnotherKey(1);
		assertEquals(tester2.Decrypt("BHJL"),"!$%&");
	}

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
		String k1=FileEncryptor.readFile("C:\\key1.txt",StandardCharsets.UTF_8);
		String k2=FileEncryptor.readFile("C:\\key2.txt",StandardCharsets.UTF_8);
		assertEquals(k1,"2");
		assertEquals(k2,"5");
	}

}
