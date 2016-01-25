package algoBuilder;

import adService.GeneralPublisher;
import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.MultiplyKey;
import writingFormat.Directoryformat;
import writingFormat.Fileformat;

public class DoubleMultiplyKeyAlgorithmBuilder implements AlgorithmBuilder {


	private DoubleKey key;

	private GeneralPublisher pub;
	
	public DoubleMultiplyKeyAlgorithmBuilder(){
		MultiplyKey key1=new MultiplyKey();
		MultiplyKey key2=new MultiplyKey();
		this.key= new DoubleKey(key1,key2);
		
	}
	
	public void DoubleMultiplyEncryptorBuilder(BuildEncryptor encrypt) {
		DoubleEncryption<MultiplyKey> Algo=
				this.DoubleMultiplyAlgoCreation(encrypt.Algorithm);
		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			Fileformat form=new Fileformat(encrypt);
			
			FileEncryptor<DoubleKey> Code =
					new FileEncryptor<DoubleKey>(Algo);

			if (encrypt.EDOperation.equals("Encryption")){
				Code.encrtptFile(form);
			}
			else if (encrypt.EDOperation.equals("Decryption")){
				Code.decryptFile(form);
			}

		}else if(encrypt.FileORDirec.equals("Directory")){
			Directoryformat form=new Directoryformat(encrypt);
			AsyncDirectoryProcessor<DoubleKey> Code =
					new AsyncDirectoryProcessor<DoubleKey>(Algo);
			if (encrypt.EDOperation.equals("Encryption")){
				
				Code.encryptDirectory(form);
			}
			if (encrypt.EDOperation.equals("Decryption")){
				
				Code.decryptDirectory(form);
			}

		}else{
			System.out.print("Wrong input!");
		}

	}
	
	public DoubleEncryption<MultiplyKey> DoubleMultiplyAlgoCreation(String Algorithm){
		if(Algorithm.equals("ShiftMultiplyEncryption")){
			EncryptionAlgorithm<MultiplyKey> intAlgo=new ShiftMultiplyEncryption();
			DoubleEncryption<MultiplyKey> Algo=new DoubleEncryption<MultiplyKey>(intAlgo);
			return Algo;
		} else{
			return null;
		}
	}

	public GeneralPublisher getPub() {
		return pub;
	}

	public void setPub(GeneralPublisher pub) {
		this.pub = pub;
	}
		

}
