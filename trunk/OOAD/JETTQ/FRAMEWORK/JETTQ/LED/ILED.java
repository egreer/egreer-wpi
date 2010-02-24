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

import com.sun.spot.sensorboard.peripheral.LEDColor;

/**
 * Abstract class for using the LEDs on the SPOT.  Implements only the setIntensity method.
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 */
public abstract class ILED {
	
	public final static int DIM_INTENSITY = 1;
	public final static int MODERATE_INTENSITY = 2;
	public final static int BRIGHT_INTENSITY = 3;
	
	public final static int RGB_MINIMUM_VALUE = 0;
	public final static int RGB_INTENSITY_INTERVAL = 85; // MAX RGB / # intensity intervals
	public final static int RGB_MAXIMUM_VALUE = 255;
	
	/**
	 * Function determines whether or not the LED is ON
	 * @return True if on, False otherwise
	 */
	public abstract boolean isOn();
	
	/**
	 * Function turns ON the LED
	**/
	public abstract void turnOn();
	
	/**
	 * Function turns OFF the LED
	**/
	public abstract void turnOff();
	
	/**
	 * Function determines if an LED supports RGB Color codes
	 * @return True if LED supports RGB color codes, false otherwise
	**/
	public abstract boolean supportsRGBColors();
	
	/**
	 * Function sets the Color displayed by the LED
	 * @param r Int representing the RED color value (0-255)
	 * @param g Int representing the GREEN color value (0-255)
	 * @param b Int representing the BLUE color value (0-255)
	 */
	public abstract void setRGBColor(int r, int g, int b);
	
	/**
	 * Function set the Color as specified in the given LEDColor object
	 * @param ledcolor The LEDColor object containing the color to display
	**/
	public abstract void setColor(LEDColor ledcolor);
	
	/**
	 * Function gets the current Color as a LEDColor object
	 * @return the LEDColor of the LED
	**/
	public abstract LEDColor getColor();
	
	/**
	 * Function retrieves the current display Intensity
	 * @return int representing how bright the LED is.
	**/
	public abstract int getIntensity();
	
	/**
	 * Function sets the current display Intensity
	 * @param intensity The new Intensity
	 */
	public void setIntensity(int intensity) {
		if ((intensity < DIM_INTENSITY) || (intensity > BRIGHT_INTENSITY)) {
			// Error, intensity out of range
			Exception e = new RuntimeException("Intensity out of range");
			e.printStackTrace();
		}
		// Get Current Percentage from total range 
		double rP = this.getColor().red() / RGB_MAXIMUM_VALUE;
		double gP = this.getColor().green() / RGB_MAXIMUM_VALUE;
		double bP = this.getColor().blue() / RGB_MAXIMUM_VALUE;
		// Calculate new value within Intensity range
		int r = (int)((intensity * RGB_INTENSITY_INTERVAL) * rP);
		int g = (int)((intensity * RGB_INTENSITY_INTERVAL) * gP);
		int b = (int)((intensity * RGB_INTENSITY_INTERVAL) * bP);
		
		this.setRGBColor(r,g,b);
	}
	
}
