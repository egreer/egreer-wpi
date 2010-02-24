package cs4233.hw3;
/**
 * This class tests the SimpleSpeedControllerAdapter
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @date 09-14-08
 * CS 4233-A08 HW3
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class SimpleSpeedControllerAdapterTest {

	private IEngine engine;
	
	@Before
	public void setUp()
	{
		engine = new Engine();
	}
	
	/** 
	 * Tests Default Constructor
	 */
	@Test
	public void testValidConstructor()
	{
		ISpeedController speedController = new SimpleSpeedControllerAdapter(new SimpleSpeedController(20.0),engine ,1.0 );
		assertNotNull(speedController);
	}
	
	/**
	 * Tests that the engine speed is set correctly
	 */
	@Test
	public void testSetSpeed()
	{
		ISpeedController speedController = new SimpleSpeedControllerAdapter(new SimpleSpeedController(1.2),engine ,100.0 );
		assertTrue(speedController.setSpeed(100.0));		// Speed = 100 cm./min.
		assertEquals(100.0, speedController.getSpeed());
	}
	
	/**
	 * Tests that the speed is retrieved accurately
	 */
	@Test
	public void testGetSpeed()
	{
		ISpeedController speedController = new SimpleSpeedControllerAdapter(new SimpleSpeedController(1.2),engine ,100.0 );
		assertTrue(speedController.setSpeed(100.0));		// Speed = 100 cm./min.
		assertEquals(100.0, speedController.getSpeed(), 0.1);
	}
	
	/**
	 * Tests that the engine returns correctly
	 */
	@Test
	public void testGetControlledEngine()
	{
		engine.setRunning(true);
		ISpeedController speedController = new SimpleSpeedControllerAdapter(new SimpleSpeedController(1.2),engine ,100.0 );
		assertTrue(speedController.getControlledEngine().isRunning());
	}
	
	/***************************** Negative tests *********************************/
	
	/**
	 * Tests an invalid speed set for proper return
	 */
	@Test
	public void testInvalidSetSpeed()
	{
		ISpeedController speedController = new SimpleSpeedControllerAdapter(new SimpleSpeedController(1.2),engine ,100.0 );
		speedController.setSpeed(100.0);
		assertFalse(speedController.setSpeed(-100.0));
		assertEquals(100.00, speedController.getSpeed(), 0.1);
		assertTrue(speedController.setSpeed(0));
	}
	
	
	
}
