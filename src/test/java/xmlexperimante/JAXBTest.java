package xmlexperimante;

import static org.junit.Assert.*;

import org.junit.Test;

import algoBuilder.BuildEncryptor;
import xmlexperimante.JAXB;

public class JAXBTest {

	@Test
	public void testReadXML() {
		JAXB j=new JAXB();
		String fileN="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\exec7.xml";
		BuildEncryptor b=j.ReadXML(fileN);
		assertEquals(b.getAlgorithm(),"ShiftUpEncryption");
		assertEquals(b.getIsDouble(), false);
	}

}
