package cs4233.hw3;
/**
 * This class tests the RadianDirectionControlAdapter
 * 
 * @author Eric Greer  		(egreer)
 * @author Jason Codding  	(jcodding)
 * @date 09-14-08
 * CS 4233-A08 HW3
 */
import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class RadianDirectionControlAdapterTest {

	/**
	 * Default Constructor
	 */
	@Test
	public void testDefaultConstructor()
	{
		IDirectionController directionController = new RadianDirectionControlAdapter(new RadianDirectionControl());
		assertEquals(0.0, directionController.getCurrentDirection(), 0.0);
	}


	/**
	 * Tests that the direction changes appropriately 
	 */
	@Test
	public void testSetDirection()
	{
		IDirectionController directionController = new RadianDirectionControlAdapter(new RadianDirectionControl());
		directionController.setCurrentDirection(90.0);
		assertEquals(90.0, directionController.getCurrentDirection(), 0.0);
		directionController.setCurrentDirection(390.0);
		assertEquals(30.0, directionController.getCurrentDirection(), 0.0);
		directionController.setCurrentDirection(-90.0);
		assertEquals(270.0, directionController.getCurrentDirection(), 0.0);
	}
	
	/**
	 * Tests that turns are performed correctly
	 */
	@Test
	public void testTurn()
	{
		IDirectionController directionController = new RadianDirectionControlAdapter(new RadianDirectionControl());
		directionController.turn(90.0);
		assertEquals(90.0, directionController.getCurrentDirection(), 0.0);
		directionController.turn(-450.0);
		assertEquals(0.0, directionController.getCurrentDirection(), 0.0);
	}
	
}
