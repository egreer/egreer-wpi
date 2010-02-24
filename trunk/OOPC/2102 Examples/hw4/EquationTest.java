package hw4;

import java.io.File;
import java.io.FileNotFoundException;

import junit.framework.TestCase;
// CH4 + 2O2 = CO2 + 2H2O
public class EquationTest extends TestCase {
	
	public void testBalanced() throws FileNotFoundException {
		PeriodicTable.initialize (new File ("hw4\\elements.txt"));
		Molecule methane = new Molecule(PeriodicTable.get("C"), 1);
		methane.add(PeriodicTable.get("H"), 4);
		
		Molecule oxygen = new Molecule (PeriodicTable.get("O"), 2);
		Molecule carbonDioxide = new Molecule (PeriodicTable.get("C"), 1);
		carbonDioxide.add(PeriodicTable.get("O"), 2);
		
		Molecule water = new Molecule (PeriodicTable.get("H"), 2);
		water.add(PeriodicTable.get("O"), 1);
		
		// empty equations balance...
		MoleculeList list1 = new MoleculeList();
		MoleculeList list2 = new MoleculeList();
		Equation eq = new Equation (list1, list2);
		assert (eq.isBalanced());
		
		list1.add(methane,1);
		eq = new Equation (list1, list2);
		assertFalse (eq.isBalanced());  // out of balance
		list2.add(oxygen,2);
		eq = new Equation (list1, list2);
		assertFalse (eq.isBalanced());  // out of balance still
		
		list1.add(oxygen, 2);
		list2.add(methane, 1);
		
		eq = new Equation (list1, list2);

		// back into balance
		assertTrue (eq.isBalanced());
	}
	
	public void testConstructors () throws FileNotFoundException {
		PeriodicTable.initialize (new File ("hw4\\elements.txt"));
		Molecule methane = new Molecule(PeriodicTable.get("C"), 1);
		methane.add(PeriodicTable.get("H"), 4);
		
		Molecule oxygen = new Molecule (PeriodicTable.get("O"), 2);
		Molecule carbonDioxide = new Molecule (PeriodicTable.get("C"), 1);
		carbonDioxide.add(PeriodicTable.get("O"), 2);
		
		Molecule water = new Molecule (PeriodicTable.get("H"), 2);
		water.add(PeriodicTable.get("O"), 1);
		
		System.out.println (methane);
		MoleculeList list1 = new MoleculeList();
		MoleculeList list2 = new MoleculeList();
		
		list1.add(methane,1);
		list1.add(oxygen, 2);
		list2.add(carbonDioxide, 1);
		list2.add(water, 2);
		
		Equation eq = new Equation (list1, list2);
		
		assertTrue (eq.isBalanced());
	}
}
