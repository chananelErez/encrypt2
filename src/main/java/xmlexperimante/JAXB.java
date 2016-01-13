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
			if(encrypt.EDOperation=="Encryption"){
				this.EncryptXML(encrypt);
			}
			if(encrypt.EDOperation=="Decryption"){
				this.DecryptXML(encrypt);
			}

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		
		

	}
	public void EncryptXML(BuildEncryptor encrypt){
		if(encrypt.FileORDirec=="File"){
			this.EncryptFile(encrypt);
		}
		if(encrypt.FileORDirec=="Directory"){
			this.EncryptDirectory(encrypt);
		}
		
	}
	
	private void EncryptFile(BuildEncryptor encrypt) {
		
		
	}
	private void EncryptDirectory(BuildEncryptor encrypt) {
		// TODO Auto-generated method stub
		
	}
	public void DecryptXML(BuildEncryptor encrypt){
		if(encrypt.FileORDirec=="File"){
			this.DecryptFile(encrypt);
		}
		if(encrypt.FileORDirec=="Directory"){
			this.DecryptDirectory(encrypt);
		}
		
	}
	
	private void DecryptDirectory(BuildEncryptor encrypt) {
		// TODO Auto-generated method stub
		
	}
	private void DecryptFile(BuildEncryptor encrypt) {
		// TODO Auto-generated method stub
		
	}
	 
	

}