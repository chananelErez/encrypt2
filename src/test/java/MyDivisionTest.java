import static org.junit.Assert.*;

import org.junit.Test;

public class MyDivisionTest {

	@Test
	public void testOperate() {
		MyDivision tester=new MyDivision();
		assertEquals(tester.Operate(10, 5),2);
		assertEquals(tester.Operate(9, 3),3);
	}

}
