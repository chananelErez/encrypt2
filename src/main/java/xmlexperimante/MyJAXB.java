package xmlexperimante;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import algoBuilder.BuildEncryptor;


public class MyJAXB implements XMLreader{
	private String fileName;
	
	
	public BuildEncryptor ReadXML(){
		try {

			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(BuildEncryptor.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			BuildEncryptor encrypt = (BuildEncryptor) jaxbUnmarshaller.unmarshal(file);
			return encrypt;

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		
		return null;

	}

	@Inject
	@Override
	public void setFileName(@Named("specific") String fileN) {
		this.fileName=fileN;
		
	}
	
	

	
}

