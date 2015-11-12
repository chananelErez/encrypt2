import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import fileOperation.FileEncryptor;

public class FileEncryptorTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testEncrtptFile() throws IOException  {
		exception.expect(IOException.class);
		FileEncryptor.readFile("C:\\abc.txt", StandardCharsets.UTF_8);
	}

	@Test
	public void testNameConvert() {
		EncryptionAlgorithm algo=new ShiftUpEncryption();
		FileEncryptor tester= new FileEncryptor(algo);
		assertEquals(tester.NameConvert("try.txt", "E"),"try_encrypted.txt");
		assertEquals(tester.NameConvert("try.txt", "D"),"try_decrypted.txt");
	}

}
