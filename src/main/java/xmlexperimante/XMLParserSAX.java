package xmlexperimante;

import java.io.File;
import java.io.IOException;
import java.util.List;
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
 
import org.xml.sax.SAXException;

import algoBuilder.BuildEncryptor;
import algoBuilder.EncryptByBuilder;
import algoBuilder.SaxHandler;

public class XMLParserSAX implements XMLreader{
	public void ReadXML(String fileN){
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SaxHandler handler = new SaxHandler();
			saxParser.parse(new File(fileN), handler);
			List<BuildEncryptor> BuildList = handler.getList();
			for(BuildEncryptor Bu : BuildList)
				EncryptByBuilder.Encrypt(Bu);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args){
		XMLParserSAX x=new XMLParserSAX();
		x.ReadXML("C:\\Users\\Public\\Documents"
				+ "\\openingexperiment\\justfolder\\exec7.xml");
	}
	

}
