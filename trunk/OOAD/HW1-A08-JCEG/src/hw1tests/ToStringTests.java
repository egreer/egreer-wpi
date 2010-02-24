/*
* @author Eric Greer      (egreer) 
* @author Jason Codding (jcodding) 
* @date 09/04/08 
* CS 4233-A08 HW1 
*/
package hw1tests;

import static org.junit.Assert.*;
import hw1.Builder;
import hw1.Type;
import hw1.Wood;
import org.junit.*;

/**
 * This class tests the toString classes of the guitar type, wood, and build
 * 
 */
public class ToStringTests
{

	@Test
	//tests toString of the guitar type
	public void testType()
	{
		assertEquals("acoustic", Type.ACOUSTIC.toString());
		assertEquals("electric", Type.ELECTRIC.toString());

	}
	

	
	@Test
	//tests toString of wood types
	public void testWood()
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
	
	
	@Test
	//tests toString of the build types
	public void testBuilder()
	{
		assertEquals("Fender", Builder.FENDER.toString());
		assertEquals("Martin", Builder.MARTIN.toString());
		assertEquals("Gibson", Builder.GIBSON.toString());
		assertEquals("Collings", Builder.COLLINGS.toString());
		assertEquals("Olson", Builder.OLSON.toString());
		assertEquals("Ryan", Builder.RYAN.toString());
		assertEquals("PRS", Builder.PRS.toString());

	}

}
