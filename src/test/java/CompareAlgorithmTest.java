import static org.junit.Assert.*;

import org.junit.Test;

import encryption.CompareAlgorithm;
import encryption.EncryptionAlgorithm;
import encryption.RepeatEncryption;
import encryption.ShiftUpEncryption;
import keyBuilding.SimpleKey;

public class CompareAlgorithmTest {

	@Test
	public void testComparator() {
		CompareAlgorithm comp=new CompareAlgorithm();
		EncryptionAlgorithm<SimpleKey> tester1= new ShiftUpEncryption();
		EncryptionAlgorithm<SimpleKey> tester2= new RepeatEncryption(3);
		EncryptionAlgorithm<SimpleKey> tester3= new RepeatEncryption(30);
		
		assertEquals(comp.compare(tester1, tester2),1);
		assertEquals(comp.compare(tester3, tester2),-1);
		
	}

}
