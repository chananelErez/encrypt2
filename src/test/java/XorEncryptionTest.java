import static org.junit.Assert.*;

import org.junit.Test;

import encryption.EncryptionAlgorithm;
import encryption.XorEncryption;
import keyBuilding.SimpleKey;

public class XorEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm<SimpleKey> tester=new XorEncryption();
		SimpleKey k1=new SimpleKey();
		k1.setKey(23);
		tester.setKey(k1);
		assertEquals(tester.Encrypt("89"),"/.");
		assertEquals(tester.Encrypt("EF"),"RQ");
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm<SimpleKey> tester=new XorEncryption();
		SimpleKey k1=new SimpleKey();
		k1.setKey(23);
		tester.setKey(k1);
		assertEquals(tester.Decrypt("89"),"/.");
		assertEquals(tester.Decrypt("EF"),"RQ");
	}

}
