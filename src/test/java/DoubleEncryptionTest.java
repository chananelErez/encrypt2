import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class DoubleEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		DoubleEncryption tester=new DoubleEncryption(algo);
		tester.setPlainText("ABCDE");
		tester.setKey(1);
		tester.setAnotherKey(2);
		tester.Encrypt();
		assertEquals(tester.getCypher(),"DEFGH");
		
		EncryptionAlgorithm algor=new ShiftMultiplyEncryption();
		DoubleEncryption tester2=new DoubleEncryption(algor);
		tester2.setPlainText("!$%&");
		tester2.setKey(2);
		tester2.setAnotherKey(1);
		tester2.Encrypt();
		assertEquals(tester2.getCypher(),"BHJL");
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		DoubleEncryption tester=new DoubleEncryption(algo);
		tester.setCypher("DEFGH");
		tester.setKey(1);
		tester.setAnotherKey(2);
		tester.Decrypt();
		assertEquals(tester.getPlainText(),"ABCDE");
		
		EncryptionAlgorithm algor=new ShiftMultiplyEncryption();
		DoubleEncryption tester2=new DoubleEncryption(algor);
		tester2.setCypher("BHJL");
		tester2.setKey(2);
		tester2.setAnotherKey(1);
		tester2.Decrypt();
		assertEquals(tester2.getPlainText(),"!$%&");
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
		String k1=FileEncryptor.readFile("C:\\Users\\user\\key1.txt",StandardCharsets.UTF_8);
		String k2=FileEncryptor.readFile("C:\\Users\\user\\key2.txt",StandardCharsets.UTF_8);
		assertEquals(k1,"2");
		assertEquals(k2,"5");
	}

}
