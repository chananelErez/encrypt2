import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.SimpleKey;
import mathOperation.MyRepeatSubstraction;

public class MyRepeatSubstractionTest {

	@Test
	public void testOperate() {
		MyRepeatSubstraction<SimpleKey> tester=new MyRepeatSubstraction<SimpleKey>(5);
		SimpleKey k1=new SimpleKey();
		SimpleKey k2= new SimpleKey();
		k1.setKey(5);
		k2.setKey(9);
		
		assertEquals(tester.Operate(54, k1),29);
		assertEquals(tester.Operate(103, k2),58);
	}

}
