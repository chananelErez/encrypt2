package dataFromUser;

import algoBuilder.BuildEncryptor;
import xmlexperimante.XMLValidation;

public class XmlEncryption {
	
	public BuildEncryptor GetData(XmlInfo inf){
		if(XMLValidation.validateXMLSchema(inf.getXsdPath(), inf.getXmlPath())){
			inf.getReader().setFileName(inf.getXmlPath());
			BuildEncryptor encrypt=inf.getReader().ReadXML();
			return encrypt;
		}
		return null;
	}
	

}
