package cs4233.hw4;

import java.util.Observable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author Eric Greer	(egreer)
 * @date 10-18-08
 * CS4233-A08 HW4
 *
 */
public class DogDoor {
	
	private String name;
	private boolean doorIsOpen;
	private int openTime;
	private int timeLeft;
	private timer tThread;
	
	LinkedList<IObserver> observers;
	
	/**
	 * Default constructor for DogDoor, defaults the openTime to 5 seconds  
	 * @param doorName		The name of the door.
	 * @throws Exception	The doorName cannot be null, or empty string 
	 */
	DogDoor(String doorName) throws Exception {
		this(doorName,5000); // Default openTime = 5000 milliseconds
	}
	
	/**
	 * Constructor for DogDoor
	 * @param doorName		The name of the door.
	 * @param openTime		Length of time the door should remain open.
	 * @throws Exception 	The doorName cannot be null, or empty string
	 */
	DogDoor(String doorName, int openTime) throws Exception {
		// Duplicate Door Names?
		if ((doorName == null) || (doorName.trim().equals(""))) {
			throw new Exception("Invalid Dog Door Name!");
		}
		this.name = doorName.trim();
		this.openTime = openTime;
		this.timeLeft = 0;
		this.doorIsOpen = false;
		observers = new LinkedList<IObserver>(); 
		this.tThread = new timer();
	}
	
	/** Open opens the DogDoor if it is closed and extends the timeLeft by the 
	 * openTime amount if the door is all ready open. 
	 * 
	 * @throws Exception 	throws exception if NotifyObserver throws exception
	 */
	public void open() throws Exception {
		// If door is not already open, then open it
		if (!this.doorIsOpen) {
			this.doorIsOpen = true;
			this.notifyObservers();
		}
		// Add openTime to remainingTimeOpen
		if(tThread.isAlive()) tThread.stop();
		
		this.timeLeft = this.openTime;
		tThread = new timer();
		// Start Timer
		tThread.start();
	}
	
	
	/** Closes the DogDoor. 
	 *  
	 * @throws Exception	throws exception if NotifyObserver throws exception
	 */
	private void close() throws Exception {
		this.doorIsOpen = false;
		this.notifyObservers();
	}
	
	/** Returns the given name of the DogDoor
	 * 
	 * @return string name of DogDoor 
	 */
	public String getName() {
		return this.name;
	}
	
	/** isOpen determines the state of the DogDoor
	 * 
	 * @return True DogDoor is open, False DogDoor is closed
	 */
	public boolean isOpen() {
		return this.doorIsOpen;
	}
	
	/** registerObserver adds the IObserver to the list of 
	 * IObservers that can receive notifications  
	 * 
	 * @param observer	is the IObserver to be added
	 */
	public void registerObserver(IObserver observer) {
		observers.add(observer);
	}
	
	/** removeObserver removes the IObserver from the list of IObservers
	 * 
	 * @param observer	is the IObserver to be removed.
	 */
	public void removeObserver(IObserver observer) {
		observers.remove(observer);
	}
	
	/** Notifies any IObservers of the DogDoor's name and current state
	 * 
	 * @throws Exception throws exception if the monitor method update throws an exception  
	 */
	private void notifyObservers() throws Exception {
		for (Iterator<IObserver> i = observers.iterator(); i.hasNext();) {
			IObserver observer = (IObserver) i.next();
			observer.update(this.getName(),this.isOpen());
		}
	}
	
	/**
	 * timer is a subclass of DogDoor. It is used to create separate threads to run 
	 * the count down for closing the dog door . Counts down in 10ms intervals
	 *  
	 * @author Eric Greer
	 */
	private class timer extends Thread {

		//Default Constructor
		timer(){

		}

		/**
		 * Run is the main method for the timer thread. It counts down in a 10ms from time 
		 * remaining and the closes the door.
		 */
		public void run(){
			while(true) {
				try{
					sleep(10);
					timeLeft -= 10;
					
					 /* Testing output only For Testing Purposes Uncomment */
					System.out.print(DogDoor.this.getName() + " time remaining :" + timeLeft + "\n");
					
					// Close the Door
					if(timeLeft <= 0){ 
						DogDoor.this.close();
						return;
					}
				}catch(Exception e){
					return;
				}
			}
		}
	}
}
