package xmlexperimante;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class JAXB {
	public void EncryptionFromJAXB(){
		try {

			File file = new File("C:\\Users\\Public\\Documents\\openingexperiment\\justfolder\\exec7.xml");
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
		j.EncryptionFromJAXB();
	}
	

	 
	

}