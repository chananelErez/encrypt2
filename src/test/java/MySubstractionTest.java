import static org.junit.Assert.*;

import org.junit.Test;

public class MySubstractionTest {

	@Test
	public void testOperate() {
		MySubstraction tester=new MySubstraction();
		assertEquals(tester.Operate(100, 5),95);
		assertEquals(tester.Operate(13, 9),4);
	}

}
