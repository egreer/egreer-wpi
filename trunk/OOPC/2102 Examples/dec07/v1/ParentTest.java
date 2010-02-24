package dec07.v1;

import junit.framework.TestCase;

public class ParentTest extends TestCase {
	public void testCreation() {
		// In our example, we create Parent objects FIRST, before creating Children.
		Parent p = new Parent ();
		Child c = new Child (p);
		
		assertEquals (p.getChild(), c);
		assertEquals (c.getParent(), p);

		// c2 takes over where c had been the child of p.
		Child c2 = new Child (p);
		assertEquals (p.getChild(), c2);
		assertEquals (c2.getParent(), p);
		
		// what of 'c'? make sure that it is parent-less
		assertNull(c.getParent());
		
	}
}
