package cs4233.hw2;


import static org.junit.Assert.*;

import org.junit.Test;

import cs4233.hw2.*;


/**
 * This is a more complete set of unit tests for the GuitarSpec class. There is very little behavior
 * in GuitarSpec that needs to be tested beyond the basic tests. The only real behavior in
 * GuitarSpec is in the matches method.
 * 
 * @author gpollice
 */
public class GuitarSpecTest
{
	private GuitarSpec referenceSpec = new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 6,
			Wood.MAHOGANY, Wood.INDIAN_ROSEWOOD);

	@Test
	public void testMatchAnything()
	{
		assertTrue(referenceSpec.matches(new GuitarSpec(null, null, null, 0, null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(null, "  ", null, 0, null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(null, "", null, -1, null, null)));
	}

	@Test
	public void testMatchOneProperty()
	{
		assertTrue(referenceSpec.matches(new GuitarSpec(Builder.RYAN, null, null, 0, null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(null, "model-1", null, 0, null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(null, null, Type.ACOUSTIC, 0, null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(null, null, null, 6, null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(null, null, null, 0, Wood.MAHOGANY, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(null, null, null, 0, null,
				Wood.INDIAN_ROSEWOOD)));
	}

	@Test
	public void testMatchProgressiveProperties()
	{
		assertTrue(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", null, 0, null,
				null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 0,
				null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 6,
				null, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 6,
				Wood.MAHOGANY, null)));
		assertTrue(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 6,
				Wood.MAHOGANY, Wood.INDIAN_ROSEWOOD)));
	}

	@Test
	public void testNoMatchOneProperty()
	{
		assertFalse(referenceSpec
				.matches(new GuitarSpec(Builder.GIBSON, null, null, 0, null, null)));
		assertFalse(referenceSpec.matches(new GuitarSpec(null, "model-2", null, 0, null, null)));
		assertFalse(referenceSpec.matches(new GuitarSpec(null, null, Type.ELECTRIC, 0, null, null)));
		assertFalse(referenceSpec.matches(new GuitarSpec(null, null, null, 12, null, null)));
		assertFalse(referenceSpec.matches(new GuitarSpec(null, null, null, 0,
				Wood.BRAZILIAN_ROSEWOOD, null)));
		assertFalse(referenceSpec.matches(new GuitarSpec(null, null, null, 0, null, Wood.COCOBOLO)));
	}

	@Test
	public void testNoMatchProgressiveProperties()
	{
		assertFalse(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 6,
				Wood.MAHOGANY, Wood.ALDER)));
		assertFalse(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.INDIAN_ROSEWOOD)));
		assertFalse(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ACOUSTIC, 12,
				Wood.MAHOGANY, Wood.INDIAN_ROSEWOOD)));
		assertFalse(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-1", Type.ELECTRIC, 6,
				Wood.MAHOGANY, Wood.INDIAN_ROSEWOOD)));
		assertFalse(referenceSpec.matches(new GuitarSpec(Builder.RYAN, "model-2", Type.ACOUSTIC, 6,
				Wood.MAHOGANY, Wood.INDIAN_ROSEWOOD)));
		assertFalse(referenceSpec.matches(new GuitarSpec(Builder.GIBSON, "model-1", Type.ACOUSTIC,
				6, Wood.MAHOGANY, Wood.INDIAN_ROSEWOOD)));
	}
	
	@Test
	public void testNoMatchToNull()
	{
		try {
			assertFalse(referenceSpec.matches(null));
		} catch (NullPointerException e) {
			throw e;
		} catch (Exception e) {
			// Any other exception is okay
			assertTrue(true);
		}
	}
}
