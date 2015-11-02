import static org.junit.Assert.*;

import org.junit.Test;

public class MyRepeatSubstractionTest {

	@Test
	public void testOperate() {
		MyRepeatSubstraction tester=new MyRepeatSubstraction(5);
		assertEquals(tester.Operate(54, 5),29);
		assertEquals(tester.Operate(103, 9),58);
	}

}
