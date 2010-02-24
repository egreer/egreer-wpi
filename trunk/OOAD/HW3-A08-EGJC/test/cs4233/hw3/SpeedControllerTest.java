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

import static org.junit.Assert.*;
import org.junit.*;


/**
 * Test cases for the SPeedController class
 * 
 * @author gpollice
 */
public class SpeedControllerTest
{
	private IEngine engine;
	
	@Before
	public void setUp()
	{
		engine = new Engine();
	}
	
	@Test
	public void testValidConstructor()
	{
		ISpeedController speedController = new SpeedController(engine, 3.2, 500.0);
		assertNotNull(speedController);
	}
	
	@Test
	public void testSetSpeed()
	{
		engine.setRunning(true);
		ISpeedController speedController = new SpeedController(engine, 1.2, 100.0);
		assertTrue(speedController.setSpeed(100.0));		// Speed = 100 cm./min.
		assertEquals(2653, engine.getRPM());
	}
	
	@Test
	public void testGetSpeed()
	{
		engine.setRunning(true);
		ISpeedController speedController = new SpeedController(engine, 1.2, 100.0);
		assertTrue(speedController.setSpeed(100.0));		// Speed = 100 cm./min.
		assertEquals(100.0, speedController.getSpeed(), 0.1);
	}
	
	/***************************** Negative tests *********************************/
	@Test
	public void testInvalidConstructors()
	{
		performInvalidConstructor(null, 3.2, 500.0);
		performInvalidConstructor(engine, -2.0, 500.0);
		performInvalidConstructor(engine, 3.2, 0.0);
	}
	
	@Test
	public void testInvalidSetSpeed()
	{
		ISpeedController speedController = new SpeedController(engine, 1.2, 100.0);
		assertFalse(speedController.setSpeed(100.0));
		engine.setRunning(true);
		speedController.setSpeed(100.0);
		assertFalse(speedController.setSpeed(-100.0));
		assertEquals(100.00, speedController.getSpeed(), 0.1);
	}
	
	/***************************** Helper functions *******************************/
	private void performInvalidConstructor(IEngine engine, double wheelDiameter, double gearRatio)
	{
		try {
			new SpeedController(engine, wheelDiameter, gearRatio);
			fail("Expected an exception on SpeedController constructor");
		} catch (HW3Exception e) {
			assertTrue(true);
		}
	}
}
