package encryption;
public class CompareAlgorithm {
	
	public EncryptionAlgorithm Comparator(EncryptionAlgorithm a,EncryptionAlgorithm b){
		if(a.getKeySrength()>=b.getKeySrength()){
			return a;
		}else{
			return b;
		}
		
	}

}
