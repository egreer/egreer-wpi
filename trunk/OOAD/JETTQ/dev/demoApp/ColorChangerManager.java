package demoApp;
/**
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 * CS4233-Team Project
 *
 */
import java.util.Enumeration;
import java.util.Vector;

import JETTQ.Accelerometer.IAccelerometerObserver;
import JETTQ.LED.ILED;

import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;

/** This class should be responsible to subscribing to the acceleration, 
 *  Creating a colorchanger for each LED;
 *  Dishing it out to each individual led's ColorChanger;   
 * 
 * @author Eric Greer
 * @author Tyler Flaherty
 *
 */
public class ColorChangerManager implements IAccelerometerObserver{
	Vector leds = new Vector(8, 0);
	int numLEDs = 0; 
	ColorChanger[] ledChangers;
	
	public ColorChangerManager(Enumeration LEDs){
		while(LEDs.hasMoreElements()){
			numLEDs++;
			leds.addElement(LEDs.nextElement());
		}
		
		System.out.println("number of leds:" + numLEDs); // testing
		ledChangers = new ColorChanger[numLEDs];
		this.setupColorChangers();
	}
	
	/**
	 * Creates a ColorChanger for each LED and adds it to the array of ColorChangers.
	 */
	private void setupColorChangers() {
		int i = 0;
		while (!leds.isEmpty() && i < numLEDs){
			System.out.println("add element:" + i); // testing
			ILED led = (ILED)leds.elementAt(i);
			led.turnOn();
			this.ledChangers[i] = new ColorChanger(led);
			
			i++;
		}
	}


	/**
	 * Updates the color of the LEDs based on the tilt.
	 * @param ia3d an IAccelerometer3D to get the tilt from.
	 */
	public void update(IAccelerometer3D ia3d){
		System.out.println("Update command"); // testing
		int i = 0;
		try{
		while (i < numLEDs){
			//System.out.println("ChangeColor:" + this.ledChangers[i]); // testing
			this.ledChangers[i].changeColor(ia3d);
			i++;
		}
		}catch(Exception e){
			System.out.println("failed to change colors");
		}
	}
}
