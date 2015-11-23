import static org.junit.Assert.*;

import org.junit.Test;

import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;
import keyBuilding.MultiplyKey;

public class ShiftMultiplyEncryptionTest {

	@Test
	public void testShiftMulitiplyEncryption() {
		EncryptionAlgorithm<MultiplyKey> tester=new ShiftMultiplyEncryption();
		MultiplyKey k1=new MultiplyKey();
		k1.setKey(3);
		tester.setKey(k1);
		assertEquals(tester.Encrypt("!"),"c");
		assertEquals(tester.Encrypt("$"),"l");
		assertEquals(tester.Decrypt("c"),"!");
		
		
	}

}
