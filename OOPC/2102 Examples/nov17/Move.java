package nov17;

/**
 * Base class for all Moves to the pen in the toy system.
 * 
 * @author heineman
 */
public class Move {

	/** new location. */
	protected int toX;
	protected int toY;
	
	/**
	 * Base constructor that knows the destination to which to move.
	 * 
	 * @param toX   x destination of pen
	 * @param toY   y destination of pen
	 */
	public Move (int toX, int toY) {
		this.toX = toX;
		this.toY = toY;
	}
	
	/**
	 * Execute an operation with the pen, starting from the given position and
	 * terminating at our (toX, toY).
	 * 
	 * @param fromX     X position where move starts from
	 * @param fromY     Y position where move starts from
	 */
	public void execute (int fromX, int fromY) {
		System.out.println ("Move to " + toX + "," + toY);
	}

	/** 
	 * Return the x-coordinate of the final destination of our planned move. 
	 *
	 * @return   x coordinate
	 */
	public int getX() {
		return toY;
	}
	
	/** 
	 * Return the y-coordinate of the final destination of our planned move. 
	 *
	 * @return   y coordinate
	 */
	public int getY() {
		return toY;
	}

}
