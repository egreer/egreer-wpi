package lpf.model.core;

import junit.framework.TestCase;

public class TestArraySet extends TestCase {
	public void testAdd() {
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(4);
		set.add(4);
		
		assertTrue(set.contains(4));
		
		set.remove(4);
		
		assertFalse(set.contains(4));
	}
}
