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
 * Test cases for the engine.
 * 
 * @author gpollice
 */
public class EngineTest
{
	private IEngine engine;
	
	@Before
	public void setUp()
	{
		engine = new Engine();
	}
	
	@Test
	public void testInitialConfiguration()
	{
		assertTrue(engine.isGoingForward());
		assertFalse(engine.isRunning());
		assertEquals(0, engine.getRPM());
	}
	
	@Test 
	public void testForwardAndReverseGears()
	{
		engine.setGoingForward(false);
		assertFalse(engine.isGoingForward());
	}
	
	@Test
	public void testStartAndStop()
	{
		engine.setRunning(true);
		assertTrue(engine.isRunning());
		engine.setRunning(false);
		assertFalse(engine.isRunning());
	}
	
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
		assertFalse(engine.isGoingForward());
	}
	
	@Test
	public void testValidRPM()
	{
		engine.setRPM(3000);
		assertEquals(3000, engine.getRPM());
	}
	
	@Test
	public void testInvalidRPM()
	{
		engine.setRPM(3000);
		try {
			engine.setRPM(-5);
			fail("Expected exception on negative RPM");
		} catch (HW3Exception e) {
			assertEquals(3000, engine.getRPM());
		}
	}
}
