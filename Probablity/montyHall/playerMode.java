package montyHall;

import java.util.*;

public class playerMode {

// What I was given in the Group Roles email
	/** Takes the door the player has guessed and the door with a 
	 *  prize as arguments and returns a random integer, 1, 2, or 3 
	 *  which is neither to be removed. 
	 * 
	 * @param playerDoor	The door the player guessed
	 * @param correctDoor	The correct door with the prize behid it.
	 * @return				Returns the door that is to be removed, 0 is error
	 */
	public static int removeDoor(int playerDoor, int correctDoor){
		Random generator = new Random();
		
		// Checks to make sure that playerDoor, and correctDoor are vaild. 
		if(playerDoor > 3 || correctDoor > 3 || playerDoor < 1 || correctDoor < 1 ){
			return 0;
		}
		
		// Door that will be removed
		int removedDoor;
		
		// Loop that makes sure the returned isn't the playerDoor or the correctDoor 
		for(;;){
			removedDoor = generator.nextInt(3) + 1;
			
			if ( (removedDoor != playerDoor) && (removedDoor != correctDoor) ){
				return removedDoor;
			}
		}
	}
	
	
// Other functions that may be used by the interface.
// They made need a little tweaking based upon the interface
	
 	/** Choose updates one of the doors to be "open"
 	 * 	 
 	 * @param doorNumber	a number 1 thru 3
 	 */
 	public void choose(int doorNumber){
	 		 		 	
	 	this[(doorNumber-1)].open = true;
	 		 		 		 	
 	}

 	/** switch changes the door and returns the result of the  
 	 * 
 	 * @param switch  true if they want to switch
 	 * @return true if they won
 	 * @return false if lost
 	 */
 	public boolean theSwitch(boolean theSwitch){
 		
 		for (int i = 0 ; i < 3; i++ ){
 			if(!this[i].open){
 				return this[i].prize;
 			} 
 		}
 	
 	}
 
 
}