package dataFromUser;

import java.util.Scanner;

import encryptionInfo.EncryptionKind;

public class ClientEncryptionKind implements ClientData{
	
	public static EncryptionKind getData(){
		EncryptionKind ek=new EncryptionKind();
		Scanner user_input=new Scanner(System.in);
		System.out.println(showIsDouble());
		String s=user_input.next();
		if(s.equals("1")){
			ek.setDouble(false);
		}else if(s.equals("2")){
			ek.setDouble(true);
		}
		
		System.out.println(showAlgorithm());
		String s1=user_input.next();
		if(s1.equals("1")){
			ek.setAlgorithm("ShiftUpEncryption");
			ek.setRepeat(0);
		}else if(s1.equals("2")){
			ek.setAlgorithm("RepeatEncryption");
			System.out.println(showRepeat());
			String s2=user_input.next();
			ek.setRepeat(Integer.parseInt(s2));
		}else if(s1.equals("3")){
			ek.setAlgorithm("ShiftMultiplyEncryption");
			ek.setRepeat(0);
		}else if(s1.equals("4")){
			ek.setAlgorithm("XorEncryption");
			ek.setRepeat(0);
		}
		
		if(s.equals("1")){
			if(s1.equals("1")||s1.equals("2")||s1.equals("4")){
				ek.setKeyType("SimpleKey");
			}else if(s1.equals("3")){
				ek.setKeyType("MultiplyKey");
			}
			
		}else if(s.equals("2")){
			if(s1.equals("1")||s1.equals("2")||s1.equals("4")){
				ek.setKeyType("DoubleKey<SimpleKey>");
			}else if(s1.equals("3")){
				ek.setKeyType("DoubleKey<MultiplyKey>");
			}
		}
		
		
		user_input.close();
		
		return ek;
	}
	
	
	public static String showRepeat() {
		String Repeat="Please insert the number of repeats: ";
		return Repeat;
	}
	
	public static String showAlgorithm() {
		String Algorithm="Please type 1 for ShiftUpEncryption,"
				+ " 2 for RepeatEncryption, 3 for ShiftMultiplyEncryption "
				+ "and 4 for XorEncryption: ";
		return Algorithm;
	}
	
	public static String showIsDouble() {
		
		String IsDouble="Please type 1 for normal encryption and 2 for double encryption :";
		return IsDouble;
	}

}
