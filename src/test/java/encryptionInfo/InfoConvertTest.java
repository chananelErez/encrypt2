package encryptionInfo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoBuilder.BuildEncryptor;

public class InfoConvertTest {

	@Test
	public void testBuilderToSperate() {
		BuildEncryptor b=new BuildEncryptor();
		b.setAlgorithm("ShiftUpEncryption");
		b.setIsDouble(true);
		b.setFileName("Yelik");
		ArrayList<GeneralInfo> l=InfoConvert.BuilderToSperate(b);
		FileInfo fi= (FileInfo) l.get(0);
		EncryptionKind ek=(EncryptionKind)l.get(1);
		assertEquals(fi.getFileN(), "Yelik");
		assertEquals("ShiftUpEncryption", ek.getAlgorithm());
		
	}

	@Test
	public void testSperateToBuilder() {
		FileInfo fi=new FileInfo();
		fi.setFileN("Yelik");
		EncryptionKind ek=new EncryptionKind();
		ek.setDouble(true);
		ek.setAlgorithm("ShiftUpEncryption");
		EncrOrDecr eord=new EncrOrDecr();
		eord.setEord("Encryption");
		BuildEncryptor b=InfoConvert.SperateToBuilder(fi, ek, eord);
		assertEquals(b.getFileName(), "Yelik");
		assertEquals(b.getIsDouble(), true);
	}

}
