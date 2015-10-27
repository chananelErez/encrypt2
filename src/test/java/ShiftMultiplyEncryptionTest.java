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
		assertEquals(tester.getCypher(),"c");
		tester.setPlainText("$");
		tester.Encrypt();
		assertEquals(tester.getCypher(),"l");
		
		assertEquals(tester.getDecryptMethod().Operate(200, 5),40);
		tester.setCypher("c");
		tester.setKey(3);
		tester.Decrypt();
		assertEquals(tester.getPlainText(),"!");
		
		
	}

}
