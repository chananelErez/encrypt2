import static org.junit.Assert.*;

import org.junit.Test;

public class MyAdditionTest {

	@Test
	public void testOperate() {
		MyAddition tester=new MyAddition();
		assertEquals(tester.Operate(4, 5),9);
		assertEquals(tester.Operate(3, 9),12);
	}

}
