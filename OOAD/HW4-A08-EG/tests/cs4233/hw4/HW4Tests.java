package cs4233.hw4;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import org.junit.*;


/** Tests the DogDoor functionality.
 * 
 * @author Eric Greer	(egreer)
 * @date 10-18-08
 * CS4233-A08 HW4
 *
 */
public class HW4Tests {

	private Monitor monitor1, monitor2;
	private DogDoor door1, door2, door3;
	private Remote remote1, remote2, remote3;
	private ByteArrayOutputStream logStream1;
	
	/** Sets up the variables
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		logStream1 = new ByteArrayOutputStream();
		monitor1 = new Monitor(logStream1);
		monitor2 = new Monitor();
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

	/**
	 * Tests one remote controlling a singular door
	 * @throws Exception
	 */
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

	/**
	 * Tests one remote controlling two doors
	 * @throws Exception
	 */
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

	/**
	 * Tests having 2 doors controlled by 2remotes, and the reset of the open time
	 * @throws Exception
	 */
	@Test
	public void testTwoDoorsTwoRemotes() throws Exception
	{
		door1.registerObserver(monitor1);
		door2.registerObserver(monitor1);
		door1.registerObserver(monitor2);
		door2.registerObserver(monitor2);
		remote1.addDoor(door2);
		remote2.addDoor(door1);
		
		remote1.openDoor(door1);
		Thread.sleep(100);
		remote1.openDoor(door2);
		Thread.sleep(100);
		remote2.openDoor(door1);
		Thread.sleep(600);
		logStream1.close();
		String log = logStream1.toString();
		BufferedReader reader = new BufferedReader(new StringReader(log));
		assertEquals("Door one open", reader.readLine());
		assertEquals("Door two open", reader.readLine());
		assertEquals("Door two close", reader.readLine());
		assertEquals("Door one close", reader.readLine());
		assertNull(reader.readLine());
	}
	
	/**
	 * Tests the observer methods in DogDoor
	 */
	@Test
	public void testsObservers(){
		door3.registerObserver(monitor1);
		assertTrue(door3.observers.contains(monitor1));
		door3.removeObserver(monitor1);
		assertFalse(door3.observers.contains(monitor1));
		
	}
	
	/**
	 * Tests trying to use an invalid DogDoor name (null, and "")
	 */
	@Test
	public void testInvalidDoorName(){
		try {
			door3 = new DogDoor(null); 
			fail("Expected exception Invalid name");
		} catch (Exception e) {
			assertTrue(true);
		}
		
		try {
			door3 = new DogDoor(" "); 
			fail("Expected exception Invalid name");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Tests the remote methods
	 * @throws Exception
	 */
	@Test
	public void testRemote() throws Exception{
		door1 = new DogDoor("door1");
		remote3.addDoor(door1);
		try {
			remote3.openDoor(door1);
			assertTrue(true);
		} catch (Exception e) {
			fail("Door 1 didn't open");
		}

		remote3.removeDoor(door1);
		
		try {
			remote3.openDoor(door1);
			fail("Expected exception Door Not Controlled by this remote");
		} catch (Exception e) {
			assertTrue(true);
		}
	
	}
}
