package xmlexperimante;

import static org.junit.Assert.*;

import org.junit.Test;

import algoBuilder.BuildEncryptor;
import xmlexperimante.MyJAXB;

public class JAXBTest {

	@Test
	public void testReadXML() {
		MyJAXB j=new MyJAXB();
		String fileN="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\exec7.xml";
		j.setFileName(fileN);
		BuildEncryptor b=j.ReadXML();
		assertEquals(b.getAlgorithm(),"ShiftUpEncryption");
		assertEquals(b.getIsDouble(), false);
	}

}
