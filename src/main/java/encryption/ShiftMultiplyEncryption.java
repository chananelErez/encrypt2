package encryption;
import keyBuilding.MultiplyKey;
import mathOperation.MyModDivision;
import mathOperation.MyMultiply;
public class ShiftMultiplyEncryption extends GeneralEncryption<MultiplyKey>
implements EncryptionAlgorithm<MultiplyKey>{

	
	public ShiftMultiplyEncryption(){
		
		encryptMethod=new MyMultiply<MultiplyKey>();
		decryptMethod=new MyModDivision<MultiplyKey>();
		
	}
		
	@Override
	public String toString(){
		return "Shift Multiply Encryption";
	}
	
	
}
