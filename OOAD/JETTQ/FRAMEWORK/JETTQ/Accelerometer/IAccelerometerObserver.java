/*
 * Created on Oct 10, 2008
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
/**
 * 

 * @date 09-28-08
 * CS4233-Team Project
 *
 */
import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;

/**
 * Observer interface for the SPOT's accelerometer
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 10-10-08
 */
public interface IAccelerometerObserver {
	
	/** Update is called on any registered observers when the AccelerometerManager 
	 * polls the accelerometer.
	 * 
	 * @param ia3d		Passes the updated IAccerometer3D to the observer 
	 */
	public void update(IAccelerometer3D ia3d);
	
}
