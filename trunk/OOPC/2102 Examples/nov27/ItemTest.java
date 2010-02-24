package nov27;

import junit.framework.TestCase;

public class ItemTest extends TestCase {

	public void testItemConstruction () {
		HouseholdItem i = new HouseholdItem ("Book", 10);
		assertEquals ("Book", i.name);
		assertEquals (10.0, i.weight);
		
		// Note how this test case "FAILS" it the object is mistakenly
		// constructed without throwing an exception.
		try {
			 i = new HouseholdItem ("Book", -2);
			 fail ("Should have thrown an exception.");
		} catch (IllegalArgumentException iae) {
			// here we now test case has succeeded.
		}
		
		
	}
}
