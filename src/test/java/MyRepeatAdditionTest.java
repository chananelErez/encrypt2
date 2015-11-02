import static org.junit.Assert.*;

import org.junit.Test;

public class MyRepeatAdditionTest {

	@Test
	public void testOperate() {
		MyRepeatAddition tester=new MyRepeatAddition(4);
		assertEquals(tester.Operate(4, 5),24);
		assertEquals(tester.Operate(3, 9),39);
	}

}
