package algoBuilder;

import adService.GeneralPublisher;
import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.MultiplyKey;
import writingFormat.Directoryformat;
import writingFormat.Fileformat;

public class MultiplyKeyAlgorithmBuilder implements AlgorithmBuilder {
	private MultiplyKey key;
	private GeneralPublisher pub;
	private Fileformat form;
	
	
	public MultiplyKeyAlgorithmBuilder(){
		this.key=new MultiplyKey();
	}

	public void MultiplyEncryptorBuilder(BuildEncryptor encrypt) {
		
		EncryptionAlgorithm<MultiplyKey> Algo=
				this.MultiplyAlgoCreation(encrypt.Algorithm);

		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			this.form=new Fileformat(encrypt);

			FileEncryptor<MultiplyKey> Code =
					new FileEncryptor<MultiplyKey>(Algo);

			if (encrypt.EDOperation.equals("Encryption")){
				Code.encrtptFile(form);
			}
			else if (encrypt.EDOperation.equals("Decryption")){
				Code.decryptFile(form);
			}

		}else if(encrypt.FileORDirec.equals("Directory")){
			AsyncDirectoryProcessor<MultiplyKey> Code =
					new AsyncDirectoryProcessor<MultiplyKey>(Algo);
			Directoryformat form=new Directoryformat(encrypt);

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
	
	public EncryptionAlgorithm<MultiplyKey> MultiplyAlgoCreation(String Algorithm){
		if(Algorithm.equals("ShiftMultiplyEncryption")){
			EncryptionAlgorithm<MultiplyKey> Algo=new ShiftMultiplyEncryption();
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
