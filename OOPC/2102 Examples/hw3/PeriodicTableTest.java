package hw3;

import java.io.File;
import java.io.FileNotFoundException;

import junit.framework.TestCase;

public class PeriodicTableTest extends TestCase {
	/**
	 * Test initialization of Periodic table.
	 * @throws FileNotFoundException 
	 */
	public void testInitialization () throws FileNotFoundException {
		PeriodicTable.initialize(new File ("hw3\\elements.txt"));
		
		Element e = PeriodicTable.get("H");		
		assertTrue (e != null);
		assertEquals (e, e);
		e = PeriodicTable.get("Skldj");
		assertNull (e);
		
		e = PeriodicTable.get("Uuo");
		assertTrue (e != null);
		assertEquals (e.getMass(), 294.0f);
	}
	
	// can't confirm, But at least know that no error/exception occured.
	public void testOutput () throws FileNotFoundException {
		PeriodicTable.initialize(new File ("hw3\\elements.txt"));
		
		PeriodicTable.outputElements();
	}
}
