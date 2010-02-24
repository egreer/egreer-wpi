package dec05;

/**
 * Lines are a geometric construct within a two-dimensional plane. 
 * 
 * Two points (x1,y1) and (x2,y2) define a line, which stretches to infinity in
 * both directions.
 * 
 * Given two lines in a two-dimensional Euclidian plane, it must be the case that 
 * they are either:
 * 
 *    a) parallel to each other
 *    b) coincident with each other (they actually represent the same infinite line)
 *    c) intersect each other
 *  
 * @author heineman
 */
public class Lines {

	/** Declares that two lines are parallel. */
	public static final int PARALLEL = 1;
	
	/** Declares that two lines are, in fact, the exact same line. */
	public static final int COINCIDENT = 2;
	
	/** Declares that two lines intersect. */
	public static final int INTERSECTING = 3;
	
	/**
	 * Given two infinite lines represented by the points (p1-p2) and (q1-q2) 
	 * determine the relationship between the lines:
	 * 
	 *    PARALLEL        if the lines are parallel
	 *    COINCIDENT      if the lines represent the same line in the plane
	 *    INTERSECTING    if the lines intersect each other
	 * 
	 * @param x1   x coordinate of point p1
	 * @param y1   y coordinate of point p1
	 * @param x2   x coordinate of point p2
	 * @param y2   y coordinate of point p2
	 * @param u1   x coordinate of point q1
	 * @param v1   y coordinate of point q1
	 * @param u2   x coordinate of point q2
	 * @param v2   y coordinate of point q2
	 * 
	 * @exception   IllegalArgumentException   if the points do not properly represent
	 *                                         two line (p1-p2) and (q1-q2).
	 * 
	 * @return  type of relationship between two lines.
	 */
	public static int intersection (int x1, int y1, int x2, int y2,
									int u1, int v1, int u2, int v2) throws IllegalArgumentException {
		// Note: You will not be able to run your JUnit Test cases! They will be run
		// on our own implementation of this method.
		throw new IllegalArgumentException ("Not Yet Implemented");
	}
}
