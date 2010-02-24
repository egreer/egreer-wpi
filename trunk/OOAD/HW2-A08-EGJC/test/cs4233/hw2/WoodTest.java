package cs4233.hw2;


import static org.junit.Assert.*;

import org.junit.Test;

import cs4233.hw2.Wood;



/**
 * Simple unit test for the Wood enum. The only thing that needs to be 
 * tested is to make sure that the names are put correctly as a string.
 * 
 * @author gpollice
 * @version 4-Aug-2008
 */
public class WoodTest
{

	/**
	 * Simple unit test for the Type enum. The only thing that needs to be 
	 * tested is to make sure that the names are put correctly as a string.
	 * 
	 * @author gpollice
	 * @version 4-Aug-2008
	 */
	@Test
	public void testToString()
	{
		assertEquals("Indian Rosewood", Wood.INDIAN_ROSEWOOD.toString());
		assertEquals("Brazilian Rosewood", Wood.BRAZILIAN_ROSEWOOD.toString());
		assertEquals("Mahogany", Wood.MAHOGANY.toString());
		assertEquals("Maple", Wood.MAPLE.toString());
		assertEquals("Cocobolo", Wood.COCOBOLO.toString());
		assertEquals("Cedar", Wood.CEDAR.toString());
		assertEquals("Adirondack", Wood.ADIRONDACK.toString());
		assertEquals("Alder", Wood.ALDER.toString());
		assertEquals("Sitka", Wood.SITKA.toString());
	}

}
