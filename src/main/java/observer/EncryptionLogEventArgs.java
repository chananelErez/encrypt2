package observer;

import java.util.concurrent.ConcurrentHashMap;

import listenersEvents.EncryptionEvent;
import listenersEvents.ErrorEvent;
import listenersEvents.GeneralEvent;

public class EncryptionLogEventArgs {
	
	ConcurrentHashMap<String , EncryptionEvent> databaseE= 
			                      new ConcurrentHashMap<String , EncryptionEvent>();
	ConcurrentHashMap<String , EncryptionEvent> databaseD=
			                      new ConcurrentHashMap<String , EncryptionEvent>();
	
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
				if(event.getFile().toLowerCase().endsWith(".txt")){
				System.out.print("The Encryption of the file "+event.getFile()
				                    +" was started.\n");
				}else{
					System.out.print("The Encryption of the folder "+event.getFile()
                    +" was started.\n");
				}
			}else{
				EncryptionEvent preEvent=databaseE.get(event.getFile());
				long t=event.getCurrentTime()-preEvent.getCurrentTime();
				String T=String.valueOf(t);
				if(event.getFile().toLowerCase().endsWith(".txt")){
					System.out.println("The encryption for file "+preEvent.getFile());
					System.out.println("The encryption used the algorithm "
					                    +preEvent.getAlgorithm());
					System.out.println("The encryption took "+T+" milliseconds." );
					System.out.println("The encrtpted file is located in file "+
					                    preEvent.getOutputFile());
					}else{
						System.out.println("The encryption of the folder "+preEvent.getFile());
						System.out.println("The encryption used the algorithm "
						                    +preEvent.getAlgorithm());
						System.out.println("The whole encryption took "+T+" milliseconds." );
						System.out.println("The encrtpted folder is located in "+
						                    preEvent.getOutputFile());
					}
			}
			
			
			
		}
		if(event.geteORd()=="Decryption"){
			if(event.getAlgorithm()!=null){
				databaseD.put(event.getFile(), event);
				if(event.getFile().toLowerCase().endsWith(".txt")){
				System.out.print("The Decryption of the file "+event.getFile()
				                    +" was started.\n");
				}else{
					System.out.print("The Decryption of the folder "+event.getFile()
                    +" was started.\n");
				}
			}else{
				EncryptionEvent preEvent=databaseD.get(event.getFile());
				long t=event.getCurrentTime()-preEvent.getCurrentTime();
				String T=String.valueOf(t);
				if(event.getFile().toLowerCase().endsWith(".txt")){
					System.out.println("The decryption for file "+preEvent.getFile());
					System.out.println("The decryption used the algorithm "
					                    +preEvent.getAlgorithm());
					System.out.println("The decryption took "+T+" milliseconds." );
					System.out.println("The decrtpted file is located in file "+
					                    preEvent.getOutputFile());
					}else{
						System.out.println("The decryption of the folder "+preEvent.getFile());
						System.out.println("The decryption used the algorithm "
						                    +preEvent.getAlgorithm());
						System.out.println("The whole decryption took "+T+" milliseconds." );
						System.out.println("The decrtpted folder is located in "+
						                    preEvent.getOutputFile());
					}
				
			}
			
		}
	}
	
	

}
