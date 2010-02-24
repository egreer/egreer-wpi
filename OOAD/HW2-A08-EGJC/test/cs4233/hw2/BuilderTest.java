package cs4233.hw2;


import static org.junit.Assert.*;

import org.junit.Test;

import cs4233.hw2.Builder;



/**
 * Simple unit test for the Builder enum. The only thing that needs to be 
 * tested is to make sure that the names are put correctly as a string.
 * 
 * @author gpollice
 * @version 4-Aug-2008
 */
public class BuilderTest
{

	/**
	 * Test method for {@link Builder#toString()}.
	 */
	@Test
	public void testToString()
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
