import static org.junit.Assert.*;

import org.junit.Test;

import encryption.EncryptionAlgorithm;
import encryption.RepeatEncryption;
import keyBuilding.SimpleKey;

public class RepeatEncryptionTest {

	@Test
	public void testEncrypt() {
		EncryptionAlgorithm<SimpleKey> tester=new RepeatEncryption(5);
		SimpleKey k1=new SimpleKey();
		k1.setKey(4);
		tester.setKey(k1);
		assertEquals(tester.Encrypt("!#"),"57");
		assertEquals(tester.Encrypt("12"),"EF");
	}

	@Test
	public void testDecrypt() {
		EncryptionAlgorithm<SimpleKey> tester=new RepeatEncryption(5);
		SimpleKey k1=new SimpleKey();
		k1.setKey(4);
		tester.setKey(k1);
		assertEquals(tester.Decrypt("57"),"!#");
		assertEquals(tester.Decrypt("EF"),"12");
	}
	
	@Test
	public void testGetKeyStrength() {
		EncryptionAlgorithm<SimpleKey> tester=new RepeatEncryption(5);
		SimpleKey k1=new SimpleKey();
		k1.setKey(4);
		tester.setKey(k1);
		assertEquals(tester.getKeySrength(),2);
		
		EncryptionAlgorithm<SimpleKey> tester1=new RepeatEncryption(33);
		tester.setKey(k1);
		assertEquals(tester1.getKeySrength(),1);
	}
	

}
