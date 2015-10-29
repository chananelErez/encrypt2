
public class Test {
	
	public static void main(String[] args){
		EncryptionAlgorithm Enc =new ShiftUpEncryption();
		Enc.setKey(5);
		Enc.setCypher("MJQQT");
		Enc.Decrypt();
		String ch=Enc.getPlainText();
		System.out.println(ch);
			
	}
	

}
