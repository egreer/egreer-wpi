package hw4;

import junit.framework.TestCase;

public class MoleculeListTest extends TestCase {

	/*
	 * Test method for 'hw4.MoleculeList.isEmpty()'
	 */
	public void testIsEmpty() { 
		MoleculeList ml = new MoleculeList();
		assertTrue (ml.isEmpty());
	}

	
	/*
	 * Test method for 'hw4.MoleculeList.toString()'
	 * 
	 * This (in passing) tests add and remove as well.
	 */
	public void testToString() {
		
		MoleculeList ml = new MoleculeList();
		assertEquals ("", ml.toString());
		
		Element h = new Element ("Hydrogen", "H", 1, 1.00794f);
		Element ox = new Element ("Oxygen", "O", 8, 15.9994f);
		
		ml.add(new Molecule(h, 3), 1);
		assertEquals ("H3", ml.toString());
		ml.add(new Molecule(ox, 2), 3);
		assertEquals ("H3 + 3O2", ml.toString());
		
		ml.remove (new Molecule(ox, 2));
		assertEquals ("H3 + 2O2", ml.toString());
		
		ml.remove (new Molecule(ox, 2), 2);
		assertEquals ("H3", ml.toString());
	}

	/*
	 * Test method for 'hw4.MoleculeList.remove(Molecule, int)'
	 * 
	 * Focus on testing add and remove. They kinda need to be tested
	 * at the same time.
	 */
	public void testAddAndRemove() {
		MoleculeList ml = new MoleculeList();
		assertEquals ("", ml.toString());
		
		Element h = new Element ("Hydrogen", "H", 1, 1.00794f);
		Element ox = new Element ("Oxygen", "O", 8, 15.9994f);
		
		// add three times, and then remove three times.
		ml.add(new Molecule(h, 3), 1);
		assertEquals ("H3", ml.toString());
		
		// note: this helped identify H3+H3 as invalid.
		ml.add(new Molecule(h, 3), 1);
		assertEquals ("2H3", ml.toString());
		
		ml.add(new Molecule(h, 3), 1);
		assertEquals ("3H3", ml.toString());
		
		ml.remove(new Molecule(h, 3), 1);
		assertEquals ("2H3", ml.toString());
		
		ml.remove(new Molecule(h, 3), 1);
		assertEquals ("H3", ml.toString());

		ml.remove(new Molecule(h, 3), 1);
		assertEquals ("", ml.toString());
		
		// ensure we can't remove from an empty molecule
		assertFalse (ml.remove(new Molecule(h,1), 1));
		
		// ensure we can't remove too many! but when they are removed,
		// that none are left...
		ml.add(new Molecule(h, 3), 5);
		assertFalse (ml.remove(new Molecule(h,3), 7));
		assertEquals ("", ml.toString());
	
		// try to remove,add from end.
		ml.add(new Molecule(h,3), 1);
		ml.add(new Molecule(ox, 2), 3);
		ml.add(new Molecule(h,3), 6);
		
		// this found trailing error in one of my while loops.
		ml.add(new Molecule(ox, 2), 3);
		assertEquals ("7H3 + 6O2", ml.toString());
		ml.add(new Molecule(ox, 2), 3);
		ml.add(new Molecule(h,3), 6);
		assertEquals ("13H3 + 9O2", ml.toString());
		
	}


	/*
	 * Test method for 'hw4.MoleculeList.equals(Object)'
	 */
	public void testEqualsObject() {
		MoleculeList ml = new MoleculeList();
		MoleculeList ml2 = new MoleculeList();
		assertEquals (ml, ml2);
		assertEquals (ml2, ml);  // just checking...

		Element h = new Element ("Hydrogen", "H", 1, 1.00794f);
		Element ox = new Element ("Oxygen", "O", 8, 15.9994f);

		// ml=3O2
		ml.add (new Molecule(ox, 2), 3);
		assertFalse (ml.equals(ml2));
		assertFalse (ml2.equals(ml));
		
		// see if we are dealing properly with molecule counts.
		// This test case detected DEFECT in my .equals() method.
		// ml2=3O2
		ml2.add (new Molecule(ox, 2), 2);
		ml2.add (new Molecule(ox, 2), 1);
		assertEquals (ml, ml2);
		
		// note that equality checks do not work associatively
		// at least, they do not have to work associatively
		// ml = 3O2+5H3
		ml.add (new Molecule(h, 3), 5);
		// ml = 3O2+6H3
		ml2.add (new Molecule(h, 3), 6);
		// ml= 3O2+5H3
		ml2.remove(new Molecule (h, 3));
		
		assertEquals (ml, ml2);
	}


}
