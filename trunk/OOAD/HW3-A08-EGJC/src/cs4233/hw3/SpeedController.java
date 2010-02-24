/*
 * Created on Aug 23, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * The speed controller is used to set the speed of the vehicle. It sets the engine's RPMs to the
 * appropriate value given the wheel size.
 * 
 * @author gpollice
 * @version 23-Aug-2008
 */
public class SpeedController implements ISpeedController
{
	private IEngine engineControlled;
	private double gearRatio;
	private double wheelCircumference;

	/**
	 * Constructor. The engine controlled by this controller is established when the object is
	 * created as is the diameter of the wheel (in cm.) attached to the engine, and the gear ratio.
	 * The gear ratio indicates how many RPMs of the engine are required to make a complete rotation
	 * of the wheel.
	 * 
	 * @param engineControlled
	 *            the engine controlled by this speed controller
	 * @param wheelDiameter
	 *            the diameter of the wheel in centimeters
	 * @param gearRatio
	 *            the number of RPMs require to make a full rotation of the wheel
	 * @throws HW3Exception
	 *             if there is an error in the arguments
	 */
	public SpeedController(IEngine engineControlled, double wheelDiameter, double gearRatio)
	{
		if (engineControlled == null || wheelDiameter <= 0.0 || gearRatio <= 0.0) {
			throw new HW3Exception("Invalid argument to SpeedController constructor");
		}
		this.engineControlled = engineControlled;
		this.gearRatio = gearRatio;
		wheelCircumference = Math.PI * wheelDiameter;
	}

	/* (non-Javadoc)
	 * @see cs4233.hw3.ISpeedController#setSpeed(double)
	 */
	public boolean setSpeed(double cpmSpeed)
	{
		boolean returnValue = true;
		if (!engineControlled.isRunning() || cpmSpeed < 0.0) {
			returnValue = false;
		} else {
			Double accurateRPM = cpmSpeed * gearRatio / wheelCircumference;
			engineControlled.setRPM((int) Math.round(accurateRPM));
		}
		return returnValue;
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.ISpeedController#getSpeed()
	 */
	public double getSpeed()
	{
		return engineControlled.getRPM() / gearRatio * wheelCircumference;
	}
	
	/* (non-Javadoc)
	 * @see cs4233.hw3.ISpeedController#getControlledEngine()
	 */
	public IEngine getControlledEngine()
	{
		return engineControlled;
	}
}
