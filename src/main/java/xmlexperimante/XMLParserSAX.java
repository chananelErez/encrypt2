package xmlexperimante;

import java.io.File;
import java.io.IOException;
import java.util.List;
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
 
import org.xml.sax.SAXException;

import algoBuilder.BuildEncryptor;
import algoBuilder.SaxHandler;

public class XMLParserSAX implements XMLreader{
	
	private String fileName;

	public BuildEncryptor ReadXML(){
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SaxHandler handler = new SaxHandler();
			saxParser.parse(new File(fileName), handler);
			List<BuildEncryptor> BuildList = handler.getList();
			return BuildList.get(0);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public void setFileName(String fileN) {
		this.fileName=fileN;
		
	}
}
