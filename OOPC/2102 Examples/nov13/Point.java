package nov13;

import java.util.Scanner;

/**
 * Example of a static method.
 * 
 * Shows a static method for the raw functionality that computes distance between two 
 * (x,y) coordinates.
 * 
 * @author heineman
 */
public class Point {
	
	/** Location information. */
	double x;
	double y;
	
	/**
	 * Instantiates a point with the given coordinates.
	 * 
	 * @param x   x-coordinate of the point.
	 * @param y   y-coordinate of the point.
	 */
	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Given a scanner, return the next Point.
	 * 
	 * Format is based on toString output.
	 * 
	 * @param sc
	 * @return
	 */
	public static Point scanPoint (Scanner sc) {
		String value = "";
		while (sc.hasNext()) {
			value = value + sc.next();
			
			if (value.startsWith("(") && value.endsWith(")")) {
				int comma = value.indexOf(',');
				double x = Double.parseDouble(value.substring(1, comma));
				double y = Double.parseDouble(value.substring(comma+1,value.length()-1));
				return new Point (x,y);
			}
			
			// must keep going.
		}
		
		// not enough input? Return 'no object'.
		return null;
	}
	
	/**
	 * Returns the distance to the given point.
	 * 
	 * @param p    Target point from which to compute distance
	 * @return     distance as a double.
	 */
	public double distance (Point p) {
		return distance (x, y, p.x, p.y);
	}
	
	/**
     * Returns the distance between two points.
     * 
     * @param x1, y1   the coordinates of the first point
     * @param x2, y2   the coordinates of the second point
     * 
     * @return the distance between the two sets of specified
     * coordinates.
     */
    public static double distance(double x1, double y1,
				  double x2, double y2) {
		x1 -= x2;
		y1 -= y2;
		
		return Math.sqrt(x1 * x1 + y1 * y1);
    }

    // standard methods
    
    /**
     * Return String representation of a point.
     */
    public String toString () {
    	return "(" + x + "," + y + ")";
    }
    
    /**
     * Determines equality between two Point objects.
     * 
     * @param p    target point against which this object is compared.
     */
    public boolean equals (Point p) {
    	return x == p.x && y == p.y;
    }
    
    /**
     * The dreaded hashcode.
     * 
     * Represents a unique value for equivalent Point objects. That is, if two point
     * objects are 'equals', then their hashCode values must be identical.
     */
    public int hashCode() {
    	return (int) (x+y);
    }
    
}
