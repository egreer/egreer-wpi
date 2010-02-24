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
import org.junit.*;

/**
 * Test cases for the Vehicle class.
 * 
 * @author gpollice
 */
public class VehicleTest
{
	private Vehicle vehicle;
	private IEngine engine;
	private ISpeedController speedController;
	private IDirectionController directionController;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		engine = new Engine();
		speedController = new SpeedController(engine, 6.0, 100.0);
		directionController = new DirectionController();
		vehicle = new Vehicle(engine, speedController, directionController);
	}

	/**
	 * Test method for {@link cs4233.hw3.Vehicle#setSpeed(double)}.
	 */
	@Test
	public void testSetSpeed()
	{
		vehicle.start();
		vehicle.setSpeed(200.0);
		assertEquals(200.0, vehicle.getSpeed(), 0.1);
	}

	/**
	 * Test method for {@link cs4233.hw3.Vehicle#accelerate(double)}.
	 */
	@Test
	public void testAccelerate()
	{
		vehicle.start();
		vehicle.setSpeed(200.0);
		vehicle.accelerate(50.0);
		assertEquals(250.0, vehicle.getSpeed(), 0.1);
	}

	/**
	 * Test method for {@link cs4233.hw3.Vehicle#decelerate(double)}.
	 */
	@Test
	public void testDecelerate()
	{
		vehicle.start();
		vehicle.setSpeed(200.0);
		vehicle.decelerate(50.0);
		assertEquals(150.0, vehicle.getSpeed(), 0.1);
	}
	
	@Test
	public void testDecelerateToStop()
	{
		vehicle.start();
		vehicle.setSpeed(50.0);
		vehicle.decelerate(50.0);
		assertEquals(0.0, vehicle.getSpeed(), 0.0);
		vehicle.accelerate(50.0);
		vehicle.decelerate(51.0);
		assertEquals(0.0, vehicle.getSpeed(), 0.0);
	}

	/**
	 * Test method for {@link cs4233.hw3.Vehicle#goForward()}.
	 */
	@Test
	public void testGoForwardAndBackward()
	{
		vehicle.start();
		assertTrue(vehicle.isGoingForward());
		vehicle.goBackward();
		assertFalse(vehicle.isGoingForward());
		vehicle.goForward();
		assertTrue(vehicle.isGoingForward());
	}

	/**
	 * Test method for {@link cs4233.hw3.Vehicle#setDirection(double)}.
	 */
	@Test
	public void testSetDirection()
	{
		vehicle.start();
		vehicle.setDirection(90.0);
		assertEquals(90.0, vehicle.getDirection(), 0.0);
	}

	/**
	 * Test method for {@link cs4233.hw3.Vehicle#turn(double)}.
	 */
	@Test
	public void testTurn()
	{
		vehicle.start();
		vehicle.setDirection(90.0);
		vehicle.turn(-45.0);
		assertEquals(45.0, vehicle.getDirection(), 0.0);
	}

	@Test
	public void testStop()
	{
		vehicle.start();
		vehicle.setSpeed(100.0);
		vehicle.stop();
		assertFalse(vehicle.isRunning());
		assertEquals(0.0, vehicle.getSpeed(), 0.0);
	}

	/****************************** Negative tests ***************************/
	@Test
	public void testInvalidVehicleConstructor()
	{
		performInvalidConstructor(null, directionController);
		performInvalidConstructor(speedController, null);
	}
	
	@Test
	public void testSetSpeedWhileStopped()
	{
		try {
			vehicle.setSpeed(100.0);
			fail("Expected exception while setting the speed to a stopped vehicle");
		} catch (HW3Exception e) {
			assertEquals(0.0, vehicle.getSpeed(), 0.0);
		}
	}
	
	@Test
	public void testSetNegativeSpeed()
	{
		vehicle.start();
		try {
			vehicle.setSpeed(-100.0);
			fail("Expected exception while setting the speed to a stopped vehicle");
		} catch (HW3Exception e) {
			assertEquals(0.0, vehicle.getSpeed(), 0.0);
		}
	}
	
	@Test
	public void testNegativeAcceleration()
	{
		vehicle.start();
		try {
			vehicle.accelerate(-20.0);
			fail("Expected exception on acceleration negatively");
		} catch (HW3Exception e) {
			assertEquals(0.0, vehicle.getSpeed(), 0.0);
		}
	}
	
	@Test
	public void testAccelerationToStoppedVehicle()
	{
		try {
			vehicle.accelerate(20.0);
			fail("Expected exception on acceleration to a stopped vehicle");
		} catch (HW3Exception e) {
			assertEquals(0.0, vehicle.getSpeed(), 0.0);
		}
	}

	@Test
	public void testNegativeDeceleration()
	{
		vehicle.start();
		try {
			vehicle.decelerate(-20.0);
			fail("Expected exception on deceleration negatively");
		} catch (HW3Exception e) {
			assertEquals(0.0, vehicle.getSpeed(), 0.0);
		}
	}
	
	@Test
	public void testDecelerationToStoppedVehicle()
	{
		try {
			vehicle.decelerate(20.0);
			fail("Expected exception on deceleration to a stopped vehicle");
		} catch (HW3Exception e) {
			assertEquals(0.0, vehicle.getSpeed(), 0.0);
		}
	}

	/****************************** Helper methods **************************/
	private void performInvalidConstructor(ISpeedController speedController,
			IDirectionController directionController)
	{
		try {
			new Vehicle(engine, speedController, directionController);
			fail("Expected exception on vehicle constructor");
		} catch (HW3Exception e) {
			assertTrue(true);
		}
	}
}
