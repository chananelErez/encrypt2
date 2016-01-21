package algoBuilder;

import adService.GeneralPublisher;
import encryption.EncryptionAlgorithm;
import encryption.RepeatEncryption;
import encryption.ShiftUpEncryption;
import encryption.XorEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.SimpleKey;
import writingFormat.Directoryformat;
import writingFormat.Fileformat;

public class SimpleKeyAlgorithmBuilder implements AlgorithmBuilder {
	
	private GeneralPublisher pub;
	private Fileformat form;
	
	public SimpleKeyAlgorithmBuilder(){
		
	}
	

	public void SimpleEncryptorBuilder(BuildEncryptor encrypt){
		EncryptionAlgorithm<SimpleKey> Algo=
				this.SimpleAlgoCreation(encrypt.Algorithm, encrypt.Repeat);
		
		SimpleKey key=new SimpleKey();
		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			this.form=new Fileformat(encrypt);
			FileEncryptor<SimpleKey> Code =
					new FileEncryptor<SimpleKey>(Algo);

			if (encrypt.EDOperation.equals("Encryption")){
				Code.encrtptFile(form);
			}
			else if (encrypt.EDOperation.equals("Decryption")){
				Code.decryptFile(form);
			}

		}else if(encrypt.FileORDirec.equals("Directory")){
			AsyncDirectoryProcessor<SimpleKey> Code =
					new AsyncDirectoryProcessor<SimpleKey>(Algo);
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
	
	public EncryptionAlgorithm<SimpleKey> SimpleAlgoCreation(String Algorithm,
			int repeat){
		if(Algorithm.equals("ShiftUpEncryption")){
			EncryptionAlgorithm<SimpleKey> Algo=new ShiftUpEncryption();
			SimpleKey k1=new SimpleKey();
			Algo.setKey(k1);
			return Algo;
		}else if(Algorithm.equals("XorEncryption")){
			EncryptionAlgorithm<SimpleKey> Algo=new XorEncryption();
			SimpleKey k1=new SimpleKey();
			Algo.setKey(k1);
			return Algo;
		}else if(Algorithm.equals("RepeatEncryption")){
			EncryptionAlgorithm<SimpleKey> Algo=new RepeatEncryption(repeat);
			SimpleKey k1=new SimpleKey();
			Algo.setKey(k1);
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