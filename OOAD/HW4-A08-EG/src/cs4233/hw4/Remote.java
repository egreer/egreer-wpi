package cs4233.hw4;

import java.util.Iterator;
import java.util.LinkedList;

/** Remote is a class that controls one or many DogDoors. 
 * 
 * @author Eric Greer	(egreer)
 * @date 10-18-08
 * CS4233-A08 HW4
 *
 */
public class Remote {
	private LinkedList<DogDoor> doors;
	
	/**
	 * Default constructor initializes the LinkedList of doors
	 */
	Remote() {
		this.doors = new LinkedList<DogDoor>();
	}
	
	/** addDoor adds a DogDoor to the remote's controlled doors 
	 * 
	 * @param dogDoor	the DogDoor to be added to the remote 
	 */
	public void addDoor(DogDoor dogDoor) {
		this.doors.add(dogDoor);
	}
	
	/** removeDoor removes a DogDoor from the remote's controlled doors 
	 * 
	 * @param dogDoor	the DogDoor to be removed from the remote
	 */
	public void removeDoor(DogDoor dogDoor) {
		this.doors.remove(dogDoor);
	}
	
	/** openDoor will open the specified DogDoor if it is in the list of 
	 * controlled doors    
	 * 
	 * @param dogDoor		the DogDoor to open 
	 * @throws Exception	if the DogDoor does not exist in the list.   
	 */
	public void openDoor(DogDoor dogDoor) throws Exception {
		boolean isControlled = false;
		Iterator<DogDoor> i = doors.iterator();
		
		//Loops through the list of doors for the matching door
		while (i.hasNext() && !isControlled) {
			DogDoor door = (DogDoor) i.next();
			if(door.getName().equals(dogDoor.getName())) {
				isControlled = true;	// Door is controlled so open door
				door.open();
			}
		}
		
		//Door Not Found!
		if (!isControlled) {
			throw new Exception("Door Not Controlled by this Remote!");
		}
	}
	
}
