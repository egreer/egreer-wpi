/*
 * Created on Aug 25, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu. 
 *
 * The program is provided under the terms and conditions of
 * the Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL
 * is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * Interface for any direction control device used by the Vehicle in 
 * homework assignment #3.
 * 
 * @author gpollice
 * @version 25-Aug-2008
 */
public interface IDirectionController
{

	/**
	 * Turn the amount specified in degrees. A negative value turns to the left (counterclockwise)
	 * and a positive value turns to the right (clockwise). 
	 * 
	 * @param turnAngle the angle to turn
	 */
	public abstract void turn(double turnAngle);

	/**
	 * @return the value of currentDirection in degrees
	 */
	public abstract double getCurrentDirection();

	/**
	 * Set currentDirection to the value specified. This should be called only when the vehicle or
	 * some other control object detects that the current setting is in error.
	 * 
	 * @param currentDirection
	 *            the new value of currentDirection in degrees
	 */
	public abstract void setCurrentDirection(double currentDirection);

}