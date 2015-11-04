import static org.junit.Assert.*;

import org.junit.Test;

public class CompareAlgorithmTest {

	@Test
	public void testComparator() {
		CompareAlgorithm comp=new CompareAlgorithm();
		EncryptionAlgorithm tester1= new ShiftUpEncryption();
		EncryptionAlgorithm tester2= new RepeatEncryption(3);
		EncryptionAlgorithm tester3= new RepeatEncryption(30);
		
		assertEquals(comp.Comparator(tester1, tester2),tester1);
		assertEquals(comp.Comparator(tester3, tester2),tester2);
		
	}

}
