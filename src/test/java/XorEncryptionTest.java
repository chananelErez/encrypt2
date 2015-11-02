import static org.junit.Assert.*;

import org.junit.Test;

public class XorEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm tester=new XorEncryption();
		tester.setKey(23);
		assertEquals(tester.Encrypt("89"),"/.");
		assertEquals(tester.Encrypt("EF"),"RQ");
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm tester=new XorEncryption();
		tester.setKey(23);
		assertEquals(tester.Decrypt("89"),"/.");
		assertEquals(tester.Decrypt("EF"),"RQ");
	}

}
