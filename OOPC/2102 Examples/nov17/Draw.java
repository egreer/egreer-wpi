package nov17;

/**
 * A specialized Move that holds the pen down during the move
 * 
 * @author heineman
 */
public class Draw extends Move {

	/**
	 * Make use of our parent's constructor.
	 * 
	 * @param newX     new location as X coordinate
	 * @param newY     new location as Y coordinate
	 */
	public Draw (int newX, int newY) {
		super (newX, newY);
	}
	
	/**
	 * Execute the draw by drawing a line from (fromX, fromY) to (toX, toY).
	 * 
	 * @param    fromX    x coordinate of where we started move
	 * @param    fromY    y coordinate of where we started move 
	 */
	public void execute (int fromX, int fromY) {
		System.out.println ("Draw " + fromX + "," + fromY + " to " + toX + "," + toY);
	}

}
