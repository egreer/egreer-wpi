/*
 * Created on Sep 24, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw4;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

/**
 * Acceptance tests for homework #4, A08.
 * 
 * @author gpollice
 * @version Sep 24, 2008
 */
public class HW4AcceptanceTests
{
	private Monitor monitor1;
	private DogDoor door1, door2, door3;
	private Remote remote1, remote2, remote3;
	private ByteArrayOutputStream logStream1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		logStream1 = new ByteArrayOutputStream();
		monitor1 = new Monitor(logStream1);
		door1 = new DogDoor("one", 500);
		door2 = new DogDoor("two", 500);
		door3 = new DogDoor("three", 500);
		remote1 = new Remote();
		remote2 = new Remote();
		remote3 = new Remote();
		remote1.addDoor(door1);
		remote2.addDoor(door2);
		remote3.addDoor(door3);
	}

	@Test
	public void testTwoDoorsTwoRemotesOneMonitorNoDoubleOpen() throws Exception
	{
		remote2.addDoor(door1);
		remote1.addDoor(door2);
		door1.registerObserver(monitor1);
		door2.registerObserver(monitor1);
		remote1.openDoor(door1);
		Thread.sleep(100);
		remote1.openDoor(door2);
		Thread.sleep(100);
		remote2.openDoor(door1);
		Thread.sleep(250);
		remote2.openDoor(door2);
		Thread.sleep(600);
		logStream1.close();
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door two open"));
		assertTrue(findLine(reader, "Door one close"));
		assertTrue(findLine(reader, "Door two close"));
		assertNull(reader.readLine());
	}

	@Test
	public void testMultipleCloses() throws Exception
	{
		remote1.addDoor(door1);
		door1.registerObserver(monitor1);
		remote1.openDoor(door1);
		Thread.sleep(600);
		remote1.openDoor(door1);
		Thread.sleep(600);
		logStream1.close();
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door one close"));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door one close"));
		assertNull(reader.readLine());
	}

	@Test
	public void testTwoMonitors() throws Exception
	{
		remote1.addDoor(door1);
		door1.registerObserver(monitor1);
		ByteArrayOutputStream logStream2 = new ByteArrayOutputStream();
		Monitor monitor2 = new Monitor(logStream2);
		door1.registerObserver(monitor2);
		remote1.openDoor(door1);
		Thread.sleep(600);
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door one close"));
		assertNull(reader.readLine());
		log = logStream2.toString();
		reader = new BufferedReader(new StringReader(log));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door one close"));
		assertNull(reader.readLine());
	}

	@Test
	public void testRegisterTwoMonitorsThroughMonitors() throws Exception
	{
		remote1.addDoor(door1);
//		monitor1.monitor(door1);
		ByteArrayOutputStream logStream2 = new ByteArrayOutputStream();
		Monitor monitor2 = new Monitor(logStream2);
	//	monitor2.monitor(door1);
		remote1.openDoor(door1);
		Thread.sleep(600);
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door one close"));
		assertNull(reader.readLine());
		log = logStream2.toString();
		reader = new BufferedReader(new StringReader(log));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door one close"));
		assertNull(reader.readLine());
	}

	@Test
	public void testStopMonitoring() throws Exception
	{
		remote1.addDoor(door1);
	//	monitor1.monitor(door1);
		remote1.openDoor(door1);
		Thread.sleep(600);
	//	monitor1.stopMonitoring(door1);
		Thread.sleep(600);
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertTrue(findLine(reader, "Door one open"));
		assertTrue(findLine(reader, "Door one close"));
		assertNull(reader.readLine());
	}

	@Test
	public void testOpenInvalidDoor() throws Exception
	{
		remote1.addDoor(door1);
	//	monitor1.monitor(door1);
		remote1.removeDoor(door1);
		remote1.openDoor(door1);
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertNull(reader.readLine());
	}

	/********************************
	 * helper methods
	 * 
	 * @throws IOException
	 ********************
	 ************/
	private boolean findLine(BufferedReader reader, String lineToFind) throws IOException
	{
		String readerLine = reader.readLine();
		while (readerLine != null && !readerLine.equals(lineToFind)) {
			reader.readLine();
		}
		return lineToFind.equals(readerLine);
	}
}
