import static org.junit.Assert.*;

import org.junit.Test;

import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;

public class ShiftMultiplyEncryptionTest {

	@Test
	public void testShiftMulitiplyEncryption() {
		EncryptionAlgorithm tester=new ShiftMultiplyEncryption();
		tester.setKey(3);
		assertEquals(tester.Encrypt("!"),"c");
		assertEquals(tester.Encrypt("$"),"l");
		assertEquals(tester.Decrypt("c"),"!");
		
		
	}

}
