/*
 * Created on Aug 26, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu. 
 *
 * The program is provided under the terms and conditions of
 * the Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL
 * is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * This is an alternate direction controller that can be used in the
 * Vehicle for homework assignment 3. It is based upon radians rather than
 * degrees
 * 
 * @author gpollice
 * @version 26-Aug-2008
 */
public class RadianDirectionControl
{
	private double direction;
	private final double TwoPI = 2.0 * Math.PI;
	
	/**
	 * Default constructor. Initializes the direction to 0.0 radians.
	 */
	public RadianDirectionControl()
	{
		direction = 0.0;  // 0.0 <= direction <= 2 * Math.PI
	}
	
	/**
	 * Turn left the specified number of radians. The value must be positive.
	 * 
	 * @param radians the number of radians to turn
	 * @throws HW3Exception if there is an error (like a negative argument)
	 */
	public void turnLeft(double radians)
	{
		if (radians < 0.0) {
			throw new HW3Exception("Negative argument in turnLeft method");
		}
		direction = normalize (direction -= radians);
	}
	
	/**
	 * Turn right the specified number of radians. The value must be positive.
	 * 
	 * @param radians the number of radians to turn
	 * @throws HW3Exception if there is an error (like a negative argument)
	 */
	public void turnRight(double radians)
	{
		if (radians < 0.0) {
			throw new HW3Exception("Negative argument in turnRight method");
		}
		direction = normalize (direction += radians);
	}
	
	/**
	 * @return the direction in radians
	 */
	public double getDirection()
	{
		return direction;
	}
	
	private double normalize(double radians)
	{
		if (Double.isNaN(radians) || radians == Double.POSITIVE_INFINITY
				|| radians == Double.NEGATIVE_INFINITY) {
			throw new HW3Exception("Radians must be a finite number");
		}
		double normalizedRadians = direction;
		double normalizeDelta = (direction < 0.0) ? TwoPI : -TwoPI;

		while (normalizedRadians < 0.0 || normalizedRadians >= TwoPI) {
			normalizedRadians += normalizeDelta;
		}
		return normalizedRadians;
	}
}
