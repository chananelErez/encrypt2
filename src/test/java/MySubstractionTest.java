import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.SimpleKey;
import mathOperation.MySubstraction;

public class MySubstractionTest {

	@Test
	public void testOperate() {
		MySubstraction<SimpleKey> tester=new MySubstraction<SimpleKey>();
		SimpleKey k1=new SimpleKey();
		SimpleKey k2= new SimpleKey();
		k1.setKey(5);
		k2.setKey(9);
		tester.setKey(k1);
		assertEquals(tester.Operate(100),95);
		tester.setKey(k2);
		assertEquals(tester.Operate(13),4);
	}

}
