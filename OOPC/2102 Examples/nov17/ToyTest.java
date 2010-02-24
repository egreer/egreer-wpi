package nov17;

import junit.framework.TestCase;

public class ToyTest extends TestCase {
	
	// test example.
	public void testThis() {
		Toy t = new Toy ();
		t.add (new Draw (5, 5));
		t.add (new Draw (2, -3));
		t.add (new Move (-2, 4));
		t.add (new Draw (0, 0));
		
		t.draw();

	}
}
