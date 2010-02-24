/*
 * Created on Sep 28, 2008
 * 
 * JETTQ's framework for Sun Microsystem's SPOT (Small Programmable Object Technology).
 * Built for CS4233 in A-term of 2008.
 *
 * Copyright (C) 2008, Tim Navien, Eric Greer, Jason Codding, 
 * Qian Wei, Tyler Flaherty, Worcester Polytechnic Institute, JETTQ@wpi.edu.
 * 
 * The program is provided under the terms and conditions of the Eclipse Public License Version 1.0
 * ("EPL"). A copy of the EPL is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */

package JETTQ.Accelerometer;

import java.util.Vector;
import JETTQ.JETTQManager;
import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;

/**
 * Manager class for the SPOT's accelerometer.
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 */
public class AccelerometerManager {
	/** Variables **/
	
	private final static AccelerometerManager accelerometermanager = new AccelerometerManager();
	
	private final static int DEFAULT_NOTIFICATION_INTERVAL = 500; // 500 milliseconds = 0.5 seconds
	private final static int MINIMUM_NOTIFICATION_INTERVAL = 5; // 5 milliseconds
	
	private IAccelerometer3D accelerometer;
	private int notificationInterval;
	protected Thread accelThread;
	private Vector accelerometerObservers;
	
	
	/**
	 * Private Constructor
	**/
	private AccelerometerManager() {
		this.accelerometerObservers = new Vector();
		this.notificationInterval = DEFAULT_NOTIFICATION_INTERVAL;	
	}
	
	
	/** Public Methods **/
	
	/**
	 * Get this instance of the AccelerometerManager
	 * @return AccelerometerManager
	 */
	public static AccelerometerManager getInstance() {
		return accelerometermanager;
	}
	
	/**
	 * Configures the Accelerometer manager with the specified IAccelerometer3D
	 * @param ia3d the IAccelerometer3D for the manager to use
	 * @return AccelerometerManager configured to the IAccelerometer3D
	 */
	public static AccelerometerManager configureInstance(IAccelerometer3D ia3d) {
		accelerometermanager.accelerometer = ia3d;
		accelerometermanager.runScheduledNotify();
		return accelerometermanager;
	}
	
	/**
	 * Function retrieves the accelerometer
	 * @return IAccelerometer3D object representing the Accelerometer
	**/
	public IAccelerometer3D getAccelerometer() {
		return this.accelerometer;
	}

	/** Observation Functions **/
	
	/**
	 * Registers the observer for the accelerometer
	 */
	public void registerObserver(IAccelerometerObserver iao) {
		this.accelerometerObservers.addElement(iao);
	}
	
	/**
	 * Removes the observer for the accelerometer
	 * @param iao the accelerometer observer to be removed
	 */
	public void removeObserver(IAccelerometerObserver iao) {
		this.accelerometerObservers.removeElement(iao);
	}
	
	 /**
	  * Retrieves the notification interval
	  * @return the notification interval
	  */
	public int getNotificationInterval() {
		return this.notificationInterval;
	}
	
	/**
	 * Sets the notification interval
	 * @param ni the desired notification interval
	 */
	public void setNotificationInterval(int ni) {
		if(ni > MINIMUM_NOTIFICATION_INTERVAL) {
			this.notificationInterval = ni;
		} else {
			System.out.println("Interval too Short!");
		}
	}
	
	
	
	/**
	 * Begins the scheduled notifications to the observers
	 */
	private void runScheduledNotify() {
		this.accelThread = new Thread() {
			public boolean running = true;
			public void run () {
				while (running){
					try{
						notifyObservers();
						JETTQManager.pause(notificationInterval);
					}catch(Exception e){
						System.out.println("Failed to notify observers!");
					}
				}
			}
			public void interrupt(){
				JETTQManager.decho("Ending Accelerometer Notification");
				running = false;
			}
        };
		this.accelThread.start();
	}
	
	/**
	 * Notifies the observers of the accelerometer object
	 * @throws Exception if observers aren't properly notified
	 */
	public void notifyObservers() throws Exception{
		for (int i = 0; i < this.accelerometerObservers.size(); i++) {
			((IAccelerometerObserver)this.accelerometerObservers.elementAt(i)).update(this.accelerometer);
		}
	}
}
