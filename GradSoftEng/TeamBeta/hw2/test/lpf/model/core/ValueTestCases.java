package lpf.model.core;

import junit.framework.TestCase;

public class ValueTestCases extends TestCase {
	
	/**
	 * Setup some sample values to work with.
	 */
	protected void setUp() {
		
	}
	
	/*
	 * Test for 'Value(char)'
	 */
	public void testValue() {
		Value v = new Value('1');
		
		assertEquals('1', v.value);
	}
	
	/*
	 * Test for 'equals(Object)'
	 */
	public void testEqual() {
		Value v1 = new Value('3');
		Value v2 = new Value('3');
		Value v3 = new Value('1');
		Object obj = new Object();
		
		assertTrue(v1.equals(v2));
		assertFalse(v1.equals(v3));
		assertFalse(v1.equals(obj));
	}
	
	/*
	 * Test for 'hashCode'
	 */
	public void testHashCode() {
		Value v1 = new Value('3');
		
		assertEquals(v1.hashCode(), '3');
	}
}
