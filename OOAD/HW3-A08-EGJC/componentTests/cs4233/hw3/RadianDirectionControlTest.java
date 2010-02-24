/*
 * Created on Aug 26, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu. 
 *
 * The program is provided under the terms and conditions of
 * the Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL
 * is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Test cases for RadianDirectionControl.
 * 
 * @author gpollice
 */
public class RadianDirectionControlTest
{
	private RadianDirectionControl control;
	private final double TwoPI = 2.0 * Math.PI;
	
	private enum TurnDirection{
		LEFT,
		RIGHT
	}
	
	@Before
	public void setUp()
	{
		control = new RadianDirectionControl();
	}
	
	@Test
	public void testInitialConfiguration()
	{
		assertEquals(0.0, control.getDirection(), 0.0);
	}
	
	/**
	 * Test method for {@link cs4233.hw3.RadianDirectionControl#turnLeft(double)}.
	 */
	@Test
	public void testTurnLeft()
	{
		control.turnLeft(1.01);
		assertEquals(TwoPI - 1.01, control.getDirection(), 0.01);
	}

	/**
	 * Test method for {@link cs4233.hw3.RadianDirectionControl#turnRight(double)}.
	 */
	@Test
	public void testTurnRight()
	{
		control.turnRight(1.02);
		assertEquals(1.02, control.getDirection(), 0.0);
	}
	
	@Test
	public void testTurnLeftWithValuesToNormalize()
	{
		control.turnLeft(7 * TwoPI + 1.15);
		assertEquals(TwoPI - 1.15, control.getDirection(), 0.01);
	}
	
	@Test
	public void testTurnLeftTwoPI()
	{
		control.turnLeft(TwoPI);
		assertEquals(0.0, control.getDirection(), 0.0);
	}
	
	@Test
	public void testTurnRightWithValuesToNormalize()
	{
		control.turnRight(7 * TwoPI + 1.15);
		assertEquals(1.15, control.getDirection(), 0.01);
	}
	
	@Test
	public void testTurnRightTwoPI()
	{
		control.turnRight(TwoPI);
		assertEquals(0.0, control.getDirection(), 0.0);
	}
	
	/*************************** Negative tests ******************************/
	@Test
	public void testInvalidTurnLeft()
	{
		performInvalidTurn(TurnDirection.LEFT,Double.NaN);
		performInvalidTurn(TurnDirection.LEFT, Double.NEGATIVE_INFINITY);
		performInvalidTurn(TurnDirection.LEFT, Double.POSITIVE_INFINITY);
		performInvalidTurn(TurnDirection.LEFT, -0.1);
	}
	
	@Test
	public void testInvalidTurnRight()
	{
		performInvalidTurn(TurnDirection.RIGHT,Double.NaN);
		performInvalidTurn(TurnDirection.RIGHT, Double.NEGATIVE_INFINITY);
		performInvalidTurn(TurnDirection.RIGHT, Double.POSITIVE_INFINITY);
		performInvalidTurn(TurnDirection.RIGHT, -0.1);
	}
	
	/***************************** Helper methods ****************************/
	private void performInvalidTurn(TurnDirection direction, double radians)
	{
		try {
			if (direction == TurnDirection.LEFT) {
				control.turnLeft(radians);
			} else {
				control.turnRight(radians);
			}
			fail("Expected error on turn method");
		} catch (HW3Exception e) {
			assertTrue(true);
		}
	}
}
