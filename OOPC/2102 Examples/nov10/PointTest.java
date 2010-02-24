package nov10;

import junit.framework.TestCase;

public class PointTest extends TestCase {

	public void testConstructor () {
		Point p = new Point (10,20);
		
		assertEquals (p.toString(), "(10.0,20.0)");
	}
	
	/** tests use of constructor with a String argument. */
	public void testConstructorWithString () {
		Point p = new Point ("20 30");
		
		assertEquals (p.toString(), "(20.0,30.0)");
	}
}
