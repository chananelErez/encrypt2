import injection.BuildEncryptorInjection;
import injection.DirectoryProcessorInjection;
import writingFormat.Directoryformat;

public class draft {

	  public static void main(String[] args) {
		  BuildEncryptorInjection b=new BuildEncryptorInjection();
		  DirectoryProcessorInjection f=new DirectoryProcessorInjection();
		  Directoryformat ff=new Directoryformat(b.getBuilder());
		  f.getDirectoryEncryptor().encryptDirectory(ff);
		  
		  

		 
		  
	  }

}
