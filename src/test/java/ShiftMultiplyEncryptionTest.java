import static org.junit.Assert.*;

import org.junit.Test;

public class ShiftMultiplyEncryptionTest {

	@Test
	public void testShiftMulitiplyEncryption() {
		EncryptionAlgorithm tester=new ShiftMultiplyEncryption();
		tester.setPlainText("!");
		tester.setKey(3);
		assertEquals(tester.getEncryptMethod().Operate(4, 5),20);
		
		tester.Encrypt();
		assertEquals(tester.getCipher(),"c");
		tester.setPlainText("$");
		tester.Encrypt();
		assertEquals(tester.getCipher(),"l");
		
		assertEquals(tester.getDecryptMethod().Operate(200, 5),40);
		tester.setCipher("c");
		tester.setKey(3);
		tester.Decrypt();
		assertEquals(tester.getPlainText(),"!");
		
		
	}

}
