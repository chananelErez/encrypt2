package injection;

import algoBuilder.BuildEncryptor;
import xmlexperimante.MyJAXB;

public class BuildEncryptorInjection {
	
	public BuildEncryptor getBuilder(){
		MyJAXB j =new MyJAXB();
		String fileN="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\zetha.xml";
		j.setFileName(fileN);
		BuildEncryptor b=j.ReadXML();
		
		return b;
	}

}
