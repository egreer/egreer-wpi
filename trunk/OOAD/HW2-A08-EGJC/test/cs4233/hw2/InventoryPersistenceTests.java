package cs4233.hw2;


import static org.junit.Assert.*;

import java.io.*;
import org.junit.*;

import cs4233.hw2.*;


/**
 * Tests for inventory persistence.
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @date 09-10-08
 * CS 4233-A08 HW2
 */
public class InventoryPersistenceTests
{
	public Inventory inventory = new Inventory();
	
	/* Initial Setup of inventory */
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

	/* Tests that saving the inventory doesn't destroy the inventory*/
	@Test
	public void saveInventory() throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		inventory.save(oos);
		assertNotNull(inventory.getGuitar("82733"));

	}
	
	/* Tests initializing an inventory */
	@Test 
	public void initalizeInventory() throws Exception{
				
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		inventory.save(oos);
		byte[] inventoryAsBytes = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(inventoryAsBytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		
		Inventory inventory1 = new Inventory();
		inventory1.initialize(ois);
		assertNotNull(inventory.search(new GuitarSpec(Builder.GIBSON, "", null, 0, null, null)));
	}
	
	/* Guitars that are removed after a save shouldn't effect the inventory if the 
	 * save is loaded into another inventory 
	 */
	@Test
	public void savedInventoryUneffected() throws Exception{
		Inventory inventory4 = inventory; 
		Inventory inventory5 = new Inventory();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		inventory4.save(oos);
		inventory4.removeGuitar("58732");
		byte[] inventoryAsBytes = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(inventoryAsBytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		inventory5.initialize(ois);
		
		assertNotNull(inventory5.getGuitar("58732"));
	}
	

	/* Inventories containing guitars should not be overwritten by initialize*/
	@Test 
	public void initalizeNonEmptyInventory() throws Exception{
		Inventory inventory1 = inventory;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		inventory1.save(oos);
		byte[] inventoryAsBytes = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(inventoryAsBytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		
		try{
		inventory1.initialize(ois);
		
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	/* Objects that are not of type LinkedList<Guitar> shouldn't be valid to 
	 * initialize an inventory */
	@Test
	public void invalidGuitarList() throws Exception{
		Inventory inventory2 = new Inventory();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(new GuitarSpec(Builder.PRS, "", null, 0, null, Wood.SITKA));
		byte[] inventoryAsBytes = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(inventoryAsBytes);
		ObjectInputStream ois = new ObjectInputStream(bais);

		try{
			inventory2.initialize(ois);

		} catch (RuntimeException e) {
			assertTrue(true);
		}

	}
}
