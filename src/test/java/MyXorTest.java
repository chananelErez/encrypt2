import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.SimpleKey;
import mathOperation.MyXor;

public class MyXorTest {

	@Test
	public void testOperate() {
		MyXor<SimpleKey> tester=new MyXor<SimpleKey>();
		SimpleKey k1=new SimpleKey();
		SimpleKey k2= new SimpleKey();
		k1.setKey(5);
		tester.setKey(k1);
		k2.setKey(8);
		assertEquals(tester.Operate(5),0);
		tester.setKey(k2);
		assertEquals(tester.Operate(5),5^8);
	}

}
