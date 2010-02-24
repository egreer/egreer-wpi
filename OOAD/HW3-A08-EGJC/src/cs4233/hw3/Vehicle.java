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
 * This class represents the vehicle for homework assignment #3, CS4233-A08. The vehicle is able to
 * travel at various speeds and turn in any direction as well as go forward and reverse. The vehicle
 * performs the behaviors by delegating to individual objects responsible for each of them.
 * 
 * @author gpollice
 * @version 23-Aug-2008
 */
class Vehicle
{
	private IEngine engine;
	private ISpeedController speedController;
	private IDirectionController directionController;

	/**
	 * Constructor requires the component parts.
	 * 
	 * @param the
	 *            vehicle's engine
	 * @param speedController
	 *            the vehicle's speed control
	 * @param directionController
	 *            the vehicle's steering mechanism
	 * @throws HW3Exception
	 *             if any errors in the arguments
	 */
	public Vehicle(IEngine engine, ISpeedController speedController,
			IDirectionController directionController)
	{
		if (speedController == null || directionController == null) {
			throw new HW3Exception("Invalid argument for Vehicle constructor");
		}
		this.engine = engine;
		this.speedController = speedController;
		this.directionController = directionController;
	}

	/**
	 * Start the engine.
	 */
	public void start()
	{
		engine.setRunning(true);
	}

	/**
	 * Stop the engine.
	 */
	public void stop()
	{
		engine.setRunning(false);
	}

	/**
	 * Report if the vehicle's engine is running.
	 * 
	 * @return true if the engine is running
	 */
	public boolean isRunning()
	{
		return engine.isRunning();
	}

	/**
	 * Set the speed to the specified value (in cm. / min.)
	 * 
	 * @param speed
	 * @throws HW3Exception
	 *             if the operation cannot be completed
	 */
	public void setSpeed(double speed)
	{
		if (!speedController.setSpeed(speed)) {
			throw new HW3Exception("Unable to complete the operation to set the speed");
		}
	}

	/**
	 * Accelerate the engine by the specified cm. / min.
	 * 
	 * @param speedIncrease
	 *            the cm. / min. to accelerate (<em>this value must be non-negative</em>)
	 * @throws HW3Exception
	 *             if the operation cannot be completed
	 */
	public void accelerate(double speedIncrease)
	{
		double newSpeed = speedController.getSpeed() + speedIncrease;
		if (speedIncrease < 0.0 || !speedController.setSpeed(newSpeed)) {
			throw new HW3Exception("Unable to complete the operation to accelerate");
		}
	}

	/**
	 * Decelerate the engine by the specified cm. / min. If the deceleration value is greater than
	 * the current speed, then the vehicle stops.
	 * 
	 * @param speedDecrease
	 *            the cm. / min. to decelerate (<em>this value must be non-negative</em>)
	 * @throws HW3Exception
	 *             if the operation cannot be completed
	 */
	public void decelerate(double speedDecrease)
	{
		double newSpeed = speedController.getSpeed() - speedDecrease;
		if (newSpeed < 0.0) {
			newSpeed = 0.0;
		}
		if (speedDecrease < 0.0 || newSpeed < 0.0 || !speedController.setSpeed(newSpeed)) {
			throw new HW3Exception("Unable to complete the operation to decelerate");
		}
	}

	/**
	 * @return the current speed of the vehicle
	 */
	public double getSpeed()
	{
		return speedController.getSpeed();
	}

	/**
	 * Put the vehicle in forward gear.
	 */
	public void goForward()
	{
		engine.setGoingForward(true);
	}

	/**
	 * Put the vehicle in reverse
	 */
	public void goBackward()
	{
		engine.setGoingForward(false);
	}

	/**
	 * This method returns whether the vehicle is in forward gear. The vehicle may be stopped, but
	 * still in forward gear.
	 * 
	 * @return true if the vehicle is in forward gear, false if in reverse
	 */
	public boolean isGoingForward()
	{
		return engine.isGoingForward();
	}

	/**
	 * @return the vehicle's current direction in degrees (0 <= direction < 360)
	 */
	public double getDirection()
	{
		return directionController.getCurrentDirection();
	}

	/**
	 * Set the vehicle's current direction.
	 * 
	 * @param direction
	 *            degrees (0 <= direction < 360)
	 * @throws HW3Exception
	 *             if unable to complete the operation
	 */
	public void setDirection(double direction)
	{
		directionController.setCurrentDirection(direction);
	}

	/**
	 * Turn the vehicle by the specified number of degrees.
	 * 
	 * @param turnAngle
	 *            the degrees to turn
	 * @throws HW3Exception
	 *             if the operation cannot be completed
	 */
	public void turn(double turnAngle)
	{
		directionController.turn(turnAngle);
	}
}
