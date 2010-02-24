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

package JETTQ;

import JETTQ.Accelerometer.AccelerometerManager;
import JETTQ.Accelerometer.IAccelerometerObserver;
import JETTQ.LED.LEDManager;
import JETTQ.LED.TriColorLEDAdapter;
import JETTQ.Radio.IRadiogramObserver;
import JETTQ.Radio.Message;
import JETTQ.Radio.RadioManager;
import JETTQ.Switch.ISwitchObserver;
import JETTQ.Switch.SwitchManager;

import com.sun.spot.peripheral.ISpot;
import com.sun.spot.peripheral.Spot;
import com.sun.spot.sensorboard.EDemoBoard;

/**
 * Main class of the JETTQ framework.  The majority of the SPOT's functionality
 * can be accessed through this.  It also holds managers for the LED, Radio, Switch,
 * and Accelerometer components.
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 * CS4233-Team Project
 *
 */
public class JETTQManager extends Thread {
	
	private final static JETTQManager jm = new JETTQManager();
	EDemoBoard edb;
	ISpot ispot;
	
	/** To Enable decho output set to true **/
	private static boolean DEBUG = true;
	
	/** Sub Managers **/
	private AccelerometerManager am;
	//private IOManager iom;
	private LEDManager ledm;
	private RadioManager rm;
	private SwitchManager sm;
	
	/** Other Vars **/
	private static final String version = "1.2.11.0.0";
	
	
	/**
	 * Private Constructor
	**/
	private JETTQManager() {
		JETTQManager.decho("Initializing JETTQManager");
		edb = EDemoBoard.getInstance();
		am = AccelerometerManager.configureInstance(edb.getAccelerometer());
		ledm = LEDManager.getInstance();
		ispot = Spot.getInstance();
		addTriColorLEDsToLEDManager();
		//IO
		rm = RadioManager.configureInstance(ispot.getRadioPolicyManager());
		sm = SwitchManager.configureInstance(edb.getSwitches());
	}
	
	/**
	 * Function retrieves the JETTQ Framework Manager
	 * @return JETTQManager object for the Framework
	**/
	public static JETTQManager getInstance() {
		JETTQManager.decho("Returning JETTQManager");
		return jm;
	}
	
	/**
	 * Function returns the version number of the Framework
	 * @return String representing the Framework version
	**/
	public static String getVersion() {
		JETTQManager.decho("Returning Framework Version String");
		return version;
	}
	
	/*** ACCELEROMETER FUNCTIONS ***/
	
	/**
	 * Registers an observer for the accelerometer.
	 * @param iao an IAccelerometerObserver to register
	 */
	public void registerAccelerometerObserver(IAccelerometerObserver iao) {
		this.am.registerObserver(iao);
	}

	
	/**
	 * Function retrieves the Accelerometer Manager
	 * @return AccelerometerManager object
	**/
	public AccelerometerManager getAccelerometerManager() {
		JETTQManager.decho("Returning AccelerometerManager");
		return this.am;
	}
	
	
	/*** LED FUNCTIONS ***/
	
	/**
	 * Function converts the array of ITriColorLEDs to ILEDs and places
	 * them in the LEDManager 
	**/
	//Move up?
		private void addTriColorLEDsToLEDManager() {
			JETTQManager.decho("Converting LEDs");
			for(int i = 0; i < this.edb.getLEDs().length; i++) {
				JETTQManager.decho("Converting TriColorLED #"+i);
				this.ledm.addILED(new TriColorLEDAdapter(this.edb.getLEDs()[i]));
			}
			this.ledm.setRGBColor(255, 255, 255);
			this.ledm.allOn();
		}

	
	/**
	 * Function retrieves the LED Manager
	 * @return LEDManager object
	**/
	public LEDManager getLEDManager() {
		JETTQManager.decho("Returning LEDManager");
		return this.ledm;
	}
		
	/**
	 * Turns on all LEDs
	 */
	public void turnLEDsOn() {
		this.ledm.allOn();
	}
	
	/**
	 * Turns off all LEDs
	 */
	public void turnLEDsOff() {
		this.ledm.allOff();
	}
	
	/**
	 * Sets the color of all LEDs to an RGB value
	 * @param r the amount of red
	 * @param g the amount of green
	 * @param b the amount of blue
	 */
	public void setLEDsRGBColor(int r, int g,  int b) {
		this.ledm.setRGBColor(r, g, b);
	}
	

	/*** RADIO FUNCTIONS ***/
	
	/**
	 * Gets the IEEE address of the SPOT's radio
	 * @return String that represents the IEEE address.
	 */
	public static String getIEEEAddress() {
		JETTQManager.decho("Obtaining IEEE Address");
		return System.getProperty("IEEE_ADDRESS");
	}
	
	/**
	 * Function retrieves the Radio Manager
	 * @return RadioManager object
	**/
	public RadioManager getRadioManager() {
		JETTQManager.decho("Returning RadioManager");
		return this.rm;
	}
	

	/** GRAM RADIO **/
	
	/**
	 * Broadcasts a string on a specified port
	 * @param str String you want to send
	 * @param port Port you want to send on
	 */
	public void broadcast(String str, int port) {
		this.rm.sendMessageOnce(new Message("",0,str), port);
	}

	/**
	 * Broadcasts a string on the default port
	 * @param str String you want to send
	 */
	public void broadcast(String str) {
		this.rm.sendMessageOnce(new Message("",0,str));
	}
	
	
	/**
	 * Registers a listener to the GramRadio
	 * @param rgl IRadiogramObserver to register
	 * @param portNumber Port to listen on
	 */
	public void registerRadiogramListener(IRadiogramObserver rgl, int portNumber) {
		JETTQManager.decho("Registering Radiogram Observer");
		this.rm.registerRadiogramListener(rgl, portNumber);
	}
	

	/** Switch manager functions **/
	
	/**
	 * Get the switch manager
	 * @return SwitchManager object
	 */
	public SwitchManager getSwitchManager(){
		return sm;
	}
	

	/**
	 * Registers the observer for the switch
	 */
	public void registerSwitchObserver(ISwitchObserver iso) {
		sm.registerObserver(iso);
	}
	

	
	/*** DEBUGGING PRINT FUNCTION ***/
	
	/**
	 * Function to output given messages to standard output
	 * @param msg A String representing the message to be output.
	**/
	public static synchronized void decho(String msg) {
		if(DEBUG) {
			System.out.println(msg);
			System.out.flush();
		}
	}
	
	/**
	 * Function turns on the Debug output function
	**/
	public static void turnDebugOutputOn() {
		DEBUG = true;
	}
	
	/**
	 * Function turns off the Debug output function
	**/
	public static void turnDebugOutputOff() {
		DEBUG = false;
	}
	
	/**
     * Pause for a specified time.
     *
     * @param time the number of milliseconds to pause
    **/
    public static void pause(long time) {
    	try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException ex) { 
        	JETTQManager.decho("Thread pause Interrupted!"); 
        }
    }
    
    /**
     * Easter Egg :p
     */
    public void run() {
    	while(true){
    		JETTQManager.decho("SEE SPOT RUN");
    		Thread.currentThread();
			Thread.yield();
		}
    }
}
