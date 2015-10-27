import static org.junit.Assert.*;

import org.junit.Test;

public class MyMultiplyTest {

	@Test
	public void testOperate() {
        MyMultiply tester=new MyMultiply();
		
		assertEquals(tester.Operate(5, 5),25);
		assertEquals(tester.Operate(5, 8),40);
	}

}
