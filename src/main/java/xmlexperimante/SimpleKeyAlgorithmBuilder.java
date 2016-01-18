package xmlexperimante;

import encryption.EncryptionAlgorithm;
import encryption.RepeatEncryption;
import encryption.ShiftUpEncryption;
import encryption.XorEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.SimpleKey;

public class SimpleKeyAlgorithmBuilder implements AlgorithmBuilder {
	
	public SimpleKeyAlgorithmBuilder(){

	}
	

	public void SimpleEncryptorBuilder(BuildEncryptor encrypt){
		EncryptionAlgorithm<SimpleKey> Algo=
				this.SimpleAlgoCreation(encrypt.Algorithm, encrypt.Repeat);
		SimpleKey key=new SimpleKey();
		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			FileEncryptor<SimpleKey> Code =new FileEncryptor<SimpleKey>(Algo);

			if (encrypt.EDOperation.equals("Encryption")){
				String inputF=encrypt.SourceDirectory+"\\"+encrypt.FileName;
				String outputF=Code.NameConvert(inputF, "E");
				Code.encrtptFile(inputF,outputF,encrypt.KeyPath);
			}
			else if (encrypt.EDOperation.equals("Decryption")){
				String inputF=encrypt.SourceDirectory+"\\"+encrypt.FileName;
				String outputF=Code.NameConvert(inputF, "D");
				Code.decryptFile(inputF,outputF,encrypt.KeyPath);
			}

		}else if(encrypt.FileORDirec.equals("Directory")){
			AsyncDirectoryProcessor<SimpleKey> Code =
					new AsyncDirectoryProcessor<SimpleKey>(Algo);
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


}
