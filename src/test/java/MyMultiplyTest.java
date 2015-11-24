import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.MultiplyKey;
import mathOperation.MyMultiply;

public class MyMultiplyTest {

	@Test
	public void testOperate() {
        MyMultiply<MultiplyKey> tester=new MyMultiply<MultiplyKey>();
        MultiplyKey k1=new MultiplyKey();
        MultiplyKey k2= new MultiplyKey();
		k1.setKey(5);
		k2.setKey(8);
		tester.setKey(k1);
		assertEquals(tester.Operate(5),25);
		tester.setKey(k2);
		assertEquals(tester.Operate(5),40);
	}

}
