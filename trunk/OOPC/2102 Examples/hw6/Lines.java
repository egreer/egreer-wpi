package hw6;

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

	/** Constants to return from identify. */
	
	/** Declares that two lines are parallel. */
	public static final int PARALLEL = 1;
	
	/** Declares that two lines are, in fact, the exact same line. */
	public static final int COINCIDENT = 2;
	
	/** Declares that two lines intersect. */
	public static final int INTERSECTING = 4;
	
	
	/**
	 * Compute the greatest common divisor of two longs.
	 * 
	 * Grab formula from any website: 
	 */
	public static long gcd (long m, long n) {
		if ((m % n) == 0)
			return n;
		else
			return gcd(n, m % n);
	}
	
	/**
	 * Given two lines represented by the points (p1-p2) and (q1-q2) determine
	 * the relationship between the lines:
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
	 * DEFECT ALERT! Code doesn't handle cases where (0,0) is one of the points. My slope calculations
	 * are all messed up.
	 * 
	 * @return  type of relationship between two lines.
	 * @exception  IllegalArgumentException
	 */
	public static int intersection (int x1, int y1, int x2, int y2,
									int u1, int v1, int u2, int v2) {
		
		if ((x1 == x2) && (y1 == y2)) {
			throw new IllegalArgumentException ("Line p1-p2 is a single point");
		}
		
		if ((u1 == u2) && (v1 == v2)) {
			throw new IllegalArgumentException ("Line q1-q2 is a single point");
		}
		
		// check for coincidence.
		//    try same points, same points in reverse.
		//    vertical line vs. non-vertical line, non-vertical line vs. vertical line
		//    vertical line vs. vertical line
		boolean line1Vertical = false;
		boolean line2Vertical = false;
		if (x1 == x2) { line1Vertical = true; }
		if (u1 == u2) { line2Vertical = true; }
		
		// can only be intersecting!
		if (line1Vertical != line2Vertical) {
			return INTERSECTING;
		}
		
		// at this point we check for two vertical lines
		if (line1Vertical) {
			// we know line2Vertical is true; no need to check
			if (x1 == u1) {
				return COINCIDENT;    // same line if same x value 
			}
			
			return PARALLEL;          // must be parallel!
		}
		
		// THE FOLLOWING CODE incorrectly computes with very large values. Comment in to see what happens!
		boolean useBadCode = true;
		
		if (useBadCode) {
			double mp = (y2-y1);        // if you had this as (y2-y1)/(x2-x1) BAD things would happen!
			mp /= (x2-x1);
			double mq = (v2-v1);
			mq /= (u2-u1);
			double bp = y2 - mp*x2;
			double bq = v2 - mq*u2;
			
			if ((mq == mp) && (bp != bq)) { return PARALLEL; }
			if ((mq == mp) && (bp == bq)) { return COINCIDENT; }
			return INTERSECTING;
			
		}
		
		// Ok now ready to deal with the hard case. Neither line is vertical and we must determine
		// if they intersect or are parallel. To do this, we compute the y-intercept. If two lines
		// have the same slope and the same intercept, they are parallel; otherwise they intersect
		// if we eliminate all other possibilities above. To avoid precisions in error, we don't
		// actually compute slope by means of fractions. Rather we work with integers.
		// the following code is written to possibly deal with the cases where 
		// large Ints being added and subtracted cause problems with arithmetic.
		
		double p1 = x2;
		p1 = p1-x1;
		p1 *= y1;
		
		double p2 = y2;
		p2 = p2-y1;
		p2 *= x1;
		
		double nump = p1 - p2;
		double denomp = x2;
		denomp -= x1;
		
		// at this point, num is the precise numerator and denom the precise denominator. We
		// don't compute the values; rather we compute gcd
		long gp = gcd ((long)nump, (long)denomp);
		nump = nump / gp;
		denomp = denomp / gp;
		
		double q1 = u2;
		q1 = q1-u1;
		q1 *= v1;
		
		double q2 = v2;
		q2 = q2-v1;
		q2 *= u1;
		
		double numq = q1 - q2;
		double denomq = u2;
		denomq -= u1;
		
		// at this point, num is the precise numerator and denom the precise denominator. We
		// don't compute the values; rather we compute gcd
		long gq = gcd ((long)numq, (long)denomq);
		numq = numq / gq;
		denomq = denomq / gq;
		
		// see if y-intercept is the same
		boolean yInterceptSame = true;
		if ((numq != nump) || (denomq != denomp)) {yInterceptSame = false; }
		
		// compute slope!
		nump = y2;
		nump -= y1;
		denomp = x2;
		denomp -= x1;
		gp = gcd((long) nump, (long) denomp);
		nump = nump / gp;
		denomp = denomp / gp;
		
		numq = v2;
		numq -= v1;
		denomq = u2;
		denomq -= u1;
		gq = gcd((long) numq, (long) denomq);
		numq = numq / gq;
		denomq = denomq / gq;
		
		// if not same then slopes are different; must intersect
		boolean slopeIsSame = true;
		if ((numq != nump) || (denomq != denomp)) { slopeIsSame = false; }
		
		if (slopeIsSame && yInterceptSame) { return COINCIDENT; }
		if (slopeIsSame && !yInterceptSame) { return PARALLEL; }
		
		// slope must be different!
		return INTERSECTING;
		
	}


}
