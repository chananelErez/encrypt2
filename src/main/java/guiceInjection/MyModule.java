package guiceInjection;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import encryption.DoubleEncryption;
import encryption.EncryptionAlgorithm;
import encryption.ShiftUpEncryption;
import fileOperation.AsyncDirectoryProcessor;
import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;
import observer.EncryptionObserver;




public class MyModule extends AbstractModule{

	@Override
	protected void configure() {

		bind(new TypeLiteral<EncryptionAlgorithm<SimpleKey>>(){}).
		annotatedWith(Names.named("simple")).to(ShiftUpEncryption.class);
		
		
		bind(new TypeLiteral<EncryptionAlgorithm<DoubleKey>>(){}).
		annotatedWith(Names.named("double")).to(new TypeLiteral<DoubleEncryption<SimpleKey>>(){});
		
		bind(new TypeLiteral<DoubleEncryption<SimpleKey>>(){});
		bind(new TypeLiteral<AsyncDirectoryProcessor<DoubleKey>>(){});
		
		bind(EncryptionObserver.class).toInstance(new EncryptionObserver());
		
		String fileN="C:\\Users\\Public\\Documents\\openingexperiment"
				+ "\\justfolder\\zetha.xml";
		 bind(String.class).annotatedWith(Names.named("specific")).toInstance(fileN);
		 
		 
		
	}

}
