package xmlexperimante;

import static org.junit.Assert.*;

import org.junit.Test;

import algoBuilder.BuildEncryptor;

public class XMLParserSAXTest {

	@Test
	public void testReadXML() {
		XMLParserSAX j=new XMLParserSAX();
		String fileN="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\exec7.xml";
		BuildEncryptor b=j.ReadXML(fileN);
		assertEquals(b.getAlgorithm(),"ShiftUpEncryption");
		assertEquals(b.getIsDouble(), false);
	}

}
