package dataFromUser;

import xmlexperimante.XMLreader;

public class XmlInfo  {
	
	private String xmlPath;
	private String xsdPath;
	private XMLreader reader;
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public String getXsdPath() {
		return xsdPath;
	}
	public void setXsdPath(String xsdPath) {
		this.xsdPath = xsdPath;
	}
	public XMLreader getReader() {
		return reader;
	}
	public void setReader(XMLreader reader) {
		this.reader = reader;
	}
	

}
