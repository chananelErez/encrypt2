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

import algoBuilder.BuildEncryptor;
import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import fileOperation.Analphabet;
import fileOperation.FileEncryptor;
import keyBuilding.SimpleKey;
import listenersEvents.EncryptionEvent;
import observer.EncryptionObserver;
import writingFormat.Fileformat;

public class FileEncryptorTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testEncrtptFile() throws IOException  {
		exception.expect(IOException.class);
		Analphabet.readFile("C:\\abc.txt", StandardCharsets.UTF_8);
	}

	@Test
	public void testNameConvert() {
		BuildEncryptor b=new BuildEncryptor();
		b.setIsDouble(true);
		Fileformat tester=new Fileformat(b);
		
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
		tester.getPub().addEncryptionObserver(ob);
		assertEquals(tester.getPub().getList().size(),1);
		
		BuildEncryptor b=new BuildEncryptor();
		b.setIsDouble(true);
		b.setEDOperation("Encryption");
		b.setAlgorithm("Algorithm");
		b.setFileName("file.txt");
		b.setSourceDirectory("");
		Fileformat fif=new Fileformat(b);
		
		EncryptionEvent event=new EncryptionEvent(fif, 1);
		tester.getPub().notifyObserver(event);
		
		assertEquals("The Encryption of the file \\file.txt was started.\n",
				outContent.toString());
		
		
	}

}
