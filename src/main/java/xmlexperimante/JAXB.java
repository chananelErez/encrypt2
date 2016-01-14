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
			if(encrypt.KeyType=="SimpleKey"){
				SimpleKeyAlgorithmBuilder Bu= new SimpleKeyAlgorithmBuilder();
				Bu.SimpleEncryptorBuilder(encrypt);
			}
			if(encrypt.KeyType=="MultiplyKey"){
				MultiplyKeyAlgorithmBuilder Bu= new MultiplyKeyAlgorithmBuilder();
				Bu.MultiplyEncryptorBuilder(encrypt);
				
			}
			if(encrypt.KeyType=="DoubleKey<SimpleKey>"){
				DoubleSimpleKeyAlgorithmBuilder Bu= new DoubleSimpleKeyAlgorithmBuilder();
				Bu.DoubleSimpleEncryptorBuilder(encrypt);
			}
			if(encrypt.KeyType=="DoubleKey<MultiplyKey>"){
				DoubleMultiplyKeyAlgorithmBuilder Bu= new DoubleMultiplyKeyAlgorithmBuilder();
				Bu.DoubleMultiplyEncryptorBuilder(encrypt);
			}

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		
		

	}
	
	

	 
	

}