package cs4233.hw3;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Test cases for the composite vehicles that can be made from the components in
 * the homework assignment #3 code.
 * 
 * @author gpollice
 */
public class BasicCompositeVehicleTests
{
	private Vehicle vehicle;
	private NewModelEngine newModelEngine;
	private NewModelEngineAdapter nmeAdapter;
	private RadianDirectionControl radianDirectionControl;
	private RadianDirectionControlAdapter rdcAdapter;
	private SimpleSpeedController simpleSpeedController;
	private SimpleSpeedControllerAdapter sscAdapter;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		newModelEngine = new NewModelEngine();
		nmeAdapter = new NewModelEngineAdapter(newModelEngine);
		radianDirectionControl = new RadianDirectionControl();
		rdcAdapter = new RadianDirectionControlAdapter(radianDirectionControl);
		simpleSpeedController = new SimpleSpeedController(1.2 * Math.PI);
		sscAdapter = new SimpleSpeedControllerAdapter(simpleSpeedController, nmeAdapter, 100);
		vehicle = new Vehicle(nmeAdapter, sscAdapter, rdcAdapter);
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
}
