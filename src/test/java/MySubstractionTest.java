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
		
		assertEquals(tester.Operate(100, k1),95);
		assertEquals(tester.Operate(13, k2),4);
	}

}
