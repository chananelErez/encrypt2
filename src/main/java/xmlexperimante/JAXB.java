package xmlexperimante;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import algoBuilder.BuildEncryptor;
import algoBuilder.EncryptByBuilder;


public class JAXB implements XMLreader{
	public void ReadXML(String fileN){
		try {

			File file = new File(fileN);
			JAXBContext jaxbContext = JAXBContext.newInstance(BuildEncryptor.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			BuildEncryptor encrypt = (BuildEncryptor) jaxbUnmarshaller.unmarshal(file);
			EncryptByBuilder.Encrypt(encrypt);

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		
		

	}
	public static void main(String[] Args ){
		JAXB j=new JAXB();
		j.ReadXML("C:\\Users\\Public\\Documents"
				+ "\\openingexperiment\\justfolder\\exec7.xml");
	}
	

	 
	

}