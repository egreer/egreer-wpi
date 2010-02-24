/*
* @author Eric Greer      (egreer) 
* @author Jason Codding (jcodding) 
* @date 09/04/08 
* CS 4233-A08 HW1 
*/
package hw1tests;



import static org.junit.Assert.*;
import hw1.Builder;
import hw1.GuitarSpec;
import hw1.Inventory;
import hw1.Type;
import hw1.Wood;


import org.junit.*;

/**
 * This class contains tests for the Inventory class not covered in basic testing.
 * 
 */
public class InventoryTests
{
	private Inventory inventory = null;

	
	@Test
	//tests adding multiple guitars with the same serial number to ensure exception is thrown
	public void testUniqueSerial()
	{
		try {
			inventory = new Inventory();
			inventory.addGuitar("1", 129.95, new GuitarSpec(Builder.GIBSON, "G25", Type.ACOUSTIC, 6,
					Wood.MAPLE, Wood.ALDER));
			inventory.addGuitar("2", 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC, 6,
					Wood.MAHOGANY, Wood.CEDAR));
			inventory.addGuitar("1", 99.99, new GuitarSpec(Builder.FENDER, "F-210", Type.ACOUSTIC, 12,
					Wood.COCOBOLO, Wood.ADIRONDACK));
			fail("Serial number must be unique");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	//tests removing guitars by specifying invalid serial numbers to ensure remove fails
	public void testRemoveInvalidSerial()
	{
		inventory = new Inventory();
		inventory.addGuitar("1", 129.95, new GuitarSpec(Builder.GIBSON, "G25", Type.ACOUSTIC, 6,
				Wood.MAPLE, Wood.ALDER));
		inventory.addGuitar("2", 150.49, new GuitarSpec(Builder.GIBSON, "G40", Type.ELECTRIC, 6,
				Wood.MAHOGANY, Wood.CEDAR));
		assertFalse(inventory.removeGuitar(""));
		assertFalse(inventory.removeGuitar(null));
	
	}
	
}
