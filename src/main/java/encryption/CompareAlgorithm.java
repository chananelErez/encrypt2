package encryption;

import java.util.Comparator;

public class CompareAlgorithm implements Comparator<EncryptionAlgorithm<?>>{

	@Override
	public int compare(EncryptionAlgorithm<?> o1, EncryptionAlgorithm<?> o2) {
		
		if(o1.getKeySrength()>o2.getKeySrength()){
			return 1;
		};
		if(o1.getKeySrength()<o2.getKeySrength()){
			return -1;
		};
		return 0;
		
	}

}
