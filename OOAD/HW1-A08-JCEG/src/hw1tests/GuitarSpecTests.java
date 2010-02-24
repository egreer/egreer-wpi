/*
* @author Eric Greer      (egreer) 
* @author Jason Codding (jcodding) 
* @date 09/04/08 
* CS 4233-A08 HW1 
*/
package hw1tests;

import static org.junit.Assert.*;

import hw1.Builder;
import hw1.Guitar;
import hw1.GuitarSpec;
import hw1.Inventory;
import hw1.Type;
import hw1.Wood;

import java.util.List;

import org.junit.*;

/**
 * This class contains the tests to confirm the guitarSpec class is working properly.
 * 
 */
public class GuitarSpecTests
{
	private Inventory inventory = null;

	@Before
	//prepares the inventory with guitars for testing
	public void setUp()
	{
		inventory = new Inventory();
		inventory.addGuitar("1", 129.95, new GuitarSpec(Builder.GIBSON, "G25", Type.ACOUSTIC, 6,
				Wood.MAPLE, Wood.ALDER));
		inventory.addGuitar("2", 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC, 6,
				Wood.MAHOGANY, Wood.CEDAR));
		inventory.addGuitar("3", 99.99, new GuitarSpec(Builder.FENDER, "F-210", Type.ACOUSTIC, 12,
				Wood.COCOBOLO, Wood.ADIRONDACK));
	}

	@Test
	//Tests the search capability with null values not previously tested in basic testing
	public void testNullSearch()
	{
		List<Guitar> matches = inventory.search(new GuitarSpec(null, "", Type.ELECTRIC, 0, null, null));
		assertEquals(0, matches.size());

	}
	
	@Test
	//tests the search capability with string number specified
	public void testNumStringSearch()
	{
		List<Guitar> matches = inventory.search(new GuitarSpec(Builder.GIBSON, "G25", Type.ACOUSTIC, 8, null, null));
		assertEquals(0, matches.size());
	}
	
	@Test
	//tests the search capability with a backwood specified
	public void testBackWoodSearch()
	{
		List<Guitar> matches = inventory.search(new GuitarSpec(Builder.GIBSON, "G25", Type.ACOUSTIC, 6, Wood.CEDAR, null));
		assertEquals(0, matches.size());
		assertEquals("Gibson", Builder.GIBSON.toString());
		assertEquals("acoustic", Type.ACOUSTIC.toString());
	}

	@Test
	//tests the validity of guitar spec arrangements not previously tested in basic testing
	public void testValidity()
	{
		GuitarSpec specs = new GuitarSpec(Builder.GIBSON, "G25", null, 6, null, null);
		assertFalse(specs.isValid());
		GuitarSpec specs2 = new GuitarSpec(Builder.GIBSON, "G25", Type.ELECTRIC, 6, null, null);
		assertFalse(specs2.isValid());
		GuitarSpec specs3 = new GuitarSpec(Builder.GIBSON, "G25", Type.ELECTRIC, 6, Wood.ALDER, null);
		assertFalse(specs3.isValid());
	}

}
