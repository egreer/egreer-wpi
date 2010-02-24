package nov29;

import junit.framework.TestCase;

/**
 * Fill in the details of this test class.
 * 
 * @author ENTER NAME HERE
 */
public class NumberListTest extends TestCase {
	
	/**
	 * Test the add method.
	 * 
	 * These seem to cover all possible cases.
	 * 
	 * Use assertEquals ("string", nl.toString()) where "string" is the expected
	 * output from the toString method.
	 * 
	 * Write the missing statements and build up a number list from scratch to test
	 * each of the specified cases. You should convince yourself that you have
	 * evaluated each potential situation that could arise when inserting an int
	 * into the NumberList.
	 */
	public void testAdd() {
		// start with empty list
		NumberList nl = new NumberList();
		assertEquals ("{}", nl.toString());
		
		// insert on an empty list.
		// fill in 
		
		// insert at the front of the list
		// fill in 
		
		// insert somewhere in the middle of the list.
		// fill in 
		
		// insert as the last one in the list.
		// fill in 
		
		// insert same value as already in the list (but first)
		// fill in 
		
		// insert same value as already in the list (but somewhere in middle)
		// fill in 
		
		// insert same value as already in the list (but last one)
		// fill in 
	}
}
