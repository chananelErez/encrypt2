import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.SimpleKey;
import mathOperation.MyRepeatAddition;

public class MyRepeatAdditionTest {

	@Test
	public void testOperate() {
		MyRepeatAddition<SimpleKey> tester=new MyRepeatAddition<SimpleKey>(4);
		SimpleKey k1=new SimpleKey();
		SimpleKey k2= new SimpleKey();
		k1.setKey(5);
		k2.setKey(9);
		
		assertEquals(tester.Operate(4, k1),24);
		assertEquals(tester.Operate(3, k2),39);
	}

}
