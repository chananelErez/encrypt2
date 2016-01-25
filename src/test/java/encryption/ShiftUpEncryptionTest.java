package encryption;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import keyBuilding.SimpleKey;

public class ShiftUpEncryptionTest {

	@Test
	public void testEncrypt() {
		ShiftUpEncryption tester=new ShiftUpEncryption();
		SimpleKey k1=new SimpleKey();
		k1.setKey(3);
		tester.setKey(k1);
		assertEquals(tester.Encrypt("ABCDE"),"DEFGH");
	}

	@Test
	public void testDecrypt() {
		ShiftUpEncryption tester=new ShiftUpEncryption();
		SimpleKey k1=new SimpleKey();
		k1.setKey(3);
		tester.setKey(k1);
		assertEquals(tester.Decrypt("DEFGH"),"ABCDE");
	}
	
	@Test
	public void testhashForKey(){
		ShiftUpEncryption tester=new ShiftUpEncryption();
		ArrayList<String> names= new ArrayList<String>();
		String s="Wait";
		names.add(s);
		int k=tester.hashForKey(names);
		assertEquals(k, (17*31+s.hashCode())%256);
		
		
	}

}
