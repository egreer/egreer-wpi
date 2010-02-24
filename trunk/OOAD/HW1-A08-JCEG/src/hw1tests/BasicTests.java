/*
 * Created on Aug 2, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package hw1tests;

import static org.junit.Assert.*;
import hw1.Builder;
import hw1.Guitar;
import hw1.GuitarSpec;
import hw1.Type;
import hw1.Wood;

import org.junit.Test;

/**
 * Basic "smoke" tests for homework 1. The code should throw a RuntimeException when an error occurs
 * and the method does not return a value that can be checked, such as in constructors.
 * 
 * @author gpollice
 */
public class BasicTests
{
	/* GuitarSpec tests */
	@Test
	public void testCreateValidGuitarSpec()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		assertNotNull(guitarSpec);
		assertEquals(Builder.FENDER, guitarSpec.getBuilder());
		assertEquals("Ear Wax", guitarSpec.getModel());
		assertEquals(Type.ACOUSTIC, guitarSpec.getType());
		assertEquals(6, guitarSpec.getNumStrings());
		assertEquals(Wood.ALDER, guitarSpec.getBackWood());
		assertEquals(Wood.ADIRONDACK, guitarSpec.getTopWood());
	}

	@Test
	public void testCompleteMatchGuitarSpec()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		assertTrue(guitarSpec.matches(guitarSpec));
		assertFalse(guitarSpec.matches(new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.INDIAN_ROSEWOOD)));
	}
	
	@Test
	public void testPartialSpecMatch()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		assertTrue(guitarSpec.matches(new GuitarSpec(Builder.FENDER, null, null, 0, null, null)));
		assertTrue(guitarSpec.matches(new GuitarSpec(Builder.FENDER, " ", null, 0, null, null)));
		assertFalse(guitarSpec.matches(new GuitarSpec(Builder.GIBSON, " ", null, 0, null, null)));
	}

	/* Guitar tests */
	@Test
	public void testCreateValidGuitar()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		Guitar guitar = new Guitar("123", 149.95, guitarSpec);
		assertNotNull(guitar);
		assertEquals("123", guitar.getSerialNumber());
		assertEquals(149.95, guitar.getPrice(), 0.0);
		assertEquals(guitarSpec, guitar.getSpec());
	}

	/* Fields in the Guitar must have non-null and non-blank values */
	@Test
	public void testCreateGuitarWithNullFields()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		/* Bad serial number */
		performInvalidGuitarConstructor(null, 149.95, guitarSpec);
		/* Nothing is free */
		performInvalidGuitarConstructor("123", 0, guitarSpec);
		/* Invalid field in the GuitarSpec */
	}

	@Test
	public void testCreateGuitarWithInvalidSpecFields()
	{
		performGuitarConstructorWithInvalidSpec(null, null, null, 0, null, null);
		performGuitarConstructorWithInvalidSpec(Builder.GIBSON, "", Type.ELECTRIC, 12,
				Wood.BRAZILIAN_ROSEWOOD, Wood.CEDAR);
		performGuitarConstructorWithInvalidSpec(Builder.COLLINGS, "Model 1", Type.ELECTRIC, 0,
				Wood.COCOBOLO, Wood.SITKA);
	}

	@Test
	public void testSetValidPrice()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		Guitar guitar = new Guitar("123", 149.95, guitarSpec);
		guitar.setPrice(199.99);
		assertEquals(199.99, guitar.getPrice(), 0.0);
	}

	@Test
	public void testSetInvalidPrice()
	{
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Ear Wax", Type.ACOUSTIC, 6,
				Wood.ALDER, Wood.ADIRONDACK);
		Guitar guitar = new Guitar("123", 149.95, guitarSpec);
		try {
			guitar.setPrice(-199.99);
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
