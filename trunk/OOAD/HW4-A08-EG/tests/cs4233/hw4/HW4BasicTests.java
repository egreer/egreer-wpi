/*
 * Created on Sep 15, 2008
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu. 
 *
 * The program is provided under the terms and conditions of
 * the Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL
 * is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package cs4233.hw4;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

/**
 * These are the basic tests that 
 * @author gpollice
 * @version Sep 15, 2008
 */
public class HW4BasicTests
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
	public void testOneDoorOneRemote() throws Exception
	{
		door1.registerObserver(monitor1);
		remote1.openDoor(door1);
		Thread.sleep(600);
		logStream1.close();
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertEquals("Door one open", reader.readLine());
		assertEquals("Door one close", reader.readLine());
		assertNull(reader.readLine());
	}
	
	@Test
	public void testTwoDoorsOneRemote() throws Exception
	{
		door1.registerObserver(monitor1);
		door2.registerObserver(monitor1);
		remote1.addDoor(door2);
		remote1.openDoor(door1);
		Thread.sleep(100);
		remote1.openDoor(door2);
		Thread.sleep(600);
		logStream1.close();
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertEquals("Door one open", reader.readLine());
		assertEquals("Door two open", reader.readLine());
		assertEquals("Door one close", reader.readLine());
		assertEquals("Door two close", reader.readLine());
		assertNull(reader.readLine());
	}
}
