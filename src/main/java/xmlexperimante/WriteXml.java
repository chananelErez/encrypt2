package xmlexperimante;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import algoBuilder.BuildEncryptor;


	

public class WriteXml {
	
	public static void WriteXmlWithJaxb(BuildEncryptor encrypt,String output) {

		  try {

			File file = new File(output);
			JAXBContext jaxbContext = JAXBContext.newInstance(BuildEncryptor.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(encrypt, file);
			jaxbMarshaller.marshal(encrypt, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }

		}
}
