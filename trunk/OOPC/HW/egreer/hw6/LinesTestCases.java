package egreer.hw6;

import junit.framework.TestCase;

public class LinesTestCases extends TestCase {

	/**
	 * Tests the intersection method
	 * 
	 */
	public void testIntersection() {
		
		/**These tests are for parrell */
		
		//Tests that two lines are obviously parrell in positive direction
		int type = Lines.intersection(-1, -1, 1, 1,	-1, -2, 1, 0);
		assertEquals(1, type);
		
		//Tests that two lines are parrell in positive diection, but not same length
		type = Lines.intersection(0, 0, 2, 2, -1, 0, 3, 4);
		assertEquals(1, type);
		
		//Tests that two lines are parrell in positive direction, but have points that draw the line from opposite direction
		type = Lines.intersection(0, 0, 2, 2, 3, 4, -1, 0);
		assertEquals(1, type);
		
		//Tests that two lines are parrell in positive direction, but not near each other
		type = Lines.intersection(-5, -5, 0, 0, 0, 5, 5, 10);
		assertEquals(1, type);
				
		//Tests that two lines are obviously parrell in negative direction
		type = Lines.intersection(0, 0, -1, 1, 1, 0, 0, 1);
		assertEquals(1, type);
		
		//Tests that two line are parrell in negative direction, but are not same length. 
		type = Lines.intersection(0, 0, -1, 1, 2, -1, -1, 2);
		assertEquals(1, type);
		
		//Tests that two lines are parrell in negative direction, but have points that draw the line from opposite direction
		type = Lines.intersection(0, 0, -1, 1, 0, 1, 1, 0);
		assertEquals(1, type);
		
		//Tests that two lines are parrell in negative direction, but not near each other
		type = Lines.intersection(5, -5, 0, 0, 0, 5, -5, 10);
		assertEquals(1,type);
		
		
		/**These tests are for coincident*/
		//Tests that two lines are obviously coincident in the positive direction
		type = Lines.intersection(0, 0, 1, 1, 1, 1, 2, 2);
		assertEquals(2, type);
		
		//Tests that two lines are coincident  in the positive direction, but don't have a shared argument
		type = Lines.intersection(-2, -2, -1, -1, 1, 1, 2, 2);
		assertEquals(2, type);
		
		//Tests that two lines are coincident in the positive direction across a large distance
		type = Lines.intersection(-1, -1, 0, 0, 5, 5, 10, 10);
		assertEquals(2, type);
		
		//Tests that two lines are coincident in the positive direction, but have points that draw the line in the opposite direction
		type = Lines.intersection(-1, -1, 0, 0, 2, 2, 1, 1);
		assertEquals(2, type);
		
		//Tests that two lines are obviously coincident in the negative direction
		type = Lines.intersection(1, -1, 0, 0, 0, 0, -1, 1);
		assertEquals(2, type);
		
		//Tests that two lines are coincident  in the negative direction, but don't have a shared argument
		type = Lines.intersection(2, -2, 1, -1, -1, 1, -2, 2);
		assertEquals(2, type);
		
		//Tests that two lines are coincident in the negative direction across a large distance
		type = Lines.intersection(1, -1, 0, 0, 5, -5, -10, 10);
		assertEquals(2, type);
		
		//Tests that two lines are coincident in the negative direction, but have points that draw the line in the opposite direction
		type = Lines.intersection(1, -1, 0, 0, -2, 2, -1, 1);
		assertEquals(2, type);
		
		
		/**These tests are for intersecting*/
		//Tests that two lines are obviously intersecting
		type = Lines.intersection(-1, -1, 1, 1,	1, -1, -1, 1);
		assertEquals(1, type);
		
		//Tests that two lines are intersecting, but travel in same direction 
		type = Lines.intersection(0, 0, 2, 2, 1, 0, 2,3);
		assertEquals(1, type);
		
	
		
		/**These tests are for exceptions */
		
		//Tests exception
		assertEquals("Not Yet Implemented", Lines.intersection(1,1,1,1,1,1,1,1));
	}

}
