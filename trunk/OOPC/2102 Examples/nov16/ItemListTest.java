package nov16;

import junit.framework.TestCase;

public class ItemListTest extends TestCase {

	// not necessarily exhaustive, but a start.
	public void testRemove() {
		ItemList list = new ItemList();
		assertEquals (0, list.count());
		
		Item i1 = new Item ("Book", 10);
		list.prepend(i1);
		
		assertEquals (1, list.count());
		
		Item i2 = new Item ("Shelf", 200);
		list.prepend(i2);
		
		assertEquals (2, list.count());
		
		list.remove(i2);
		assertEquals (1, list.count());
		Item i3 = new Item ("Book", 10);
		list.remove(i3);
		assertEquals (0, list.count());
		
	}
	
}
