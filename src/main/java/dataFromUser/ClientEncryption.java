package dataFromUser;

import algoBuilder.BuildEncryptor;
import encryptionInfo.EncrOrDecr;
import encryptionInfo.EncryptionKind;
import encryptionInfo.FileInfo;
import encryptionInfo.InfoConvert;

public class ClientEncryption implements ClientData{
	
	public  BuildEncryptor getData(){
		
		FileInfo fi=ClientFileInfo.getData();
		EncryptionKind ek=ClientEncryptionKind.getData();
		EncrOrDecr eord=ClientEncryptionOrDecryption.getData();
		BuildEncryptor encrypt =InfoConvert.SperateToBuilder(fi, ek, eord);
		return encrypt;
	}

}
