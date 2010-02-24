/*
 * Created on Aug 25, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw3;

import static org.junit.Assert.*;
import org.junit.*;
import cs4233.hw3.NewModelEngine.*;

/**
 * Test cases for the NewModelEngine.
 * 
 * @author gpollice
 */
public class NewModelEngineTest
{
	private NewModelEngine engine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		engine = new NewModelEngine();
	}

	@Test
	public void testInitialConfiguration()
	{
		assertEquals(Direction.FORWARD, engine.getDirection());
		assertEquals(EngineState.NOT_RUNNING, engine.getEngineState());
		assertEquals(0.0, engine.getEngineSpeed(), 0.0);
	}

	@Test
	public void testStartAndStop()
	{
		assertEquals(EngineState.NOT_RUNNING, engine.getEngineState());
		engine.start();
		assertEquals(EngineState.RUNNING, engine.getEngineState());
		engine.stop();
		assertEquals(EngineState.NOT_RUNNING, engine.getEngineState());
	}
	
	@Test
	public void testChangeSpeed()
	{
		engine.start();
		engine.changeEngineSpeed(45.0);
		assertEquals(45.0, engine.getEngineSpeed(), 0.0);
		engine.changeEngineSpeed(-15.0);
		assertEquals(30.0, engine.getEngineSpeed(), 0.0);
		engine.stop();
		assertEquals(0.0, engine.getEngineSpeed(), 0.0);
	}
	
	@Test
	public void testEngineConfigurationAfterStopping()
	{
		engine.start();
		engine.changeEngineSpeed(45.0);
		engine.changeDirection();
		assertEquals(Direction.REVERSE, engine.getDirection());
		engine.stop();
		assertEquals(Direction.FORWARD, engine.getDirection());
		assertEquals(0.0, engine.getEngineSpeed(), 0.0);
		assertEquals(EngineState.NOT_RUNNING, engine.getEngineState());
	}
	
	@Test
	public void testChangeSpeedBelowZero()
	{
		engine.start();
		engine.changeEngineSpeed(45.0);
		engine.changeEngineSpeed(-50.0);
		assertEquals(0.0, engine.getEngineSpeed());
	}
	
	@Test
	public void testChangeSpeedToStoppedEngine()
	{
		try {
			engine.changeEngineSpeed(10.0);
			fail("Expected exception changing the engine speed to a stopped engine");
		} catch (HW3Exception e) {
			assertTrue(true);
		}
	}
}