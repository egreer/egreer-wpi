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
 * Test cases for the SimpleSpeedController class.
 * 
 * @author gpollice
 */
public class SimpleSpeedControllerTest
{
	private SimpleSpeedController controller;
	private Engine engine;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		engine = new Engine();
		controller = new SimpleSpeedController(1.2 * Math.PI);
		engine.setRunning(true);
	}

	/**
	 * Test method for {@link cs4233.hw3.SimpleSpeedController#changeSpeed(double)}.
	 */
	@Test
	public void testChangeSpeed()
	{
		controller.setEngine(engine, 100.0);
		controller.changeSpeed(100.0);
		assertEquals(2653, engine.getRPM());
		assertEquals(100.0, controller.getSpeed(), 0.1);
		
		controller.changeSpeed(-30000);
		assertEquals(0, controller.getSpeed());
	}
	
	/**
	 * Test method for getEngine
	 */
	@Test
	public void testGetEngine(){
		Engine testEngine = new Engine();
		SimpleSpeedController ssc =new SimpleSpeedController(1.0 );
		ssc.setEngine(testEngine, 1.0);
		assertFalse(ssc.getEngine().isRunning());
		assertEquals(ssc.getEngine().getRPM(), 0);
		assertTrue(ssc.getEngine().isGoingForward());
	}
}
