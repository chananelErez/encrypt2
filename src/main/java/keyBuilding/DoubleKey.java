package keyBuilding;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.inject.Inject;

import encryption.invalidEncryptionKeyException;
import fileOperation.Analphabet;

public class DoubleKey implements KeyType{
	
	private SimpleKey key1=new SimpleKey();
	private SimpleKey key2=new SimpleKey();
/*	private Scanner user_input;*/
	
	@Inject
	public DoubleKey(SimpleKey k1,SimpleKey k2){
		this.key1=k1;
		this.key2=k2;
	}
/*	
	public void setUser_input(Scanner user_input) {
		this.user_input = user_input;
	}*/

	@Override
	public void generateKey() {
		key1.generateKey();
		key2.generateKey();
		
	}

	@Override
	public void printKeyToFile(String file) {
		Analphabet.writeFile(String.valueOf(key1.getKey()), "key1", 
				file+"\\key1.txt");
		Analphabet.writeFile(String.valueOf(key2.getKey()), "key2",
				file+"\\key2.txt");
		
	}

	@Override
	public void setUserKey() throws invalidEncryptionKeyException {
		Scanner user_input=new Scanner(System.in);
		System.out.println("please insert the first key: ");
		if(!user_input.hasNextInt()){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		int Key=Integer.parseInt(user_input.next());
		if(Key<=0){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		key1.setKey(Key);
		System.out.println("please insert the second key: ");
		if(!user_input.hasNextInt()){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		Key=Integer.parseInt(user_input.next());
		if(Key<=0){
			user_input.close();
			throw new invalidEncryptionKeyException();
		}
		key2.setKey(Key);
		user_input.close();
		
	}
	
	public void setKey(int k1,int k2){
		key1.setKey(k1);
		key2.setKey(k2);
	}
	
	public ArrayList<SimpleKey> getKey(){
		ArrayList<SimpleKey> lst=new ArrayList<SimpleKey>();
		lst.add(key1);
		lst.add(key2);
		return lst;
	}

	@Override
	public void getKeyFromFile(String KeyPath) throws invalidEncryptionKeyException {
		try {
			String k=Analphabet.readFile(KeyPath, StandardCharsets.UTF_8);
			String[] arr=(k.split(" "));
			String keya=arr[0];
			String keyb=arr[1];
			if(!(this.isNumeric(keya)&&this.isNumeric(keyb))){
				throw new invalidEncryptionKeyException();
			}
			int Keya=Integer.parseInt(keya);
			int Keyb=Integer.parseInt(keyb);
			if(Keya<=0||Keyb<=0){
				throw new invalidEncryptionKeyException();
			}
			key1.setKey(Keya);
			key2.setKey(Keyb);
			
		} catch (IOException e) {
			System.out.println("Failed to read the key.");
		}
		
	}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}

	@Override
	public void generateKeyFromHash(int k) {
		setKey(k, k);
		
	} 

}
