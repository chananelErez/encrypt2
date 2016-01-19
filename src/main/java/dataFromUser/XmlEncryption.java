package dataFromUser;

import algoBuilder.BuildEncryptor;
import xmlexperimante.XMLValidation;

public class XmlEncryption {
	
	public BuildEncryptor GetData(XmlInfo inf){
		if(XMLValidation.validateXMLSchema(inf.getXsdPath(), inf.getXmlPath())){
			BuildEncryptor encrypt=inf.getReader().ReadXML(inf.getXmlPath());
			return encrypt;
		}
		return null;
	}
	

}
