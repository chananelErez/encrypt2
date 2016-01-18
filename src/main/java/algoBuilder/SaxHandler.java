package algoBuilder;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {

	private List<BuildEncryptor> BuildList=null;
	private BuildEncryptor Bu=null;
	
	
	public List<BuildEncryptor> getList(){
		return BuildList;
		}
	
	boolean bEDOperation;
	boolean bFileORDirec;
	boolean bSourceDirectory;
	boolean bKeyPath;
	boolean bFileName;
	boolean bAlgorithm;
	boolean bKeyType;
	boolean bIsDouble;
	boolean bRepeat;
	
    @Override
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {

		if(qName.equalsIgnoreCase("BuildEncryptor")){
           
            //initialize buildEncryptor object 
            Bu = new BuildEncryptor();
            //initialize list
            if (BuildList == null)
                BuildList = new ArrayList<>();
        }else if (qName.equalsIgnoreCase("EDOperation")) {
            bEDOperation = true;
        } else if (qName.equalsIgnoreCase("FileORDirec")) {
            bFileORDirec = true;
        } else if (qName.equalsIgnoreCase("SourceDirectory")) {
            bSourceDirectory = true;
        } else if (qName.equalsIgnoreCase("keyPath")) {
            bKeyPath = true;
        }else if (qName.equalsIgnoreCase("FileName")) {
            bFileName = true;
        } else if (qName.equalsIgnoreCase("Algorithm")) {
            bAlgorithm = true;
        } else if (qName.equalsIgnoreCase("KeyType")) {
            bKeyType = true;
        } else if (qName.equalsIgnoreCase("IsDouble")) {
            bIsDouble = true;
        } else if(qName.equalsIgnoreCase("Repeat")){
        	bRepeat=true;
        }

	}
    @Override
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
		if (qName.equalsIgnoreCase("BuildEncryptor")) {
            BuildList.add(Bu);
        }
	}
    @Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bEDOperation) {
			Bu.setEDOperation(new String(ch, start, length));
			bEDOperation = false;
		}

		if (bFileORDirec) {
			Bu.setFileORDirec(new String(ch,start,length));
			bFileORDirec = false;
		}

		if (bSourceDirectory) {
			Bu.setSourceDirectory(new String(ch, start, length));
			bSourceDirectory = false;
		}

		if (bKeyPath) {
			Bu.setKeyPath(new String(ch, start, length));
			bKeyPath = false;
		}
		if (bFileName) {
			Bu.setFileName(new String(ch, start, length));
			bFileName = false;
		}

		if (bAlgorithm) {
			Bu.setAlgorithm(new String(ch,start,length));
			bAlgorithm = false;
		}

		if (bKeyType) {
			Bu.setKeyType(new String(ch, start, length));
			bKeyType = false;
		}

		if (bIsDouble) {
			Bu.setIsDouble(Boolean.getBoolean((new String(ch, start, length))));
			bIsDouble = false;
		}if(bRepeat){
			Bu.setRepeat(Integer.parseInt(new String(ch,start,length)));
			bRepeat=false;
		}

	 }

}