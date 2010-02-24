/*
 * Created on Aug 26, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * This is a simple speed controller that can be used with the vehicle in homework assignment 3.
 * 
 * @author gpollice
 * @version 26-Aug-2008
 */
public class SimpleSpeedController
{
	private IEngine engine;
	private double gearRatio;
	private double wheelCircumference;

	/**
	 * Constructor. This constructor takes the circumference of the vehicle's wheels. However, the
	 * controller will not work until the controller is linked with an engine.
	 * <em>The dimensions in this speed controller
	 * are in inches</em>.
	 * 
	 * <p>
	 * <b>Note:</b> If a method is called without an associated engine, then a NullPointerException
	 * will be thrown.
	 * 
	 * @param wheelCircumference
	 *            the circumference for the vehicle's wheels (inches)
	 * @see cs4233.hw3.SimpleSpeedController#setEngine(IEngine, double)
	 */
	public SimpleSpeedController(double wheelCircumference)
	{
		this.wheelCircumference = wheelCircumference;
	}

	/**
	 * Change the vehicle speed by changing the engine speed (RPM). The calculation is a function of
	 * the speed change, wheel circumference, and gear ratio.
	 * 
	 * @param speedChange
	 *            the change in speed (in. / min.)
	 */
	public void changeSpeed(double speedChange)
	{
		double rpmChange = speedChange * gearRatio / wheelCircumference;
		int newRPM = engine.getRPM() + (int) Math.round(rpmChange);
		engine.setRPM(newRPM < 0 ? 0 : newRPM);
	}

	/**
	 * @return the speed in in. / min.
	 */
	public double getSpeed()
	{
		return engine.getRPM() / gearRatio * wheelCircumference;
	}

	/**
	 * Associate this controller with an engine.
	 * 
	 * @param engine
	 *            the engine to associate with the controller
	 * @param gearRatio
	 *            the gear ratio (# of engine RPM for each revolution of the vehicle wheel)
	 */
	public void setEngine(IEngine engine, double gearRatio)
	{
		this.engine = engine;
		this.gearRatio = gearRatio;
	}

	/**
	 * @return the engine associated with this controller
	 */
	public IEngine getEngine()
	{
		return engine;
	}
}
