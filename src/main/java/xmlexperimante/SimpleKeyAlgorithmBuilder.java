package xmlexperimante;

import encryption.EncryptionAlgorithm;
import encryption.RepeatEncryption;
import encryption.ShiftUpEncryption;
import encryption.XorEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.SimpleKey;

public class SimpleKeyAlgorithmBuilder implements AlgorithmBuilder {
	private SimpleKey key;
	public SimpleKeyAlgorithmBuilder(){
		this.key=new SimpleKey();
	}
	

	public void SimpleEncryptorBuilder(BuildEncryptor encrypt){
		EncryptionAlgorithm<SimpleKey> Algo=
				this.SimpleAlgoCreation(encrypt.Algorithm, encrypt.Repeat);
		Algo.setKey(key);
		if(encrypt.FileORDirec=="File"){
			FileEncryptor<SimpleKey> Code =new FileEncryptor<SimpleKey>(Algo);

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
			AsyncDirectoryProcessor<SimpleKey> Code =
					new AsyncDirectoryProcessor<SimpleKey>(Algo);
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
	
	public EncryptionAlgorithm<SimpleKey> SimpleAlgoCreation(String Algorithm,
			int repeat){
		if(Algorithm=="ShiftUpEncryption"){
			EncryptionAlgorithm<SimpleKey> Algo=new ShiftUpEncryption();
			return Algo;
		}else if(Algorithm=="XorEncryption"){
			EncryptionAlgorithm<SimpleKey> Algo=new XorEncryption();
			return Algo;
		}else if(Algorithm=="RepeatEncryption"){
			EncryptionAlgorithm<SimpleKey> Algo=new RepeatEncryption(repeat);
			return Algo;
		} else{
		return null;
		}
	}


}
