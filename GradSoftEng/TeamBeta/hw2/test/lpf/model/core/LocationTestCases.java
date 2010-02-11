package lpf.model.core;

import junit.framework.TestCase;

public class LocationTestCases extends TestCase {

	protected void setUp() {
		
	}
	
	/*
	 * Test for 'Location(int, char)'
	 */
	public void testLocation() {
		Location loc = new Location(3, 'B');
		
		assertEquals(loc.row, 3);
		assertEquals(loc.column, 'B');
	}
	
	/*
	 * Test for 'Equals(Object)'
	 */
	public void testEquals() {
		Location loc1 = new Location(1, 'A');
		Location loc2 = new Location(1, 'A');
		Location loc3 = new Location(2, 'B');
		Object obj = new Object();
		
		assertTrue(loc1.equals(loc2));
		assertFalse(loc1.equals(loc3));
		assertFalse(loc1.equals(obj));
	}
	
	/*
	 * Test for 'hashCode()'
	 */
	public void testHashCode() {
		Location loc1 = new Location(2, 'A');
		
		assertEquals(loc1.hashCode(), 265);
	}
	
	/*
	 * Test for 'toString()'
	 */
	public void testToString() {
		Location loc = new Location(2,'B');
		
		assertEquals(loc.toString(), "(B,2)");
	}
}
