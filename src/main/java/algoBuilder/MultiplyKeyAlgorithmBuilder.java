package algoBuilder;

import adService.FilePublisher;
import adService.GeneralPublisher;
import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.MultiplyKey;
import writingFormat.Fileformat;

public class MultiplyKeyAlgorithmBuilder implements AlgorithmBuilder {
	private MultiplyKey key;
	private GeneralPublisher pub;
	private Fileformat form;
	
	
	public MultiplyKeyAlgorithmBuilder(GeneralPublisher pub){
		this.key=new MultiplyKey();
		this.pub=pub;
	}

	public void MultiplyEncryptorBuilder(BuildEncryptor encrypt) {
		
		EncryptionAlgorithm<MultiplyKey> Algo=
				this.MultiplyAlgoCreation(encrypt.Algorithm);

		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			this.form=new Fileformat(encrypt);

			FileEncryptor<MultiplyKey> Code =
					new FileEncryptor<MultiplyKey>(Algo
					,(FilePublisher) pub);

			if (encrypt.EDOperation.equals("Encryption")){
				Code.encrtptFile(form);
			}
			else if (encrypt.EDOperation.equals("Decryption")){
				Code.decryptFile(form);
			}

		}else if(encrypt.FileORDirec.equals("Directory")){
			AsyncDirectoryProcessor<MultiplyKey> Code =
					new AsyncDirectoryProcessor<MultiplyKey>(Algo);
			if (encrypt.EDOperation.equals("Encryption")){
				String inputF=encrypt.SourceDirectory;
				Code.encryptDirectory(inputF);
			}
			if (encrypt.EDOperation.equals("Decryption")){
				String inputF=encrypt.SourceDirectory;
				Code.decryptDirectory(inputF,encrypt.KeyPath);
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

}
