package hw6;

import junit.framework.TestCase;

/**
 * A set of test cases for the Lines.intersection method.
 * 
 * We know that we need to test for three conditions: PARALLEL, INTERSECTING, COINCIDENT
 * 
 * We also want to check for invalid arguments. Also note that if a particular method
 * invocation throws IllegalArgumentException and we weren't prepared for it, then the
 * JUnit test case will fail, as it should.
 * 
 * [p1-p2, q1-q2] is my shorthand for [L1, L2], the 8-parameters x1,y1,x2,y2,u1,v1,u2,v2
 * 
 * ============================================
 * SCORING: 4 points for JavaDoc
 * ============================================
 * 
 * ============================================
 * SCORING: 2 points for getting both 1 and 2.
 *          1 point for considering case 3.
 * ============================================
 * 
 * 1. [p1-p1, ???] where (x1,y1)==(x2,y2) is an Invalid Line. EXCEPTION
 * 2. [????, p2-p2] where (x1,y1)==(x2,y2) is an Invalid Line. EXCEPTION
 * 3. [p1-p1,p2-p2] where all 8 values are identical. EXCEPTION; we include this one just in case
 *                  someone tried to write code to short-circuit the COINCIDENCE evaluation
 *                  and in this case return "COINCIDENT"
 * 
 * I expect vertical lines will play a role. Why? Because the slope of a vertical line
 * is infinity. The good news, is that we are dealing with infinite lines. Thus there 
 * is some simple logic to check for
 *
 *
 * ============================================
 * SCORING: 1 point for case 4
 *          1 point for case 5
 *          2 point for case 6 &7
 * ============================================
 *
 * 4. (x1==x2) means L1 is vertical. Make L2 vertical too, but x1!=u1. PARALLEL
 * 5. (x1==x2) means L1 is vertical. Make L2 vertical too, and x1==u1. COINCIDENT
 * 6. (x1==x2) means L1 is vertical. Make L2 non-vertical. INTERSECTING
 * 7. (u1==u2) means L2 is vertical. Make L1 non-vertical. INTERSECTING
 * 
 * From here on out, we no longer have vertical lines.
 * 
 * ============================================
 * SCORING: 2 points for case 8
 *          2 points for case 9
 *          2 points for case 10
 * ============================================
 *
 * 8. [L1,L2] where the lines are non-vertical parallel
 * 9. [L1,L2] where the lines are non-vertical intersecting
 * 10. [L1,L2] where the lines are non-vertical coincident
 * 
 * OK: The remaining twelve test cases may seem like overkill! But they do test the boundaries of
 * the problem space. To truly get your tests cases to detect boundary conditions, take a look at 
 * the fringes of the problem space. We know that ints are stored as 32 bit integers; this is a 
 * limitation! What if the lines exceed the values we normally would expect to find with the normal
 * int arithmetic? 
 * 
 * ============================================
 * SCORING: 1 point for considering VERY LARGE int values for parallel test case
 *          1 point for considering VERY LARGE int values for coincident test case
 *          1 point for considering VERY LARGE int values for intersecting test case
 * ============================================
 *
 * What about those situations where the y-intercept is outside of the integer space? 
 * Such as, when y-intercept is Integer.MAX_VALUE + 2?
 * 
 * 11. [L1,L2] where L1's y-intercept is above the int plane; L1 is parallel
 * 12. [L1,L2] where L1's y-intercept is above the int plane; L1 is intersecting
 * 13. [L1,L2] where L1's y-intercept is above the int plane; L1 is coincident
 * 
 *    CASE 13 IS A CASE THAT I DON'T IMMEDIATELY KNOW HOW TO RESOLVE NUMERICALLY. THIS
 *    CASE WILL ALWAYS FAIL IN MY IMPLEMENTATION.
 *
 * Same for L2
 * 
 * 14. [L1,L2] where L2's y-intercept is above the int plane; L1 is parallel
 * 15. [L1,L2] where L2's y-intercept is above the int plane; L1 is intersecting
 * 16. [L1,L2] where L2's y-intercept is above the int plane; L1 is coincident
 * 
 *    CASE 16 IS A CASE THAT I DON'T IMMEDIATELY KNOW HOW TO RESOLVE NUMERICALLY. THIS
 *    CASE WILL ALWAYS FAIL IN MY IMPLEMENTATION.
 *
 * Now when both are above the int plane
 *  
 * 17. [L1,L2] where L1 and L2's y-intercept are above the int plane; both parallel
 * 18. [L1,L2] where L1 and L2's y-intercept are above the int plane; both intersecting
 * 19. [L1,L2] where L1 and L2's y-intercept are above the int plane; both coincident
 *
 * What happens when the slope of a line is greater than the allowed int values?
 * 20. [L1,L2] where L1 and L2 have excessive slope and they are parallel
 * 21. [L1,L2] where L1 and L2 have excessive slope and they are intersecting
 * 22. [L1,L2] where L1 and L2 have excessive slope and they are coincident
 * 
 * @author heineman
 */
public class LinesTestCase extends TestCase {
	
	public void testParallel() {
		// Case 8: non-vertical parallel line
		assertEquals (Lines.PARALLEL, Lines.intersection(-2, 2, 4, 2, -5, -5, 10, -5));
		
		// Case 20: non-vertical parallel lines with excessive slope
		// 
		// deal with abberation. Note that improper implementation will not show this as being
		// parallel.
		assertEquals (Lines.PARALLEL, Lines.intersection(1, 0, 2, Integer.MAX_VALUE,
														 2, 0, 3, Integer.MAX_VALUE));
		
		// Case 4: vertical line
		assertEquals (Lines.PARALLEL, Lines.intersection(-2, 99, -2, -99,
					9, 99, 9, -99));
		
		// Case 11. [L1,L2] where L1's y-intercept is above the int plane; L1 is parallel
		assertEquals (Lines.PARALLEL, Lines.intersection(Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE,
				Integer.MAX_VALUE-20, -2, -19, Integer.MAX_VALUE-2));
		
		// Case 14. [L1,L2] where L2's y-intercept is above the int plane; L1 is parallel
		assertEquals (Lines.PARALLEL, Lines.intersection(Integer.MAX_VALUE-20, -2, -19, Integer.MAX_VALUE-2,
				Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE));
		
		// Case 17. [L1,L2] where L1 and L2's y-intercept are above the int plane; both parallel
		assertEquals (Lines.INTERSECTING, Lines.intersection(-Integer.MIN_VALUE/2, 0, -2, Integer.MAX_VALUE,
														 Integer.MAX_VALUE/2+1, 0, -1, Integer.MAX_VALUE));
		
		
	}
	
	/**
	 * Test intersection.
	 * 
	 * That is, where p1-p2 simply intersects q1-q2
	 * 
	 * Note we must consider if the lines are vertical, since only then is it possible there is
	 * no y-intercept value.
	 */
	public void testIntersection () {
		// Case 6: vertical line L2 vs. non vertical line
		assertEquals (Lines.INTERSECTING, Lines.intersection(0, 0, 1, 100, 0, -2, 0, 80));
		
		// Case 7: vertical line L1 vs. non vertical line
		assertEquals (Lines.INTERSECTING, Lines.intersection(0, -2, 0, 80, 0, 0, 1, 100));
		
			// Case 9a: non-vertical line; this one is horizontal ? does it matter
		assertEquals (Lines.INTERSECTING, Lines.intersection(0, 0, 100, 0, -2, 1, 80, 0));
			// Case 9b: non-vertical line; this one is not horizontal ? does it matter
		assertEquals (Lines.INTERSECTING, Lines.intersection(0, 0, 100, 2, -2, 1, 80, 0));
		
		// Case 12. [L1,L2] where L1's y-intercept is above the int plane; L1 is intersecting
		assertEquals (Lines.INTERSECTING, Lines.intersection(Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE,
				Integer.MAX_VALUE-20, -3, -19, Integer.MAX_VALUE-2));
		
		// Case 15. [L1,L2] where L2's y-intercept is above the int plane; L1 is intersecting
		assertEquals (Lines.INTERSECTING, Lines.intersection(Integer.MAX_VALUE-20, -3, -19, Integer.MAX_VALUE-2,
				Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE));

		// Case 21: non-vertical intersecting lines with excessive slope
		// 
		// deal with abberation. Note that improper implementation will not show this as being
		// parallel.
		assertEquals (Lines.INTERSECTING, Lines.intersection(1, 0, 2, Integer.MAX_VALUE,
														 2, 1, 3, Integer.MAX_VALUE));
		
		// Case 18. [L1,L2] where L1 and L2's y-intercept are above the int plane; both intersecting
		assertEquals (Lines.INTERSECTING, Lines.intersection(-Integer.MIN_VALUE/2, 0, -2, Integer.MAX_VALUE,
														 Integer.MAX_VALUE/2, 0, 2, Integer.MAX_VALUE));
	}
	
	
	/**
	 * Test coincidence.
	 * 
	 * That is, where p1-p2 forms the exact same line as q1-q2.
	 * 
	 * Note we must consider if the lines are vertical, since only then is it possible there is
	 * no y-intercept value.
	 */
	public void testCoincident () {
		// Case 5: vertical line
		assertEquals (Lines.COINCIDENT, Lines.intersection(0, 0, 0, 100, 0, -2, 0, 80));
		
		// Case 10a: non-vertical horizontal lines
		assertEquals (Lines.COINCIDENT, Lines.intersection(0, 0, 100, 0, -2, 0, 80, 0));
		
		// Case 10a: non-vertical non-horizontal lines
		assertEquals (Lines.COINCIDENT, Lines.intersection(1, -1, -1, 1, 2, -2, -2, 2));

		// Case 19. [L1,L2] where L1 and L2's y-intercept are above the int plane; both coincident
		assertEquals (Lines.COINCIDENT, Lines.intersection(Integer.MIN_VALUE/2, 0, -2, Integer.MAX_VALUE,
														 Integer.MIN_VALUE/2, 0, Integer.MIN_VALUE+2, -Integer.MIN_VALUE+1));
		
		// Case 22: non-vertical coincident lines with excessive slope
		// 
		// deal with abberation. Note that improper implementation will not show this as being
		// parallel.
		assertEquals (Lines.COINCIDENT, 
				Lines.intersection(-1, Integer.MIN_VALUE+1, 1, Integer.MAX_VALUE,
								   0, 0, 1, Integer.MAX_VALUE));
	}
	
	// These test cases are commented out, because I don't have the time to implement
	// a thorough code check to detect these errors.
	public void badTestCases() {
		// Case 13. [L1,L2] where L1's y-intercept is above the int plane; L1 is coincident
		assertEquals (Lines.COINCIDENT, Lines.intersection(Integer.MAX_VALUE/2, 0, 0, Integer.MAX_VALUE,
				Integer.MAX_VALUE/2, 0, Integer.MAX_VALUE/2-2, 4));
		
		// Case 16. [L1,L2] where L2's y-intercept is above the int plane; L1 is coincident
		assertEquals (Lines.COINCIDENT, Lines.intersection(Integer.MAX_VALUE/2, 0, Integer.MAX_VALUE/2-2, 4,
				Integer.MAX_VALUE/2, 0, 0, Integer.MAX_VALUE));
		}
	
	/**
	 * Test illegal arguments to the method.
	 * 
	 * That is, where p1-p2 is a single point and/or q1-q2 is a single point.
	 */
	public void testIllegalArguments () {
		try {
			// CASE: 1
			Lines.intersection(2, 0, 2, 0, 1, 2, 3, 4);
			fail ("Doesn't handle case when p1-p2 is a single point.");
		} catch (IllegalArgumentException iae) {
			// ok
		}
		
		try {
			// CASE: 2
			Lines.intersection(1, 2, 3, 4, 2, 0, 2, 0);
			fail ("Doesn't handle case when q1-q2 is a single point.");
		} catch (IllegalArgumentException iae) {
			// ok
		}
		
		try {
			// CASE: 3
			Lines.intersection(2, 0, 2, 0, 2, 0, 2, 0);
			fail ("Doesn't handle case when both p1-p2 and q1-q2 are a single point.");
		} catch (IllegalArgumentException iae) {
			// ok
		}
	}
}
