package mathOperation;

import static org.junit.Assert.*;

import org.junit.Test;

import keyBuilding.DoubleKey;
import keyBuilding.SimpleKey;

public class DoubleOperationTest {

	@Test
	public void testOperateIntDoubleKeyOfT() {
		DoubleOperation<SimpleKey> tester=
				new DoubleOperation<SimpleKey>
		(new MyAddition<SimpleKey>());
		SimpleKey key1=new SimpleKey();
		SimpleKey key2=new SimpleKey();
		DoubleKey<SimpleKey> k= new DoubleKey<SimpleKey>(key1,key2);
		k.setKey(3, 5);
		tester.setKey(k);
		assertEquals(tester.Operate(4),12);
		
	}

}
