package nov17;

/**
 * Represents a small toy that can draw and move a pen over a 2d Plane.
 * 
 * @author heineman
 */
public class Toy {

	/** Knows the first Node in the list. */
	MoveNode head;
	
	/** The current location of the pen. */
	int x = 0;
	int y = 0;
	
	/**
	 * Add a move to the end of our list of commands.
	 * 
	 * @param m   A move of the pen.
	 */
	public void add (Move m) {
		if (head == null) {
			head = new MoveNode (m);
			return;
		}
		
		// append to the end of the list.
		MoveNode node = head;
		while (node.next != null) {
			node = node.next;
		}
		
		node.next = new MoveNode (m);
	}
	
	/**
	 * Reset pen location to (0,0) and throw away the head, in effect deleting
	 * the linked list of commands.
	 */
	public void reset() {
		head = null;
		x = 0;
		y = 0;
	}
	
	/**
	 * Compute the operations made to the Pen in the toy.  Note that there is
	 * a slight twist to our operations. Once this is done, we delete all the 
	 * commands, because that is our purpose.  Note that the pen is left at 
	 * its final location.
	 *
	 */
	public void draw() {
		MoveNode node = head;
		while (node != null) {
			// Get the Move object to operate with and invoke it.
			Move m = node.value;
			m.execute(x, y);
			
			// update our pen position
			x = m.getX();
			y = m.getY();
			
			node = node.next;   // advance
		}
	}

}
