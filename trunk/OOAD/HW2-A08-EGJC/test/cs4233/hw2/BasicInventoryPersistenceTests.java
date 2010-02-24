package cs4233.hw2;


import static org.junit.Assert.*;

import java.io.*;
import org.junit.*;

import cs4233.hw2.*;


/**
 * Basic tests for the inventory persistence. Homework 2, CS4233-A08.
 * 
 * @author gpollice
 */
public class BasicInventoryPersistenceTests
{
	public Inventory inventory = new Inventory();

	@Before
	public void setUp()
	{
		inventory.addGuitar("123", 149.95, new GuitarSpec(Builder.COLLINGS, "C1", Type.ACOUSTIC, 6,
				Wood.CEDAR, Wood.ALDER));
		inventory.addGuitar("58732", 299.95, new GuitarSpec(Builder.FENDER, "F1", Type.ELECTRIC, 6,
				Wood.COCOBOLO, Wood.MAHOGANY));
		inventory.addGuitar("2389", 567.75, new GuitarSpec(Builder.GIBSON, "G-ER", Type.ELECTRIC, 6,
				Wood.MAPLE, Wood.ADIRONDACK));
		inventory.addGuitar("82733", 159.99, new GuitarSpec(Builder.RYAN, "Musica", Type.ACOUSTIC, 12,
				Wood.CEDAR, Wood.ALDER));
	}

	@Test
	public void basicSaveAndInitializeInventory() throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		inventory.save(oos);
		byte[] inventoryAsBytes = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(inventoryAsBytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Inventory inventory1 = new Inventory();
		inventory1.initialize(ois);
		assertNotNull(inventory1.getGuitar("123"));
	}
}
