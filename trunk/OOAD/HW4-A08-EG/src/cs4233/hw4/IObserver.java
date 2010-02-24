package cs4233.hw4;

/** IObserver is the interface for any class that would like to subscribe
 * to a door to receive updates about its status.
 * 
 * @author Eric Greer	(egreer)
 * @date 10-18-08
 * CS4233-A08 HW4
 */
public interface IObserver {
	
	/** update allows Observer to be informed of a change
	 * 
	 * @param doorName The door which changed statues
	 * @param isOpen The status of the door that actually changed
	 * @throws 		Throws an exception if there is an error while writing
	 */
	public void update(String doorName, boolean isOpen) throws Exception;
}
