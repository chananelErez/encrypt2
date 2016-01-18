package xmlexperimante;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class XmlParserDOM implements XMLreader{
	
	public void ReadXML(String fileN) {
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document;
		try {
			document = builder.parse(new File(fileN));
			//Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();
			 
			//Here comes the root node
			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());
			NodeList nList = document.getElementsByTagName("buildEncryptor");
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
			 Node node = nList.item(temp);
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
			    EncryptByBuilder.Encrypt(encrypt);
			 }
			}
			
			
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		XmlParserDOM prase=new XmlParserDOM();
		prase.ReadXML("C:\\Users\\Public\\Documents"
				+ "\\openingexperiment\\justfolder\\exec7.xml");
	}
	

}
