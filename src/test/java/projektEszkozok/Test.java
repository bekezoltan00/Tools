package projektEszkozok;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class Test {

	@org.junit.Test
	public void shouldReturnFalseOnCheckFalseEqualsTrue() {
		assertNotEquals(true, false);
	}
	
	@org.junit.Test
	public void shouldReturnTrueWhenLengthOfStringsAreEqualsAndFalseIfNot() {
		String s1 = "test";
		String s2 = "teszt";
		String s3 = "fail";
		
		assertEquals(s1.length(), s3.length());
		assertFalse(s2.length() == s3.length());
		
	}
	
	@org.junit.Test(expected=IndexOutOfBoundsException.class)
	public void shouldReturnExceptionOnDivideByZero() {
		ArrayList<Integer> emptyList = new ArrayList<Integer>();
		Object o = emptyList.get(0);
	}
	
	@org.junit.Test
	public void shouldReturnTrueWhenNumbersAreEqual() {
		assertFalse( 16 == 1.6 );
		assertTrue( 16 == (int)(1.63 * 10));
	}
	
}
