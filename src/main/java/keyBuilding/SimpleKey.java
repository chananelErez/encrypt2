package keyBuilding;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import encryption.invalidEncryptionKeyException;
import fileOperation.FileEncryptor;

public class SimpleKey implements KeyType {
	
	private int key=0;
	private Scanner user_input;
	
	public void setUser_input(Scanner user_input) {
		this.user_input = user_input;
	}
	public SimpleKey(){
		this.setUser_input(new Scanner(System.in));
	}
	@Override
	public void generateKey() {
		int Key=(int)(Math.random()*13)+1;
		setKey(Key);
	}

	@Override
	public void printKeyToFile(String file) {
		FileEncryptor.writeFile(String.valueOf(key), "key",file+"\\key.txt");
		
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
	@Override
	public void getKeyFromFile(String KeyPath) {
		try {
			String k=FileEncryptor.readFile(KeyPath, StandardCharsets.UTF_8);
			setKey(Integer.valueOf(k));
		} catch (IOException e) {
			System.out.println("Failed to read the key.");
		}
		
	}
	

}
