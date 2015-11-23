package observer;

import org.junit.Test;

import fileOperation.EncryptionEvent;

public class EncryptionObserverTest {

	@Test
	public void testUpdate() {
		EncryptionEvent event=new EncryptionEvent("Encryption", "file",
				"Algorithm", "out", 1);
		EncryptionObserver tester=new EncryptionObserver();
		tester.update(event);
	}

}
