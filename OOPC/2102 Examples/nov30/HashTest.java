package nov30;

import java.util.Hashtable;

import junit.framework.TestCase;

/**
 * Determine whether Hashtable can contain null keys or values.
 * 
 * @author heineman
 *
 */
public class HashTest extends TestCase {
	
	/**
	 * Verify that hashtable does not support null keys.
	 */
	public void testNullKeys() {
		Hashtable<BadPoint, String> h = new Hashtable<BadPoint, String>();
		
		try {
			h.put(null, "Test");
			fail ("Hashtable should have thrown Null Pointer Exception");
		} catch (NullPointerException npe) {
			// This is the only time that you leave the handler empty. Why?
			// because in a test case, we were "expecting" the exception!
		}
	}
	
	/**
	 * Verify that hashtable does not support null values.
	 */
	public void testNullValues() {
		Hashtable<String, String> h = new Hashtable<String, String>();
		
		try {
			h.put("test", null);
			fail ("Hashtable should have thrown Null Pointer Exception");
		} catch (NullPointerException npe) {
			// This is the only time that you leave the handler empty. Why?
			// because in a test case, we were "expecting" the exception!
		}
	}
}
