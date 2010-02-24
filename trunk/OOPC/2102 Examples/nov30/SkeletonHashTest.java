package nov30;

import junit.framework.TestCase;

public class SkeletonHashTest extends TestCase {
	public void testPut() {
		SkeletonHashTable table = new SkeletonHashTable();
		
		table.put("Test", "here");
		assertEquals ("here", table.get("Test"));
		assertNull (table.get("NoKeyYet"));
		
		table.put("Test", "other");
		assertEquals ("other", table.get("Test"));
	}
}
