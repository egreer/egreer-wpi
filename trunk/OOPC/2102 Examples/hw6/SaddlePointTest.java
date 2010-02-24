package hw6;

import junit.framework.TestCase;

public class SaddlePointTest extends TestCase {
	/**
	 * Test that saddle points can be found.
	 */
	public void testPositiveSaddlePoints () {
		double [][]ar = new double[][] {
				{4, 2},
				{1, 3}
		};
		
		// this has no saddle point. Note that 2 is smallest in its row, but not largest 
		// in its column. Note that 1 is smallest in its row, but not largest in its column.
		assertNull (SaddlePoint.computeSaddlePoint(ar));
		
		double [][]ar2 = new double[][] {
				{3, 4},
				{1, 3}
		};
		
		// this has no saddle point. Note that 2 is smallest in its row, but not largest 
		// in its column. Note that 1 is smallest in its row, but not largest in its column.
		assertEquals ("[0][0]", SaddlePoint.computeSaddlePoint(ar2).toString());
	}
}
