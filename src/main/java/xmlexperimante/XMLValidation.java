package xmlexperimante;

import java.io.File;
import java.io.IOException;
 
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
 
import org.xml.sax.SAXException;

public class XMLValidation {
	public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        
        try {
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
	
	public static void main(String[] args) {

		String exerc="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\exec7.xml";
		String sch="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\BuildEncryptorValidation.xsd";
		System.out.println("exerc.xml validates against BuildEncryptorValidation.xsd? "
				+validateXMLSchema(sch,exerc));


	}
	

}
