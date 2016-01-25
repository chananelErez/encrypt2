package keyBuilding;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import encryption.invalidEncryptionKeyException;
import fileOperation.Analphabet;

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
		Analphabet.writeFile(String.valueOf(key), "key",file+"\\key.txt");
		
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
	public void getKeyFromFile(String KeyPath) throws invalidEncryptionKeyException { 
		try {
			String k=Analphabet.readFile(KeyPath, StandardCharsets.UTF_8);
			if(!this.isNumeric(k)){
				throw new invalidEncryptionKeyException();
			}
			int Key=Integer.parseInt(k);
			if(Key<=0){
				throw new invalidEncryptionKeyException();
			}
			
			setKey(Key);
		} catch (IOException e) {
			System.out.println("Failed to read the key.");
		}
		
	}
	
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	@Override
	public void generateKeyFromHash(int k) {
		setKey(k);
		
	} 
	

}
