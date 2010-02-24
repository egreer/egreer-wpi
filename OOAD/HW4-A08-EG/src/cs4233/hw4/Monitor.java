package cs4233.hw4;

import java.io.OutputStream;

/** Monitor prints a message to a Output Stream about the door's status when the update
 * method is called. If no OutputStream is given then prints to Standard Out.   
 * 
 * @author Eric Greer	(egreer)
 * @date 10-18-08
 * CS4233-A08 HW4
 *
 */
public class Monitor implements IObserver {
	private OutputStream outputStream;
	
	/** Default constructor for the monitor updates will go to STD Output */
	Monitor() {
		this(null);
	}
	
	/** Monitors uses a OutputStream to write any status changes of any Door
	 *  it is registered to.
	 * 
	 * @param ostream	Takes a stream to write to 
	 */
	Monitor(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	/** Update records any indicators of door status received.
	 * 
	 * @param 	doorName	The name of the door
	 * @param 	isOpen		boolean indicating door status. True is open,, False closed.
	 * @throws		Throws Exception if there is a problem writing to the outputStream
	 */
	public void update(String doorName,boolean isOpen) throws Exception {
		String doorStatus = "Door " + doorName + " " + (isOpen ? "open" : "close") + "\n";
		
		if(this.outputStream != null) {
			this.outputStream.write(doorStatus.getBytes());
			
		} else {
			System.out.println(doorStatus);
		}
		
		//For Testing Purposes Uncomment
		//System.out.println(doorStatus);
	}
	
	
}
