import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class GeneralEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm tester=new GeneralEncryption();
		tester.setPlainText("ABCDE");
		tester.setKey(3);
		tester.Encrypt();
		assertEquals(tester.getCypher(),"DEFGH");
		
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm tester=new GeneralEncryption();
		tester.setCypher("DEFGH");
		tester.setKey(3);
		tester.Decrypt();
		assertEquals(tester.getPlainText(),"ABCDE");
	}

	@Test
	public void testGenerateKey() {
		EncryptionAlgorithm tester=new GeneralEncryption();
		tester.generateKey();
		boolean check=false;
		if(tester.getKey()>0){
			check=true;
		}
		assertTrue(check);
		
	    check=false;
		if(tester.getKey()<20){
			check=true;
		}
		assertTrue(check);
	}

	@Test
	public void testPrintKeyToFile() throws IOException {
		EncryptionAlgorithm tester=new GeneralEncryption();
		tester.setKey(2);
		tester.printKeyToFile();
		String k1=FileEncryptor.readFile("C:\\Users\\user\\key.txt",StandardCharsets.UTF_8);
		assertEquals(k1,"2");
	}

}
