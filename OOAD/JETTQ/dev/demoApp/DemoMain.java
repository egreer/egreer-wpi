package demoApp;
/**
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 * CS4233-Team Project
 *
 */
import javax.microedition.midlet.MIDletStateChangeException;

import JETTQ.*;
import JETTQ.Accelerometer.AccelerometerManager;
import JETTQ.LED.LEDManager;

/**
 * Main class for the Homework 5 demo.  Sets up various managers
 * and registers an observer to the AccelerometerManager.
 * 
 * @author Eric Greer
 *
 */
public class DemoMain extends javax.microedition.midlet.MIDlet {
	
	JETTQManager jetman = JETTQManager.getInstance();
	AccelerometerManager am = jetman.getAccelerometerManager();
	LEDManager lm = jetman.getLEDManager();
	
	protected void startApp() throws MIDletStateChangeException {
		System.out.println("Start App");// testing
		jetman.setLEDsRGBColor(255, 0, 0);
		System.out.println("Turning all LEDs ON");
		jetman.turnLEDsOn();
		
		ColorChangerManager ccm = new ColorChangerManager(lm.getLEDs());
		jetman.registerAccelerometerObserver(ccm);
		System.out.println("Registered Observers");// testing

		am.setNotificationInterval(10);

		System.out.println("Set Notification Interval"); // testing
		
		
		while(true) {
			//Something to demo board
		}
	}
	
	/**
	 * Called when the application is stopped (if the SPOT is turned off or reset).
	 * Turns off all LEDs.
	 */
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		jetman.turnLEDsOff();
	}
	
	/**
	 * Called when the application is paused.
	 * We choose not to do anything.
	 */
	protected void pauseApp() {
	}

}
