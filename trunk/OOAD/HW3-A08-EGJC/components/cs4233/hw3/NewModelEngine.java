/*
 * Created on Aug 25, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

/**
 * This engine is a newer model of the engine for the vehicle. It has quite a different
 * implementation than the original engine.
 * 
 * @author gpollice
 */
public class NewModelEngine
{
	public enum Direction {
		FORWARD, REVERSE
	}

	public enum EngineState {
		RUNNING, NOT_RUNNING
	}

	private Direction direction;
	private EngineState engineState;
	private double engineSpeed; // in revs. per sec.

	/**
	 * Default constructor. Initializes the engine.
	 */
	public NewModelEngine()
	{
		direction = Direction.FORWARD;
		engineState = EngineState.NOT_RUNNING;
		engineSpeed = 0.0;
	}

	/**
	 * Start the engine.
	 */
	public void start()
	{
		engineState = EngineState.RUNNING;
	}

	/**
	 * Stop the engine. This resets the engine to the initial state.
	 */
	public void stop()
	{
		engineState = EngineState.NOT_RUNNING;
		engineSpeed = 0.0;
		direction = Direction.FORWARD;
	}

	/**
	 * @return the current direction
	 */
	public Direction getDirection()
	{
		return direction;
	}

	/**
	 * Change the direction. This simply toggles the direction from forward to reverse.
	 */
	public void changeDirection()
	{
		direction = direction == Direction.FORWARD ? Direction.REVERSE : Direction.FORWARD;
	}

	/**
	 * @return the value of engineState
	 */
	public EngineState getEngineState()
	{
		return engineState;
	}

	/**
	 * @return the value of engineSpeed
	 */
	public double getEngineSpeed()
	{
		return engineSpeed;
	}

	/**
	 * Change the engineSpeed by the value specified. If the speed goes below zero, it's set to
	 * zero.
	 * 
	 * @param speedChange
	 *            the change to the current engine speed
	 * @throws HW3Exception
	 *             if the engine is not running;
	 */
	public void changeEngineSpeed(double speedChange)
	{
		if (engineState != EngineState.RUNNING) {
			throw new HW3Exception("Attempt to change speed when engine is not running");
		}
		engineSpeed += speedChange;
		if (engineSpeed < 0.0) {
			engineSpeed = 0.0;
		}
	}
}
