package demoApp;
/**
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 * CS4233-Team Project
 *
 */
import java.io.IOException;

import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import com.sun.spot.sensorboard.peripheral.LEDColor;

import JETTQ.LED.ILED;

/**
 * This class sets the color of a specific LED to an RGB color based on the tilt on the X, Y, and Z axes.
 * 
 * @author Eric Greer
 * @author Tyler Flaherty
 *
 */
public class ColorChanger {
	
	private ILED led;
	
	/**
	 * Default constructor.
	 * @param led the LED to manage the color of.
	 */
	public ColorChanger(ILED led) {
		this.led = led;
		System.out.print("Created Color changer for LED");
	}

	/**
	 * Changes color of the led based on the current acceleration of the SPOT.
	 * @param acc an IAccelerometer3D representing the accelerometer on the SPOT.
	 * @throws IOException
	 */
	public void changeColor(IAccelerometer3D acc ) throws IOException{
		System.out.println("Begin Change Color"); //testing
		LEDColor current = this.led.getColor();
		int	red = changeRed(current.red(), acc);
		int blue = changeBlue(current.blue(), acc);
		int green = changeGreen(current.green(), acc);
		this.led.setRGBColor(red, green, blue);
	}

	/**
	 * Determines the amount of red for the color of the LED based on the tilt
	 * on the X axis.
	 * 
	 * @param red testing parameter, may be removed later.
	 * @param acc the IAccelerometer3D to get the acceleration/tilt from
	 * @return an int representing the amount of red the final color should contain.
	 * @throws IOException
	 */
	public int changeRed(int red, IAccelerometer3D acc)throws IOException{
		return  (int) degreeToColor(Math.toDegrees(acc.getTiltX()));
	}	
	
	/**
	 * Determines the amount of blue for the color of the LED based on the tilt
	 * on the Y axis.
	 * @param blue testing parameter, may be removed later.
	 * @param acc the IAccelerometer3D to get the acceleration/tilt from.
	 * @return an int representing the amount of blue the final color should contain
	 * @throws IOException
	 */
	public int changeBlue(int blue, IAccelerometer3D acc)throws IOException{
		return (int) degreeToColor(Math.toDegrees(acc.getTiltY()));
	}
	
	/**
	 * Determines the amount of green for the color of the LED based on the tilt
	 * on the Z axis
	 * @param green testing parameter, may be removed later
	 * @param acc the IAccelerometer3D to get the acceleration/tilt from.
	 * @return an int representing the amount of green the final color should contain
	 * @throws IOException
	 */
	public int changeGreen(int green, IAccelerometer3D acc) throws IOException{
		return (int) degreeToColor(Math.toDegrees(acc.getTiltZ()));
	}

	/**
	 * Takes the tilt in degrees, adds 90 to make the range 0-180 degrees
	 * and then divides by 0.706 to get a number between 1 and 255 (valid
	 * RGB values).
	 * @param degrees the tilt in degrees.
	 * @return an int between 0 and 255
	 */
	public double degreeToColor(double degrees){
		return ((degrees + 90) / .706);
	}
}
