package dataFromUser;

import java.util.Scanner;

import encryptionInfo.EncrOrDecr;

public class ClientEncryptionOrDecryption implements ClientData{
	public static EncrOrDecr getData(){
		EncrOrDecr eord=new EncrOrDecr();
		Scanner user_input=new Scanner(System.in);
		System.out.println(showEDOperation());
		String s=user_input.next();
		if(s.equals("1")){
			eord.setEord("Encryption");
		}else if(s.equals("2")){
			eord.setEord("Decryption");
		}
		user_input.close();
		return eord;
		
	}
	
	public static String showEDOperation() {
		String EDOperation="Please type 1 for encryption and 2 for decryption: ";
		return EDOperation;
	}

}
