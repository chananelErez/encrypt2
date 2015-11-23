import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.SimpleKey;
import mathOperation.MyDivision;

public class MyDivisionTest {

	@Test
	public void testOperate() {
		MyDivision<SimpleKey> tester=new MyDivision<SimpleKey>();
		SimpleKey k1=new SimpleKey();
		SimpleKey k2= new SimpleKey();
		k1.setKey(5);
		k2.setKey(3);
		
		assertEquals(tester.Operate(10, k1),2);
		assertEquals(tester.Operate(9, k2),3);
	}

}
