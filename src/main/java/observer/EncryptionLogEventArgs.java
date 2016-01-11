package observer;

import java.util.HashMap;

import fileOperation.EncryptionEvent;
import fileOperation.ErrorEvent;
import fileOperation.GeneralEvent;

public class EncryptionLogEventArgs {
	
	HashMap<String , EncryptionEvent> databaseE= 
			                      new HashMap<String , EncryptionEvent>();
	HashMap<String , EncryptionEvent> databaseD=
			                      new HashMap<String , EncryptionEvent>();
	
	public void printErrorMessage(ErrorEvent event){
		System.out.println("An error occurred during the "+event.geteORd()
		+" of the file "+event.getFile()+". The error type: "
				+event.getErrorKind());
	}
	
	public void printMessage(GeneralEvent event){
		if(event instanceof ErrorEvent){
			this.printErrorMessage((ErrorEvent) event);
		}
		if(event instanceof EncryptionEvent){
			this.printEncryptionMessage((EncryptionEvent) event);
		}
	}
	
	public void printEncryptionMessage(EncryptionEvent event){
		if(event.geteORd()=="Encryption"){
			if(event.getAlgorithm()!=null){
				databaseE.put(event.getFile(), event);
				System.out.print("The Encryption of the file "+event.getFile()
				                    +" was started.\n");
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
