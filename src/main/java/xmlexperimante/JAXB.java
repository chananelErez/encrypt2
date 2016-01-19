package xmlexperimante;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import algoBuilder.BuildEncryptor;


public class JAXB implements XMLreader{
	public BuildEncryptor ReadXML(String fileN){
		try {

			File file = new File(fileN);
			JAXBContext jaxbContext = JAXBContext.newInstance(BuildEncryptor.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			BuildEncryptor encrypt = (BuildEncryptor) jaxbUnmarshaller.unmarshal(file);
			return encrypt;

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		
		return null;

	}

}