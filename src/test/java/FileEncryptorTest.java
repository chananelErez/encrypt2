import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import fileOperation.FileEncryptor;
import keyBuilding.SimpleKey;
import listenersEvents.EncryptionEvent;
import observer.EncryptionObserver;

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
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		FileEncryptor<SimpleKey> tester= new FileEncryptor<SimpleKey>(algo);
		assertEquals(tester.NameConvert("try.txt", "E"),"try_encrypted.txt");
		assertEquals(tester.NameConvert("try.txt", "D"),"try_decrypted.txt");
	}
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	@Test
	public void testNotifyObserver(){
		EncryptionAlgorithm<SimpleKey> algo=new ShiftUpEncryption();
		FileEncryptor<SimpleKey> tester= new FileEncryptor<SimpleKey>(algo);
		EncryptionObserver ob=new EncryptionObserver();
		tester.addEncryptionObserver(ob);
		assertEquals(tester.getList().size(),1);
		
		EncryptionEvent event=new EncryptionEvent("Encryption", "file.txt",
				"Algorithm", "out", 1);
		tester.notifyObserver(event);
		
		assertEquals("The Encryption of the file file.txt was started.\n",
				outContent.toString());
		
		
	}

}
