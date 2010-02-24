package nov09;

import junit.framework.TestCase;

public class StringListTest extends TestCase {
	/**
	 * This JUnit test method must start with lower-case 'test'
	 *
	 */
	public void testClass() {
		/** Confirm Empty List has empty head. */
		StringList list = new StringList();
		
		// Empty list expects head to be null
		assertNull(list.head);
		
		// add an element.
		list.prepend ("Test");
		
		// List with an element expects head not to be null
		assertNotNull(list.head);
	}
}
