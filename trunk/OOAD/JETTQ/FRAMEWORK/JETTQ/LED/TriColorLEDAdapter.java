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

import JETTQ.JETTQManager;

import com.sun.spot.sensorboard.peripheral.ITriColorLED;
import com.sun.spot.sensorboard.peripheral.LEDColor;

/**
 * The TriColorLEDAdapter extends ILED and handles the Tricolor LEDs on the SPOT.
 * It turns them on and off and adjusts their color.
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 */
public class TriColorLEDAdapter extends ILED {
	
	private ITriColorLED itcl;
	private static final boolean supportsRGBColor = true;
	private int intensity;
	
	/**
	 * TriColorLED constructor
	 * @param tcl the TriColor LED
	 */
	public TriColorLEDAdapter(ITriColorLED tcl) {
		this.itcl = tcl;
	}
	
	/**
	 * Gets the LED color of the TriColorLED
	 * @return the led color
	 */
	public LEDColor getColor() {
		return this.itcl.getColor();
	}

	/**
	 * Gets the intensity of the TriColorLED
	 * @return the intensity
	 */
	public int getIntensity() {
		return this.intensity;
	}

	/**
	 * Checks if the TriColorLED is on
	 * @return true if the TriColorLED is on, false if it is off
	 */
	public boolean isOn() {
		return this.itcl.isOn();
	}

	/**
	 * Set the LED color of the TriColorLED
	 * @param ledcolor the led color that the TriColorLED will be set to 
	 */
	public void setColor(LEDColor ledcolor) {
		this.itcl.setColor(ledcolor);
	}

	/**
	 * Sets the RGB color of the TriColorLED
	 * r the red value of the associated led
	 * g the green value of the associated led
	 * b the blue value of the associated led
	 */
	public void setRGBColor(int r, int g, int b) {
		this.itcl.setRGB(r,g,b);		
	}

	/**
	 * Turns off the TriColorLED
	 */
	public void turnOff() {
		this.itcl.setOff();
	}

	/**
	 * Turns on the TriColorLED
	 */
	public void turnOn() {
		JETTQManager.decho("Turning LED on from TriColorLEDAdapter");
		this.itcl.setOn();
	}

	/**
	 * Verifies if the TriColorLED support RGB colors
	 * @return true if it does support rgb colors, false if it does not
	 */
	public boolean supportsRGBColors() {
		return supportsRGBColor;
	}

}
