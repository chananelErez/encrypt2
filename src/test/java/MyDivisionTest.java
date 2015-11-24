import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.MultiplyKey;
import keyBuilding.SimpleKey;
import mathOperation.MyModDivision;

public class MyDivisionTest {

	@Test
	public void testOperate() {
		MyModDivision<SimpleKey> tester=new MyModDivision<SimpleKey>();
		MultiplyKey k1=new MultiplyKey();
		MultiplyKey k2= new MultiplyKey();
		k1.setKey(5);
		k2.setKey(3);
		tester.setKey( k1);
		assertEquals(tester.Operate(10),2);
		tester.setKey(k2);
		assertEquals(tester.Operate(9),3);
	}

}
