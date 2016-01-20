package algoBuilder;

import adService.FilePublisher;
import adService.GeneralPublisher;
import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.RepeatEncryption;
import encryption.ShiftUpEncryption;
import encryption.XorEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import writingFormat.Fileformat;

public class DoubleSimpleKeyAlgorithmBuilder implements AlgorithmBuilder {

	private DoubleKey<SimpleKey> key;
	private GeneralPublisher pub;
	private Fileformat form;
	
	public DoubleSimpleKeyAlgorithmBuilder(GeneralPublisher pub){
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		this.key= new DoubleKey<SimpleKey>(key1,key2);
		
		this.pub=pub;
		
	}
	
	public void DoubleSimpleEncryptorBuilder(BuildEncryptor encrypt) {
		DoubleEncryption<SimpleKey> Algo=
				this.DoubleSimpleAlgoCreation(encrypt.Algorithm, encrypt.Repeat);
		
		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			this.form=new Fileformat(encrypt);
			FileEncryptor<DoubleKey<SimpleKey>> Code =
					new FileEncryptor<DoubleKey<SimpleKey>>(Algo,
							(FilePublisher) pub);

			if (encrypt.EDOperation.equals("Encryption")){
				Code.encrtptFile(form);
			}
			else if (encrypt.EDOperation.equals("Decryption")){
				Code.decryptFile(form);
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
