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
 * Interface for any engine used in the Vehicle for homework assignment 3.
 * 
 * @author gpollice
 * @version 25-Aug-2008
 */
public interface IEngine
{

	/**
	 * @return true if the engine is running
	 */
	public abstract boolean isRunning();

	/**
	 * @param running the new value of running
	 */
	public abstract void setRunning(boolean running);

	/**
	 * @return the revolutions per minute
	 */
	public abstract int getRPM();

	/**
	 * @param rpm the new value of the RPM setting (must be >= 0)
	 * @throws HW3Exception if the value is invalid
	 */
	public abstract void setRPM(int rpm);

	/**
	 * @return true if the engine is running forward
	 */
	public abstract boolean isGoingForward();

	/**
	 * @param goingForward true puts the engine in forward, false in reverse
	 */
	public abstract void setGoingForward(boolean goingForward);

}