package hw3;

import java.io.File;
import java.io.FileNotFoundException;

import junit.framework.TestCase;

public class MoleculeTest extends TestCase {

	/*
	 * Test method for 'hw3.Molecule.Molecule(Element)'
	 */
	public void testMoleculeElement() {
		Element e = new Element ("Hydrogen", "H", 1, 1.00794f);
		Molecule m = new Molecule (e);
		assertEquals ("H", m.toString());

	}

	/*
	 * Test method for 'hw3.Molecule.Molecule(Element, int)'
	 */
	public void testMoleculeElementInt() {
		Element e = new Element ("Hydrogen", "H", 1, 1.00794f);
		Molecule m = new Molecule (e, 2);
		assertEquals ("H2", m.toString());
	}
	

	/*
	 * Test method for 'hw3.Molecule.Molecule.add(Element)'
	 */
	public void testMoleculeAdd() {
		Element h = new Element ("Hydrogen", "H", 1, 1.00794f);
		Element ox = new Element ("Oxygen", "O", 8, 15.9994f);
		Molecule m = new Molecule (h);
		assertEquals ("H", m.toString());
		
		assertTrue (m.add(ox));
		
		assertEquals ("HO", m.toString());
	}

	/*
	 * Test method for 'hw3.Molecule.Molecule(Element[], int[])'
	 */
	public void testMoleculeElementIntArrays() {
		Element h = new Element ("Hydrogen", "H", 1, 1.00794f);
		Element ox = new Element ("Oxygen", "O", 8, 15.9994f);
		
		Molecule m = new Molecule (new Element[] { h, ox, h, ox, h}, 
								   new int[] {2, 3, 4, 5, 6});
		assertEquals ("H2O3H4O5H6", m.toString());
	}
	
	
	/*
	 * Test method for 'hw3.Molecule.add(Element, int)'
	 */
	public void testAdd() throws FileNotFoundException {
		Element e = new Element ("Hydrogen", "H", 1, 1.00794f);
		Molecule m = new Molecule (e, 2);
		assertEquals ("H2", m.toString());
		
		Element ox = new Element ("Oxygen", "O", 8, 15.9994f);
		assertTrue (m.add(ox, 1));
		assertEquals ("H2O", m.toString());
		
		PeriodicTable.initialize(new File ("hw3\\elements.txt"));
		Molecule m2 = new Molecule(PeriodicTable.get("C"), 1);
		assertTrue (m2.add(PeriodicTable.get("H"), 3));
		assertTrue (m2.add(PeriodicTable.get("O")));
		assertTrue (m2.add(PeriodicTable.get("H")));
		
		// valid
		assertEquals ("CH3OH", m2.toString());
		
		// validate a fixed number of adds. Up to numlength
		Molecule mbad = new Molecule(ox, 1);
		for (int i = 0; i < mbad.elements.length - 1; i++) {
			assertTrue (mbad.add(e, (i+1)));
		}
		
		// finally: the next add is to return false;
		assertFalse (mbad.add(e, 99));
	}

	/** Test equals. */
	public void testEquals() throws FileNotFoundException {
		PeriodicTable.initialize(new File ("hw3\\elements.txt"));
		
		// Glucose: C6H12O6
		Molecule m2 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m2.add(PeriodicTable.get("H"), 12));
		assertTrue(m2.add(PeriodicTable.get("O"), 6));

		// different by count.
		Molecule m3 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m3.add(PeriodicTable.get("H"), 12));
		assertTrue(m3.add(PeriodicTable.get("O"), 3));
		
		Molecule m4 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m4.add(PeriodicTable.get("H"), 12));
		assertTrue(m4.add(PeriodicTable.get("O"), 3));
		
		// different by elemnet
		Molecule m5 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m5.add(PeriodicTable.get("N"), 12));
		assertTrue(m5.add(PeriodicTable.get("O"), 3));
		
		Molecule m6 = new Molecule(PeriodicTable.get("C"));
		assertFalse (m2.equals(m3));
		assertTrue (m3.equals(m4));
		assertFalse (m2.equals(m6));
		assertTrue (m6.equals(m6));
		assertFalse (m3.equals(m5));
	}
	
	/**
	 * Hashcodes for equals objects MUST be the same.
	 * 
	 * Hashcodes for objects that are different MAY be the same (and hence cannot
	 * be tested).
	 */
	public void testHashCodes() {
		Molecule m2 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m2.add(PeriodicTable.get("H"), 12));
		assertTrue(m2.add(PeriodicTable.get("O"), 6));
		
		Molecule m3 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m3.add(PeriodicTable.get("H"), 12));
		assertTrue(m3.add(PeriodicTable.get("O"), 3));
		
		Molecule m4 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m4.add(PeriodicTable.get("H"), 12));
		assertTrue(m4.add(PeriodicTable.get("O"), 3));
		
		// compare hashCodes. Since m3 and m4 are equals, then hashCode MUST be equal
		assertEquals (m3.hashCode(), m4.hashCode());

		// if hash code is a different value, then MUST not be equals.
		if (m2.hashCode() != m3.hashCode()) {
			assertFalse (m2.equals(m3));
		}

	}
	
	/*
	 * Test method for 'hw3.Molecule.toString()'
	 */
	public void testWeight() throws FileNotFoundException {
		PeriodicTable.initialize(new File ("hw3\\elements.txt"));
		
		// Glucose: C6H12O6
		Molecule m2 = new Molecule(PeriodicTable.get("C"), 6);
		assertTrue(m2.add(PeriodicTable.get("H"), 12));
		assertTrue(m2.add(PeriodicTable.get("O"), 6));
		
		//assertEquals (m2.getMass(), 181.4292f);
		
		// Table Salt: NaCl
		Molecule m = new Molecule(PeriodicTable.get("Na"));
		assertTrue(m.add(PeriodicTable.get("Cl")));
		assertEquals (m.getMass(), 58.44277f);  // precision: 58.44276928
		
		// another way to do this is to say that they are equal within a tolerance of 0.0001
		assertEquals(m.getMass(), 58.44276928, 0.0001);
	}

}
