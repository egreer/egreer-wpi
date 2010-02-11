package lpf.controllers;

/**
 * 
 * Interface for controllers used in the grid games
 * 
 * @author Andrew Yee
 *
 */
public interface IGridGameController {

	/**
	 * The process to be executed by the controller
	 */
	public void process() throws ProcessFailedException;
	
}
