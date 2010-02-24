package cs4233.hw2;


import static org.junit.Assert.*;

import org.junit.Test;

import cs4233.hw2.Type;



/**
 * Simple unit test for the Type enum. The only thing that needs to be 
 * tested is to make sure that the names are put correctly as a string.
 * 
 * @author gpollice
 * @version 4-Aug-2008
 */
public class TypeTest
{

	/**
	 * Test method for {@link Type#toString()}.
	 */
	@Test
	public void testToString()
	{
		assertEquals("acoustic", Type.ACOUSTIC.toString());
		assertEquals("electric", Type.ELECTRIC.toString());
	}

}
