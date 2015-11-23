package observer;

import java.util.HashMap;

import fileOperation.EncryptionEvent;

public class EncryptionLogEventArgs {
	
	HashMap<String , EncryptionEvent> databaseE= 
			                      new HashMap<String , EncryptionEvent>();
	HashMap<String , EncryptionEvent> databaseD=
			                      new HashMap<String , EncryptionEvent>();
	
	public void printMessege(EncryptionEvent event){
		if(event.geteORd()=="Encryption"){
			if(event.getAlgorithm()!=null){
				databaseE.put(event.getFile(), event);
				System.out.print("The Encryption of the file "+event.getFile()
				                    +" was started.");
			}else{
				EncryptionEvent preEvent=databaseE.get(event.getFile());
				long t=event.getCurrentTime()-preEvent.getCurrentTime();
				String T=String.valueOf(t);
				System.out.println("The encryption for file "+preEvent.getFile());
				System.out.println("The encryption used the algorithm "
				                    +preEvent.getAlgorithm());
				System.out.println("The encryption took "+T+" milliseconds." );
				System.out.println("The encrtpted file is located in file "+
				                    preEvent.getOutputFile());
				
			}
			
			
			
		}
		if(event.geteORd()=="Decryption"){
			if(event.getAlgorithm()!=null){
				databaseD.put(event.getFile(), event);
			}else{
				EncryptionEvent preEvent=databaseD.get(event.getFile());
				long t=event.getCurrentTime()-preEvent.getCurrentTime();
				String T=String.valueOf(t);
				System.out.println("The decryption for file "+preEvent.getFile());
				System.out.println("The decryption used the algorithm "
				                    +preEvent.getAlgorithm());
				System.out.println("The decryption took "+T+" milliseconds." );
				System.out.println("The decrtpted file is located in file "+
				                    preEvent.getOutputFile());
				
			}
			
		}
	}
	
	

}
