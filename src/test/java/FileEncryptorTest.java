import static org.junit.Assert.*;

import org.junit.Test;

public class FileEncryptorTest {

	@Test
	public void testEncrtptFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testDecryptFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testNameConvert() {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		FileEncryptor tester= new FileEncryptor(algo);
		assertEquals(tester.NameConvert("try.txt", "E"),"try_encrypted.txt");
		assertEquals(tester.NameConvert("try.txt", "D"),"try_decrypted.txt");
	}

}
