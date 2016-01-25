import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import algoBuilder.BuildEncryptor;
import fileOperation.AsyncDirectoryProcessor;
import guiceInjection.MyModule;
import keyBuilding.DoubleKey;
import writingFormat.Directoryformat;
import xmlexperimante.MyJAXB;

public class GuiceExample {

	public static void main(String[] args) {
		 Injector injector = Guice.createInjector( new MyModule() );
		 BuildEncryptor b=injector.getInstance(MyJAXB.class).ReadXML();
		 AsyncDirectoryProcessor<DoubleKey> Code=
					injector.getInstance(Key.get
							(new TypeLiteral<AsyncDirectoryProcessor
									<DoubleKey>>(){}));
		 
		 Directoryformat ff=new Directoryformat(b);
		 Code.encryptDirectory(ff);

	}

}
