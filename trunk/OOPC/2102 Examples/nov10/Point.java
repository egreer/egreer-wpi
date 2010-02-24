package nov10;

import java.util.Scanner;

public class Point {

	/** x and y coordinates. */
	private double x;
	private double y;
	
	/** Default constructor. */
	public Point (double xVal, double yVal) {
		this.x = xVal;
		this.y = yVal;
	}
	
	/** Alternate constructor. */
	public Point (String s) {
		Scanner sc = new Scanner (s);
		this.x = sc.nextDouble();
		this.y = sc.nextDouble();
	}

	/** Return string representation. */
	public String toString () {
		return "(" + x + "," + y + ")";
	}
	
	/**
	 * Returns the HashCode value for a Point object.
	 * 
	 * This method returns an int with the following properties:
	 * 
	 *    If p1 and p2 refer to two Point objects, and p1.equals (p2) then
	 *    p1.hashCode() == p2.hashCode()
	 *    
	 * Note, if it so happens that two objects, p3 and p4, each return the same
	 * value with p3.hashCode() and p4.hashCode(), it is not required that p3.equals(p4).
	 */
	public int hashCode() {
		// We "cast" the sum x+y as an int. This means that we force the truncation
		// of the double to produce an int value.
		return (int) (x + y);    
	}
}
