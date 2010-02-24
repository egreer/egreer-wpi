package egreer.lab4;

import junit.framework.TestCase;

/**
 * Fill in the details of this test class.
 * 
 * @author egreer
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
		nl.insert(3);
		assertEquals ("{3}", nl.toString()); 
		
		// insert at the front of the list
		nl.insert(1);
		assertEquals ("{1,3}", nl.toString()); 
		
		// insert somewhere in the middle of the list.
		nl.insert(2);
		assertEquals ("{1,2,3}", nl.toString()); 
		
		// insert as the last one in the list.
		nl.insert(4);
		assertEquals ("{1,2,3,4}", nl.toString());
		
		// insert same value as already in the list (but first)
		nl.insert(1);
		assertEquals ("{1,1,2,3,4}", nl.toString());
		
		// insert same value as already in the list (but somewhere in middle)
		nl.insert(2);
		assertEquals ("{1,1,2,2,3,4}", nl.toString());
		
		// insert same value as already in the list (but last one)
		nl.insert(4);
		assertEquals ("{1,1,2,2,3,4,4}", nl.toString());
	}
}