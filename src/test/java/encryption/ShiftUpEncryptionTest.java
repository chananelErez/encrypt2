package encryption;

import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.SimpleKey;

public class ShiftUpEncryptionTest {

	@Test
	public void testEncrypt() {
		ShiftUpEncryption tester=new ShiftUpEncryption();
		SimpleKey k1=new SimpleKey();
		k1.setKey(3);
		tester.setKey(k1);
		assertEquals(tester.Encrypt("ABCDE"),"DEFGH");
	}

	@Test
	public void testDecrypt() {
		ShiftUpEncryption tester=new ShiftUpEncryption();
		SimpleKey k1=new SimpleKey();
		k1.setKey(3);
		tester.setKey(k1);
		assertEquals(tester.Decrypt("DEFGH"),"ABCDE");
	}

}
