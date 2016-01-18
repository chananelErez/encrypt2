package algoBuilder;

import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.RepeatEncryption;
import encryption.ShiftUpEncryption;
import encryption.XorEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;

public class DoubleSimpleKeyAlgorithmBuilder implements AlgorithmBuilder {

	private DoubleKey<SimpleKey> key;
	public DoubleSimpleKeyAlgorithmBuilder(){
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		this.key= new DoubleKey<SimpleKey>(key1,key2);
	}
	
	public void DoubleSimpleEncryptorBuilder(BuildEncryptor encrypt) {
		DoubleEncryption<SimpleKey> Algo=
				this.DoubleSimpleAlgoCreation(encrypt.Algorithm, encrypt.Repeat);
		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			FileEncryptor<DoubleKey<SimpleKey>> Code =
					new FileEncryptor<DoubleKey<SimpleKey>>(Algo);

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
			AsyncDirectoryProcessor<DoubleKey<SimpleKey>> Code =
					new AsyncDirectoryProcessor<DoubleKey<SimpleKey>>(Algo);
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
	
	public DoubleEncryption<SimpleKey> DoubleSimpleAlgoCreation(String Algorithm,
			int repeat){
		if(Algorithm.equals("ShiftUpEncryption")){
			EncryptionAlgorithm<SimpleKey> intAlgo=new ShiftUpEncryption();
			DoubleEncryption<SimpleKey> Algo=new DoubleEncryption<SimpleKey>(intAlgo);
			return Algo;
		}else if(Algorithm.equals("XorEncryption")){
			EncryptionAlgorithm<SimpleKey> intAlgo=new XorEncryption();
			DoubleEncryption<SimpleKey> Algo=new DoubleEncryption<SimpleKey>(intAlgo);
			return Algo;
		}else if(Algorithm.equals("RepeatEncryption")){
			EncryptionAlgorithm<SimpleKey> intAlgo=new RepeatEncryption(repeat);
			DoubleEncryption<SimpleKey> Algo=new DoubleEncryption<SimpleKey>(intAlgo);
			return Algo;
		} else{
			return null;
		}
	}
		
	

}
