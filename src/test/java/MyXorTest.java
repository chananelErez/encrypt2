import static org.junit.Assert.*;

import org.junit.Test;

public class MyXorTest {

	@Test
	public void testOperate() {
		MyXor tester=new MyXor();
		
		assertEquals(tester.Operate(5, 5),0);
		assertEquals(tester.Operate(5, 8),5^8);
	}

}
