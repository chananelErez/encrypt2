package algoBuilder;

import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.ShiftMultiplyEncryption;
import fileOperation.AsyncDirectoryProcessor;
import fileOperation.FileEncryptor;
import keyBuilding.DoubleKey;
import keyBuilding.MultiplyKey;

public class DoubleMultiplyKeyAlgorithmBuilder implements AlgorithmBuilder {


	private DoubleKey<MultiplyKey> key;
	public DoubleMultiplyKeyAlgorithmBuilder(){
		MultiplyKey key1=new MultiplyKey();
		MultiplyKey key2=new MultiplyKey();
		this.key= new DoubleKey<MultiplyKey>(key1,key2);
	}
	
	public void DoubleMultiplyEncryptorBuilder(BuildEncryptor encrypt) {
		DoubleEncryption<MultiplyKey> Algo=
				this.DoubleMultiplyAlgoCreation(encrypt.Algorithm, encrypt.Repeat);
		Algo.setKey(key);
		if(encrypt.FileORDirec.equals("File")){
			FileEncryptor<DoubleKey<MultiplyKey>> Code =
					new FileEncryptor<DoubleKey<MultiplyKey>>(Algo);

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
			AsyncDirectoryProcessor<DoubleKey<MultiplyKey>> Code =
					new AsyncDirectoryProcessor<DoubleKey<MultiplyKey>>(Algo);
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
	
	public DoubleEncryption<MultiplyKey> DoubleMultiplyAlgoCreation(String Algorithm,
			int repeat){
		if(Algorithm.equals("ShiftMultiplyEncryption")){
			EncryptionAlgorithm<MultiplyKey> intAlgo=new ShiftMultiplyEncryption();
			DoubleEncryption<MultiplyKey> Algo=new DoubleEncryption<MultiplyKey>(intAlgo);
			return Algo;
		} else{
			return null;
		}
	}
		

}
