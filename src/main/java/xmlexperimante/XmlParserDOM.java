package xmlexperimante;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import algoBuilder.BuildEncryptor;
import javax.xml.parsers.*;
import java.io.*;

public class XmlParserDOM implements XMLreader{
	
	private String fileName;

	public BuildEncryptor ReadXML() {
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document;
		try {
			document = builder.parse(new File(fileName));
			//Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();
			 
			//Here comes the root node
			NodeList nList = document.getElementsByTagName("buildEncryptor");
			Node node = nList.item(0);
			BuildEncryptor encrypt =new BuildEncryptor();
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				//Print each employee's detail
				Element eElement = (Element) node;
				encrypt.setAlgorithm(eElement.
						getElementsByTagName("algorithm").item(0).
						getTextContent());
				encrypt.setEDOperation(eElement.
						getElementsByTagName("EDOperation").item(0).
						getTextContent());
				encrypt.setFileName(eElement.
						getElementsByTagName("fileName").item(0).
						getTextContent());
				encrypt.setFileORDirec(eElement.
						getElementsByTagName("fileORDirec").item(0)
						.getTextContent());
				encrypt.setIsDouble(Boolean.getBoolean(
						eElement.getElementsByTagName("isDouble")
						.item(0).getTextContent()));
				encrypt.setKeyPath(eElement.
						getElementsByTagName("keyPath").item(0).
						getTextContent());
				encrypt.setKeyType(eElement.
						getElementsByTagName("keyType").item(0).
						getTextContent());
				encrypt.setRepeat(Integer.parseInt(eElement.
						getElementsByTagName("repeat").item(0).
						getTextContent()));
				encrypt.setSourceDirectory(eElement.getElementsByTagName("sourceDirectory").item(0).getTextContent());
				return encrypt;
			}
			 
			
			
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void setFileName(String fileN) {
		this.fileName=fileN;
		
	}


}
