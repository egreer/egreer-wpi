package amida;

import junit.framework.TestCase;

/**
 * Comprehensive Test cases for Edge.
 * 
 * @author heineman
 */
public class EdgeTest extends TestCase {

	public void testBadConstructor() {
		// First try the bad values, one at a time.
		try {
			new Edge (-2, 10);   // invalid line
			fail ("Failed to detect invalid line");
		} catch (AmidaException e) {
			// This is a test case to detect FAILURE. So this is success
		}
		
		// Try illegal vertical position (anything <= 0)
		try {
			new Edge (2, -10);   // invalid line
			fail ("Failed to detect invalid verticalPosition");
		} catch (AmidaException e) {
			// This is a test case to detect FAILURE. So this is success
		}
		
	}
	
	public void testGoodConstructor() {
		// First try the bad values, one at a time.
		try {
			Edge e = new Edge (2, 10);   // invalid line
			assertEquals (2, e.getStartLine());
			assertEquals (3, e.getEndLine());
			assertEquals (10, e.getVerticalPosition());
			
		} catch (AmidaException e) {
			fail ("AmidaException unexpected:" + e.getMessage());
		}
		
		// Try illegal vertical position (anything <= 0)
		try {
			new Edge (2, -10);   // invalid line
			fail ("Failed to detect invalid verticalPosition");
		} catch (AmidaException e) {
			// This is a test case to detect FAILURE. So this is success
		}
	}
	
	public void testToString() {
		try {
			Edge e = new Edge (2, 10);
			assertEquals ("[2,3 @ 10]", e.toString());
		} catch (AmidaException ae) {
			fail ("Not supposed to throw exception:" + ae.getMessage());
		}
	}
	
	public void testEquals() {
		try {
			Edge e = new Edge (2, 10);
			Edge e2 = new Edge (2, 10);
			Edge e3 = new Edge (2, 10);
			assertEquals (e, e);    // reflexivity
			assertEquals (e, e2);   
			assertEquals (e2, e3);
			assertEquals (e, e3);   // transitivity
		} catch (AmidaException ae) {
			fail ("Not supposed to throw exception:" + ae.getMessage());
		}
		
		// try when not equals
		try {
			Edge e = new Edge (2, 10);
			Edge e2 = new Edge (3, 10);  // different on line
			Edge e3 = new Edge (2, 22);  // different on vertical position
			
			assertFalse (e.equals(e2));
			assertFalse (e2.equals(e));
			assertFalse (e.equals(e3));
			assertFalse (e3.equals(e));
		} catch (AmidaException ae) {
			fail ("Not supposed to throw exception:" + ae.getMessage());
		}
		
		// try with null and with non-Edge objects
		try {
			Edge e = new Edge (2, 10);
			String s = new String ("nonEdge");
			assertFalse (e.equals (null));
			assertFalse (e.equals (s));
		} catch (AmidaException ae) {
			fail ("Not supposed to throw exception:" + ae.getMessage());
		}
		
	}
	
}
