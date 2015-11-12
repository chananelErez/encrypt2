import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import encryption.EncryptionAlgorithm;
import encryption.GeneralEncryption;
import encryption.invalidEncryptionKeyException;
import fileOperation.FileEncryptor;

public class GeneralEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm tester=new GeneralEncryption();
		tester.setKey(3);
		assertEquals(tester.Encrypt("ABCDE"),"DEFGH");
		
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm tester=new GeneralEncryption();
		tester.setKey(3);
		assertEquals(tester.Decrypt("DEFGH"),"ABCDE");
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
		String k1=FileEncryptor.readFile("C:\\Users\\user\\key.txt",
				StandardCharsets.UTF_8);
		assertEquals(k1,"2");
	}
	
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSetUserKey1() throws invalidEncryptionKeyException{
		EncryptionAlgorithm tester=new GeneralEncryption();
		String num="five";
		tester.setUser_input(new Scanner(num));
		
		exception.expect(invalidEncryptionKeyException.class);
		tester.setUserKey();
		
		num="-5";
		tester.setUser_input(new Scanner(num));
		exception.expect(invalidEncryptionKeyException.class);
		tester.setUserKey();
		
		
	}
	
	@Test
	public void testSetUserKey2() throws invalidEncryptionKeyException{
		EncryptionAlgorithm tester=new GeneralEncryption();
		String num="-5";
		tester.setUser_input(new Scanner(num));
		
		exception.expect(invalidEncryptionKeyException.class);
		tester.setUserKey();
		
		
	}
	

}
