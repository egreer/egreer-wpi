package cs4233.hw2;


import static org.junit.Assert.*;

import java.util.List;
import org.junit.*;

import cs4233.hw2.*;


/**
 * This test case contains the unit tests for the Inventory class that go beyond the basic inventory
 * tests.
 * 
 * @author gpollice
 */
public class InventoryTest
{
	private Inventory inventory = null;

	@Before
	public void setUp()
	{
		inventory = new Inventory();
		inventory.addGuitar("1", 129.95, new GuitarSpec(Builder.GIBSON, "G25", Type.ACOUSTIC, 6,
				Wood.MAPLE, Wood.ALDER));
		inventory.addGuitar("2", 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC, 6,
				Wood.MAHOGANY, Wood.CEDAR));
		inventory.addGuitar("3", 99.99, new GuitarSpec(Builder.FENDER, "F-210", Type.ACOUSTIC, 12,
				Wood.COCOBOLO, Wood.ADIRONDACK));
		inventory.addGuitar("4", 238.50, new GuitarSpec(Builder.MARTIN, "MPro", Type.ELECTRIC, 6,
				Wood.MAPLE, Wood.COCOBOLO));
		inventory.addGuitar("5", 500.0, new GuitarSpec(Builder.PRS, "PRS252", Type.ELECTRIC, 8,
				Wood.BRAZILIAN_ROSEWOOD, Wood.SITKA));
		inventory.addGuitar("6", 450.49, new GuitarSpec(Builder.GIBSON, "G50", Type.ACOUSTIC, 6,
				Wood.MAPLE, Wood.CEDAR));
	}

	@Test
	public void testAddGuitarWithDuplicateSerialNumber()
	{
		performInvalidAddGuitar("2", 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC,
				6, Wood.MAHOGANY, Wood.CEDAR));
	}

	@Test
	public void testAddGuitarWithNullSerialNumber()
	{
		performInvalidAddGuitar(null, 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC,
				6, Wood.MAHOGANY, Wood.CEDAR));
	}

	@Test
	public void testAddGuitarWithEmptySerialNumber()
	{
		performInvalidAddGuitar("", 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC, 6,
				Wood.MAHOGANY, Wood.CEDAR));
		performInvalidAddGuitar("   ", 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC,
				6, Wood.MAHOGANY, Wood.CEDAR));
	}
	
	@Test
	public void testAddGuitarWithNullSpec()
	{
		performInvalidAddGuitar("25", 100.0, null);
	}
	
	@Test
	public void testRemoveGuitarWithNullSerialNumber()
	{
		assertFalse(inventory.removeGuitar(null));
	}
	
	@Test
	public void testRemoveGuitarWithEmptySerialNumber()
	{
		assertFalse(inventory.removeGuitar(""));
		assertFalse(inventory.removeGuitar("   "));
	}

	@Test
	public void testNonEmptySearch()
	{
		List<Guitar> matches = inventory.search(new GuitarSpec(Builder.GIBSON, null, Type.ACOUSTIC,
				0, null, null));
		assertEquals(2, matches.size());
		assertTrue(matches.contains(inventory.getGuitar("1")));
		assertTrue(matches.contains(inventory.getGuitar("6")));
	}
	
	@Test
	public void testSearchWithNulGuitarSpec()
	{
		try {
			inventory.search(null);
			fail("Expected error when searching with a null GuitarSpec");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	/************************************ Helper Methods **********************************/
	private void performInvalidAddGuitar(String serialNumber, double price, GuitarSpec spec)
	{
		try {
			inventory.addGuitar(serialNumber, price, spec);
			fail("Expected runtime error adding guitar with invalid or erroneous data");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
}
