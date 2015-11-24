import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.SimpleKey;
import mathOperation.MyAddition;

public class MyAdditionTest {

	@Test
	public void testOperate() {
		MyAddition<SimpleKey> tester=new MyAddition<SimpleKey>();
		SimpleKey k1=new SimpleKey();
		SimpleKey k2= new SimpleKey();
		k1.setKey(5);
		tester.setKey(k1);
		assertEquals(tester.Operate(4),9);
		k2.setKey(9);
		tester.setKey(k2);
		assertEquals(tester.Operate(3),12);
	}

}
