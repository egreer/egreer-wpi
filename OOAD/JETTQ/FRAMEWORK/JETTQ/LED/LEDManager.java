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
package JETTQ.LED;

import java.util.Enumeration;
import java.util.Vector;

import JETTQ.JETTQManager;

import com.sun.spot.sensorboard.peripheral.LEDColor;

/**
 * The LEDManager class handles the use of the LEDs.
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 */
public class LEDManager {
	
	private final static LEDManager ledmanager = new LEDManager();
	private Vector leds; 
	
	/** Private Constructors **/
	private LEDManager() {
		JETTQManager.decho("Initializing JETTQManager");
		this.leds = new Vector();
	}
	
	/** Public Methods **/
	
	/**
	 * Adds an ILed Element
	 * @param iled the ILed to be added
	 */
	public void addILED(ILED iled) {
		JETTQManager.decho("Adding ILED");
		this.leds.addElement(iled);
	}
	
	/**
	 * Removes an ILed Element
	 * @param iled the ILed to be removed
	 */
	public void removeILED(ILED iled) {
		JETTQManager.decho("Removing ILED");
		this.leds.removeElement(iled);
	}
	
	/**
	 * Function retrieves the LED Manager
	 * @return The LED Manager
	**/
	static public LEDManager getInstance() {
		JETTQManager.decho("Returning JETTQManager");
		return ledmanager;
	}
	
	/**
	 * sets the color of the LED
	 * @param ledc the LED color
	 */
	public void setColor(LEDColor ledc) {
		this.setRGBColor(ledc.red(), ledc.green(), ledc.blue());
	}
	
	/**
	 * Sets the RGB color of an LED
	 * @param r the color of the red LED
	 * @param g the color of the green LED
	 * @param b the color of the blue LED
	 */
	public void setRGBColor(int r, int g, int b) {
		for (int i = 0; i < this.getNumberOfLEDs(); i++) {
			((ILED)leds.elementAt(i)).setRGBColor(r,g,b);
		}
	}
	
	/**
	 * Sets the intensity of all LEDs to the intensity value
	 * Presets are Dim = 1, Moderate = 2, High = 3
	 * @param intensity
	 */
	public void setIntensity(int intensity) {
		for (int i = 0; i < this.getNumberOfLEDs(); i++) {
			((ILED)leds.elementAt(i)).setIntensity(intensity);
		}
	}
	
	/**
	 * Function attempts to display the given number in binary using the LEDs
	 * @param number The number to be displayed in binary
	 * @throws Exception if number is negative or too large
	**/
	public void displayNumberAsBinary(int number) throws Exception {
		if (number < 0) {
			throw new Exception("Negative Numbers Not Allowed!");
		} else if (pow(2,this.getNumberOfLEDs()) <= number) {
		//	throw new Exception("Number too large to display in binary with only " + this.getNumberOfLEDs() + "LEDs");
		} else {
			for (int i = this.getNumberOfLEDs() - 1, bit = 1; i >= 0; i--, bit <<= 1) {
				if ((number & bit) != 0) {
					((ILED)leds.elementAt(i)).turnOn();
				} else {
					((ILED)leds.elementAt(i)).turnOff();
				}
			}
		}
	}

	/** The Math.pow method because the Math package used by the SPOTS
	 * does not include this function.
	 * @param x the base of the exponent
	 * @param y	The power to raise to 
	 * @return 	An integer of x^y
    */
	int pow( int x, int y){
		int z = x; 

		for( int i = 1; i < y; i++ ){
			z *= x;
		} 
		return z;
	}

	/**
	 * Function retrieves the number of LEDs managed
	 * @return Int the number of LEDs
	**/
	public int getNumberOfLEDs() {
		return this.leds.size();
	}
	
	/**
	 * Turns an LED on or off based on a boolean value
	 * @param i Index of the LED to set
	 * @param state if true, turns the LED on, otherwise turns it off.
	 */
	public void setLED(int i,boolean state) {
		if(state) {
			((ILED)this.leds.elementAt(i)).turnOn();
		} else {
			((ILED)this.leds.elementAt(i)).turnOff();
		}
	}
	
	/**
	 * Set an LED's color to an RGB value
	 * @param i Index of the LED to set
	 * @param r Amount of red
	 * @param g Amount of green
	 * @param b Amount of blue
	 */
	public void setLEDColor(int i, int r, int g, int b ) {
		if (i < this.leds.size()){
		((ILED) this.leds.elementAt(i)).setRGBColor(r, g, b);
		}
	}
	
	/**
	 * Get the color of a specific LED
	 * @param i Index of the LED to check
	 * @return LEDColor object for the specified LED
	 */
	public LEDColor getColor(int i) { 
		return ((ILED) this.leds.elementAt(i)).getColor();
	}
	
	/**
	 * Function turns all LEDs off
	**/
	public void allOff() { 
		for (int i = 0; i < leds.size(); i++) {
			((ILED)leds.elementAt(i)).turnOff();
		}
	}
	
	/**
	 * Function turns all LEDs on
	**/
	public void allOn() { 
		for (int i = 0; i < leds.size(); i++) {
			((ILED)leds.elementAt(i)).turnOn();
		}
	}
	
	/**
	 * Gets the possible LED elements
	 * @return the LED elements available for use
	 */
	public Enumeration getLEDs() {
		return this.leds.elements();
	}

	/**
	 * Checks if RGB colors are supported
	 * @return true if they are, false if not
	 */
	public boolean supportsRGBColors() {
		for(int i = 0; i < this.getNumberOfLEDs(); i++) {
			if (!((ILED)this.leds.elementAt(i)).supportsRGBColors()) {
				return false;
			}
		}
		return true;
	}
}
