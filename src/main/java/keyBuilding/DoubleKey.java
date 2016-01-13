package keyBuilding;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import encryption.invalidEncryptionKeyException;
import fileOperation.FileEncryptor;

public class DoubleKey<T extends SimpleKey> implements KeyType{
	
	private T key1;
	private T key2;
/*	private Scanner user_input;*/
	
	public DoubleKey(T k1,T k2){
		key1=k1;
		key2=k2;
		
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
		FileEncryptor.writeFile(String.valueOf(key1.getKey()), "key1", 
				file+"\\key1.txt");
		FileEncryptor.writeFile(String.valueOf(key2.getKey()), "key2",
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
	
	public List<T> getKey(){
		List<T> lst=new ArrayList<T>();
		lst.add(key1);
		lst.add(key2);
		return lst;
	}

}
