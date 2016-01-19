package encryptionInfo;

import java.util.ArrayList;

import algoBuilder.BuildEncryptor;

public class InfoConvert {
	
	public static ArrayList<GeneralInfo> BuilderToSperate(BuildEncryptor Bu){
		ArrayList<GeneralInfo> list=new ArrayList<GeneralInfo>();
		FileInfo fi=new FileInfo();
		EncryptionKind ek=new EncryptionKind();
		EncrOrDecr eord=new EncrOrDecr();
		
		fi.setDireN(Bu.getSourceDirectory());
		fi.setFileN(Bu.getFileName());
		fi.setFord(Bu.getFileORDirec());
		fi.setKeyPath(Bu.getKeyPath());
		
		ek.setAlgorithm(Bu.getAlgorithm());
		ek.setDouble(Bu.getIsDouble());
		ek.setKeyType(Bu.getKeyType());
		ek.setRepeat(Bu.getRepeat());
		
		eord.setEord(Bu.getEDOperation());
		
		list.add(fi);
		list.add(ek);
		list.add(eord);
		return list;
	}
	
	public static BuildEncryptor SperateToBuilder(FileInfo fi,
			EncryptionKind ek, EncrOrDecr eord ){
		BuildEncryptor encrypt=new BuildEncryptor();
		
		encrypt.setAlgorithm(ek.getAlgorithm());
		encrypt.setEDOperation(eord.getEord());
		encrypt.setFileName(fi.getFileN());
		encrypt.setFileORDirec(fi.getFord());
		encrypt.setIsDouble(ek.isDouble());
		encrypt.setKeyPath(fi.getKeyPath());
		encrypt.setKeyType(ek.getKeyType());
		encrypt.setRepeat(ek.getRepeat());
		encrypt.setSourceDirectory(fi.getDireN());
		
		return encrypt;
		
	}

}
