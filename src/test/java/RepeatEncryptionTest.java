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

}
