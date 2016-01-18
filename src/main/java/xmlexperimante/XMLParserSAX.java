package xmlexperimante;

import java.io.File;
import java.io.IOException;
import java.util.List;
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
 
import org.xml.sax.SAXException;

public class XMLParserSAX {
	public void ReadXMLBySax(){
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SaxHandler handler = new SaxHandler();
			saxParser.parse(new File("C:\\Users\\Public\\Documents"
					+ "\\openingexperiment\\justfolder\\exec7.xml"), handler);
			List<BuildEncryptor> BuildList = handler.getList();
			for(BuildEncryptor Bu : BuildList)
				EncryptByBuilder.Encrypt(Bu);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args){
		XMLParserSAX x=new XMLParserSAX();
		x.ReadXMLBySax();
	}
	

}
