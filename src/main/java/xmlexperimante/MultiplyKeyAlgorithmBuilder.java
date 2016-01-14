package xmlexperimante;

import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.MultiplyKey;

public class MultiplyKeyAlgorithmBuilder implements AlgorithmBuilder {
	private MultiplyKey key;
	public MultiplyKeyAlgorithmBuilder(){
		this.key=new MultiplyKey();
	}

	public void MultiplyEncryptorBuilder(BuildEncryptor encrypt) {
		
		EncryptionAlgorithm<MultiplyKey> Algo=
				this.MultiplyAlgoCreation(encrypt.Algorithm);
		Algo.setKey(key);
		if(encrypt.FileORDirec=="File"){
			FileEncryptor<MultiplyKey> Code =new FileEncryptor<MultiplyKey>(Algo);

			if (encrypt.EDOperation=="Encryption"){
				String inputF=encrypt.SourceDirectory+"\\"+encrypt.FileName;
				String outputF=Code.NameConvert(inputF, "E");
				Code.encrtptFile(inputF,outputF,encrypt.KeyPath);
			}
			else if (encrypt.EDOperation=="Decryption"){
				String inputF=encrypt.SourceDirectory+"\\"+encrypt.FileName;
				String outputF=Code.NameConvert(inputF, "D");
				Code.decryptFile(inputF,outputF,encrypt.KeyPath);
			}

		}else if(encrypt.FileORDirec=="Directory"){
			AsyncDirectoryProcessor<MultiplyKey> Code =
					new AsyncDirectoryProcessor<MultiplyKey>(Algo);
			if (encrypt.EDOperation=="Encryption"){
				String inputF=encrypt.SourceDirectory;
				Code.encryptDirectory(inputF);
			}
			if (encrypt.EDOperation=="Decryption"){
				String inputF=encrypt.SourceDirectory;
				Code.decryptDirectory(inputF,encrypt.KeyPath);
			}

		}else{
			System.out.print("Wrong input!");
		}
		
	}
	
	public EncryptionAlgorithm<MultiplyKey> MultiplyAlgoCreation(String Algorithm){
		if(Algorithm=="ShiftMultiplyEncryption"){
			EncryptionAlgorithm<MultiplyKey> Algo=new ShiftMultiplyEncryption();
			return Algo;
		} else{
		return null;
		}
	}

}
