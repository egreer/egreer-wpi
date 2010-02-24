/*
 * Created on Aug 24, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for the direction controller
 * 
 * @author gpollice
 */
public class DirectionControllerTest
{
	@Test
	public void testDefaultConstructor()
	{
		IDirectionController directionController = new DirectionController();
		assertEquals(0.0, directionController.getCurrentDirection(), 0.0);
	}

	@Test
	public void testConstructorWithStartingDirection()
	{
		IDirectionController directionController = new DirectionController(0.0);
		assertEquals(0.0, directionController.getCurrentDirection(), 0.0);
		directionController = new DirectionController(90.0);
		assertEquals(90.0, directionController.getCurrentDirection(), 0.0);
	}

	@Test
	public void testConsturctorWithNonNormalStartingDirection()
	{
		IDirectionController directionController = new DirectionController(360.0);
		assertEquals(0.0, directionController.getCurrentDirection(), 0.0);
		directionController = new DirectionController(-60.0);
		assertEquals(300.0, directionController.getCurrentDirection(), 0.0);
		directionController = new DirectionController(725.0);
		assertEquals(5.0, directionController.getCurrentDirection(), 0.0);
	}
	
	@Test
	public void testSetDirection()
	{
		IDirectionController directionController = new DirectionController();
		directionController.setCurrentDirection(90.0);
		assertEquals(90.0, directionController.getCurrentDirection(), 0.0);
		directionController.setCurrentDirection(390.0);
		assertEquals(30.0, directionController.getCurrentDirection(), 0.0);
		directionController.setCurrentDirection(-90.0);
		assertEquals(270.0, directionController.getCurrentDirection(), 0.0);
	}
	
	@Test
	public void testTurn()
	{
		IDirectionController directionController = new DirectionController();
		directionController.turn(90.0);
		assertEquals(90.0, directionController.getCurrentDirection(), 0.0);
		directionController.turn(-450.0);
		assertEquals(0.0, directionController.getCurrentDirection(), 0.0);
	}

	/******************************* Negative tests ***************************/
	@Test
	public void testInvalidConstructor()
	{
		performInvalidConstructor(Double.NaN);
		performInvalidConstructor(Double.NEGATIVE_INFINITY);
		performInvalidConstructor(Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void testInvalidTurn()
	{
		performInvalidTurn(Double.NaN);
		performInvalidTurn(Double.NEGATIVE_INFINITY);
		performInvalidTurn(Double.POSITIVE_INFINITY);
	}
	
	/********************************* Helper methods **************************/
	private void performInvalidConstructor(double startingDirection)
	{
		try {
			new DirectionController(
					startingDirection);
			fail("Expected exception on contructor");
		} catch (HW3Exception e) {
			assertTrue(true);
		}
	}
	
	private void performInvalidTurn(double turnAngle)
	{
		IDirectionController directionController = new DirectionController();
		try {
			directionController.turn(turnAngle);
			fail("Expected exception on turn");
		} catch (HW3Exception e) {
			assertTrue(true);
		}
	}
}
