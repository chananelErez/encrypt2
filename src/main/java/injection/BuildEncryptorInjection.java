package injection;

import algoBuilder.BuildEncryptor;
import xmlexperimante.JAXB;

public class BuildEncryptorInjection {
	
	public BuildEncryptor getBuilder(){
		JAXB j =new JAXB();
		String fileN="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\zetha.xml";
		BuildEncryptor b=j.ReadXML(fileN);
		
		return b;
	}

}
