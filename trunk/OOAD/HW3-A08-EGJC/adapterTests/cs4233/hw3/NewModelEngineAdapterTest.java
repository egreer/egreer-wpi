package cs4233.hw3;
/**
 * This class tests the NewModelEngineAdapter
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @date 09-14-08
 * CS 4233-A08 HW3
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;



/**
 * Test cases for the NewModelEngineAdapterTest.
 * 
 */

public class NewModelEngineAdapterTest {

	private IEngine engine;
	
	@Before
	public void setUp()
	{
		engine = new NewModelEngineAdapter(new NewModelEngine());
	}
	/** 
	 * Tests that the engine is initialized
	 */
	@Test
	public void testInitialConfiguration()
	{
		assertTrue(engine.isGoingForward());
		assertFalse(engine.isRunning());
		assertEquals(0, engine.getRPM());
	}
	
	/**
	 * Tests that gears change only when appropriate
	 */
	@Test 
	public void testForwardAndReverseGears()
	{
		engine.setGoingForward(false);
		assertFalse(engine.isGoingForward());
		//tests that gears don't change
		engine.setGoingForward(false);
		assertFalse(engine.isGoingForward());
	}
	
	/**
	 * Tests correct determination of engine state
	 */
	@Test
	public void testStartAndStop()
	{
		engine.setRunning(true);
		assertTrue(engine.isRunning());
		engine.setRunning(false);
		assertFalse(engine.isRunning());
	}
	
	/**
	 * Tests stop while moving
	 */
	@Test
	public void testStopWhileMoving()
	{
		engine.setRunning(true);
		engine.setRPM(500);
		engine.setRunning(false);
		assertEquals(0, engine.getRPM());
		engine.setRunning(true);
		engine.setRPM(500);
		engine.setGoingForward(false);
		engine.setRunning(false);
		assertTrue(engine.isGoingForward());
	}

}
