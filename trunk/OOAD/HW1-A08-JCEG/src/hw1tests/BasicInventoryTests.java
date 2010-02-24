/*
 * Created on Aug 3, 2008
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
import hw1.Inventory;
import hw1.Type;
import hw1.Wood;

import java.util.List;

import org.junit.*;

/**
 * This class contains the smoke tests for the Inventory class.
 * 
 * @author gpollice
 */
public class BasicInventoryTests
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
	}

	@Test
	public void testMatchingSearch()
	{
		List<Guitar> matches = inventory.search(new GuitarSpec(Builder.GIBSON, null, null, 0, null, null));
		assertEquals(2, matches.size());
		assertTrue(matches.contains(inventory.getGuitar("1")));
		assertTrue(matches.contains(inventory.getGuitar("2")));
		matches = inventory.search(new GuitarSpec(null, null, Type.ELECTRIC, 0, null, null));
		assertEquals(1, matches.size());
		assertTrue(matches.contains(inventory.getGuitar("2")));
	}

	@Test
	public void testRemoveGuitar()
	{
		assertTrue(inventory.removeGuitar("2"));
	}
	
	@Test
	public void removeNonExistentGuitar()
	{
		assertFalse(inventory.removeGuitar("42"));
	}
}
