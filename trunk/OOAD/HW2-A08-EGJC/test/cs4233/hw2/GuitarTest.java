package cs4233.hw2;


import static org.junit.Assert.*;

import org.junit.Test;

import cs4233.hw2.*;


import java.io.*;

/**
 * Tests for the Guitar class that aren't covered by the Guitar tests in the basic tests.
 * 
 * @author gpollice
 */
public class GuitarTest implements Serializable
{
	private GuitarSpec validGuitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC,
			6, Wood.ALDER, Wood.ADIRONDACK);

	@Test
	public void testNegativePrice()
	{
		performInvalidGuitarConstructor("123", -149.95, validGuitarSpec);
		performInvalidGuitarConstructor("123", -0.00000000001, validGuitarSpec);
	}

	@Test
	public void testCreateGuitarWithInvalidSpecFields()
	{
		performGuitarConstructorWithInvalidSpec(Builder.GIBSON, "abc", Type.ELECTRIC, -1,
				Wood.ALDER, Wood.MAHOGANY);
		performGuitarConstructorWithInvalidSpec(null, "", Type.ELECTRIC, 12,
				Wood.BRAZILIAN_ROSEWOOD, Wood.CEDAR);
		performGuitarConstructorWithInvalidSpec(Builder.COLLINGS, "  \t ", Type.ELECTRIC, 6,
				Wood.COCOBOLO, Wood.SITKA);
		performGuitarConstructorWithInvalidSpec(Builder.COLLINGS, null, Type.ELECTRIC, 6,
				Wood.COCOBOLO, Wood.SITKA);
		performGuitarConstructorWithInvalidSpec(Builder.COLLINGS, "Model 1", null, 6,
				Wood.COCOBOLO, Wood.SITKA);
		performGuitarConstructorWithInvalidSpec(Builder.COLLINGS, "Model 1", Type.ELECTRIC, 6,
				null, Wood.SITKA);
		performGuitarConstructorWithInvalidSpec(Builder.COLLINGS, "Model 1", Type.ELECTRIC, 6,
				Wood.COCOBOLO, null);
	}

	@Test
	public void testVerySmallPrice()
	{
		// This is not meaningful, but legal
		new Guitar("123", 0.0000000000001, validGuitarSpec);
		assertTrue(true);
	}
	
	@Test
	public void testSetInvalidPrice()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		Guitar guitar = new Guitar("123", 149.95, guitarSpec);
		try {
			guitar.setPrice(0.0);
			fail("Expected error setting a negative price");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	/******************************* Helper functions ****************************/
	/*
	 * Try to construct a guitar with something wrong in its constructor parameters. This should
	 * throw a RuntimeException.
	 */
	private void performInvalidGuitarConstructor(String serialNumber, double price,
			GuitarSpec guitarSpec)
	{
		try {
			new Guitar(serialNumber, price, guitarSpec);
			fail("Expected exception on Guitar constructor");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	/*
	 * Try to construct a guitar specification with something wrong in its constructor parameters.
	 * This should throw a RuntimeException.
	 */
	private void performGuitarConstructorWithInvalidSpec(Builder builder, String model, Type type,
			int numberOfStrings, Wood backWood, Wood topWood)
	{
		try {
			GuitarSpec guitarSpec = new GuitarSpec(builder, model, type, numberOfStrings, backWood,
					topWood);
			new Guitar("123", 129.95, guitarSpec);
			fail("Expected exception on Guitar constructor with invalid GuitarSpec");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
}
