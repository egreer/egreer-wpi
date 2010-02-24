/*
 * Created on Aug 23, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu. 
 *
 * The program is provided under the terms and conditions of
 * the Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL
 * is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * The Engine class is used to control the vehicle's engine. It controls the 
 * speed of the engine as well as the ability to start, stop, and reverse it.
 * 
 * @author gpollice
 * @version 23-Aug-2008
 */
public class Engine implements IEngine
{
	private boolean running;
	private int rpm;
	private boolean goingForward;
	
	/**
	 * Default constructor. Initializes the engine.
	 */
	public Engine()
	{
		running = false;
		rpm = 0;
		goingForward = true;
	}

	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#isRunning()
	 */
	public boolean isRunning()
	{
		return running;
	}

	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#setRunning(boolean)
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
		if (!running) {
			rpm = 0;
		}
	}

	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#getRPM()
	 */
	public int getRPM()
	{
		return rpm;
	}

	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#setRPM(int)
	 */
	public void setRPM(int rpm)
	{
		if (rpm < 0) {
			throw new HW3Exception("Expected non-negative rpm on setRPM");
		}
		this.rpm = rpm;
	}

	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#isGoingForward()
	 */
	public boolean isGoingForward()
	{
		return goingForward;
	}

	/* (non-Javadoc)
	 * @see cs4233.hw3.IEngine#setGoingForward(boolean)
	 */
	public void setGoingForward(boolean goingForward)
	{
		this.goingForward = goingForward;
	}
}
