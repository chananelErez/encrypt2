package keyBuilding;

import java.util.Scanner;

import encryption.invalidEncryptionKeyException;
import fileOperation.FileEncryptor;

public class SimpleKey implements KeyType {
	
	private int key=0;
	private Scanner user_input;
	
	public void setUser_input(Scanner user_input) {
		this.user_input = user_input;
	}
	
	@Override
	public void generateKey() {
		int Key=(int)(Math.random()*13)+1;
		setKey(Key);
	}

	@Override
	public void printKeyToFile() {
		FileEncryptor.writeFile(String.valueOf(key), "key", "C:\\Users\\user\\key.txt");
		
	}

	@Override
	public void setUserKey() throws invalidEncryptionKeyException {
		/*Scanner user_input=new Scanner(System.in);*/
		System.out.println("please insert the key: ");
		if(!user_input.hasNextInt()){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		int Key=Integer.parseInt(user_input.next());
		if(Key<=0){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		setKey(Key);
		user_input.close();
		
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	

}
