package hw3;

import junit.framework.TestCase;

public class ElementTest extends TestCase {

	public void testConstructors () {
		// String symbol, int number, float mass
		Element e;
		
		e = new Element("Hydrogen", "H", 1, 1.00794f);
		assertEquals (e.toString(), "H 1.00794");
		assertEquals (e.getName(), "Hydrogen");
		assertEquals (e.getNumber(), 1);
		assertEquals (e.getSymbol(), "H");
		assertEquals (e.isUnstable(), false);
		
		// 84 Po Polonium [209]
		e = new Element ("Polonium", "Po", 84, 209, false);
		assertEquals (e.toString(), "Po [209]");
		assertEquals (e.isUnstable(), true);
	}
	
	public void testToString() {
		Element e1 = new Element ("Polonium", "Po", 84, 209, true);
		Element e2 = new Element ("Polonium", "Po", 84, 209, false);
		
		// make sure that toString values are not the same for these two.
		assertFalse (e1.toString().equals (e2.toString()));
	}
	
	public void testEquals() {
		Element e1 = new Element("Hydrogen", "H", 1, 1.00794f);
		Element e2 = new Element("Hydrogen", "H", 1, 1.00794f);
		Element e3 = new Element("Hydrogen", "H", 1, 1.00792f);

		// as expected.
		assertEquals (e1, e2);
		assertFalse(e1.equals (e3));  // only changed the mass, but kept symbol the same.
	}
	
	public void testHashcode() {
		Element e1 = new Element("Hydrogen", "H", 1, 1.00794f);
		Element e2 = new Element("Hydrogen", "H", 1, 1.00794f);
		assertEquals (e1.hashCode(), e2.hashCode());
	}
}

/**
1 H Hydrogen 1.00794
2 He Helium 4.002602
3 Li Lithium 6.941
4 Be Beryllium 9.012182
*/