import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import encryption.invalidEncryptionKeyException;
import fileOperation.FileEncryptor;
import keyBuilding.SimpleKey;

public class SimpleKeyTest {

	@Test
	public void testGenerateKey() {
		SimpleKey tester=new SimpleKey();
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

	/*@Test
	public void testPrintKeyToFile() throws IOException {
		SimpleKey tester=new SimpleKey();
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
		SimpleKey tester=new SimpleKey();
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
		SimpleKey tester=new SimpleKey();
		String num="-5";
		tester.setUser_input(new Scanner(num));
		
		exception.expect(invalidEncryptionKeyException.class);
		tester.setUserKey();
		
		
	}*/

}
