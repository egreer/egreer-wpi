package nov14;

import junit.framework.TestCase;

public class InventoryTest extends TestCase {

	/** Create the inventory. */
	public void testCase() {
		Inventory inv = new Inventory();
		
		assertEquals (inv.count(), 0);
	}
	
	/** Test a sequence of adds. */
	public void testAdds() {
		Inventory inv = new Inventory();
		
		Item it = new Item ("Book", 2.0);
		inv.add(it);
		
		assertEquals (inv.count(), 1);
		assertEquals (inv.getWeight(), 2.0);
		
		it = new Item ("Shelf", 200.0);
		inv.add(it);
		
		assertEquals (inv.count(), 2);
		assertEquals (inv.getWeight(), 202.0);
	}
	
	public void testOutput () {
		Inventory inv = new Inventory();
		Item it = new Item ("Apple iPod", 1.0);
		inv.add (it);
		
		it = new Item ("Book", 2.0);
		inv.add(it);
		
		it = new Item ("Car", 1200.0);
		inv.add(it);
		
		assertEquals (inv.count(), 3);
		assertEquals (inv.getWeight(), 1203.0);
		
		inv.output();
	}
	
	public void testMixedTypes () {
		Inventory inv = new Inventory();
		Item it = new Item ("Apple iPod", 1.0);
		inv.add (it);
		
		it = new BusinessItem ("Computer", 2.0, 1000.00);
		inv.add(it);
		
		it = new Item ("Car", 1200.0);
		inv.add(it);
		
		assertEquals (inv.count(), 3);
		assertEquals (inv.getWeight(), 1203.0);
		
		inv.output();
	}
}
