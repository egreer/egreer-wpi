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
 * Interface for the speed controller used with the vehicle for
 * homework assignment #3.
 * 
 * @author gpollice
 * @version 25-Aug-2008
 */
public interface ISpeedController
{

	/**
	 * Set the wheel speed by setting the engine to the appropriate RPM speed. The speed is given in
	 * centimeters per minute. The method computes the appropriate speed for the engine based upon
	 * the wheel diameter and the gear ratio.
	 * 
	 * @param cpmSpeed
	 *            the new speed in cm. / min.
	 * @return true if the speed has been set and false if the operation is not able to be completed
	 */
	public abstract boolean setSpeed(double cpmSpeed);

	/**
	 * @return the current speed in cm. / min.
	 */
	public abstract double getSpeed();

	/**
	 * @return the engine controlled by this controller.
	 */
	public abstract IEngine getControlledEngine();

}