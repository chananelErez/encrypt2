import static org.junit.Assert.*;

import org.junit.Test;

public class RepeatEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm tester=new RepeatEncryption(5);
		tester.setKey(4);
		assertEquals(tester.Encrypt("!#"),"57");
		assertEquals(tester.Encrypt("12"),"EF");
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm tester=new RepeatEncryption(5);
		tester.setKey(4);
		assertEquals(tester.Decrypt("57"),"!#");
		assertEquals(tester.Decrypt("EF"),"12");
	}
	
	@Test
	public void testGetKeyStrength() {
		EncryptionAlgorithm tester=new RepeatEncryption(5);
		tester.setKey(4);
		assertEquals(tester.getKeySrength(),2);
		
		EncryptionAlgorithm tester1=new RepeatEncryption(33);
		tester.setKey(4);
		assertEquals(tester1.getKeySrength(),1);
	}
	

}
