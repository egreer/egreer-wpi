/*
 * Created on Aug 24, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * The direction controller is the basic steering mechanism for the vehicle. When the controller is
 * created, there is a starting direction set. This is a value that translates to a positive value,
 * 0.0 <= currentDirection < 360.0. The direction is in degrees where 0.0 is north (whether this is
 * magnetic or true north is not an issue here since the starting direction is given).
 * 
 * @author gpollice
 * @version 24-Aug-2008
 */
public class DirectionController implements IDirectionController
{
	private double currentDirection;

	/**
	 * Default constructor sets the starting direction to due north.
	 */
	public DirectionController()
	{
		this(0.0);
	}

	/**
	 * Constructor that takes a starting direction. If the starting direction is outside of the
	 * range 0.0 <= startingDirection < 360.0, it will be normalized to a value in the range. The
	 * same applies to negative values.
	 * 
	 * @param startingDirection
	 *            initial direction in degrees
	 */
	public DirectionController(double startingDirection)
	{
		currentDirection = normalize(startingDirection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs4233.hw3.IDirectionController#turn(double)
	 */
	public void turn(double turnAngle)
	{
		currentDirection = normalize(currentDirection + turnAngle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs4233.hw3.IDirectionController#getCurrentDirection()
	 */
	public double getCurrentDirection()
	{
		return currentDirection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs4233.hw3.IDirectionController#setCurrentDirection(double)
	 */
	public void setCurrentDirection(double currentDirection)
	{
		this.currentDirection = normalize(currentDirection);
	}

	/**
	 * Convert the direction to a value in the range 0.0 <= direction < 360.0
	 * 
	 * @param direction
	 *            the direction, in degrees, to be normalized
	 * @return the normalized direction
	 * @throws HW3Exception
	 *             if the direction is not valid (i.e. a finite number)
	 */
	private double normalize(double direction)
	{
		if (Double.isNaN(direction) || direction == Double.POSITIVE_INFINITY
				|| direction == Double.NEGATIVE_INFINITY) {
			throw new HW3Exception("Direction must be a finite number");
		}
		double normalizedDirection = direction;
		double normalizeDelta = (direction < 0.0) ? 360.0 : -360.0;

		while (normalizedDirection < 0.0 || normalizedDirection >= 360.0) {
			normalizedDirection += normalizeDelta;
		}
		return normalizedDirection;
	}
}
