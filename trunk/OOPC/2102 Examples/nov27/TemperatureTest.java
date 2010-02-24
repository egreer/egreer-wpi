package nov27;

import junit.framework.TestCase;

public class TemperatureTest extends TestCase {
	/**
	 * Only tets Comparable interface
	 *
	 */
	public void testComparison () {
		
		
		// standard conversions
		FahrenheitTemperature ft = new FahrenheitTemperature(212.0);
		assertEquals (0, ft.compareTo(ft));
		
		CelsiusTemperature ct = new CelsiusTemperature(100.0);
		
		assertEquals (0, ct.compareTo(ft));
		assertEquals (0, ft.compareTo(ct));
		
		// validate CompareTo
		CelsiusTemperature ct2 = new CelsiusTemperature(99);
		assertTrue (ct.compareTo(ct2) > 0);
		assertTrue (ct2.compareTo(ct) < 0);
		assertTrue (ft.compareTo(ct2) > 0);   // ensure mixed case as well....
	}
}
