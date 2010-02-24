package colorChanger;
/**
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 * CS4233-Team Project
 *
 */


import javax.microedition.midlet.MIDletStateChangeException;

import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import JETTQ.*;
import JETTQ.Accelerometer.AccelerometerManager;
import JETTQ.Accelerometer.IAccelerometerObserver;

/**
 * Main class for the Homework 5 demo.  Sets up various managers
 * and registers an observer to the AccelerometerManager.
 * 
 * @author Eric Greer
 * @author Tyler Flaherty
 *
 */
public class DemoMain extends javax.microedition.midlet.MIDlet implements IAccelerometerObserver {
	
	JETTQManager jetman = JETTQManager.getInstance();
	AccelerometerManager am = jetman.getAccelerometerManager();
	
	int Z = 0;
	int X = 1;
	int Y = 2;
	
	protected void startApp() throws MIDletStateChangeException {
		jetman.registerAccelerometerObserver(this);
		am.setNotificationInterval(25);
		jetman.setLEDsRGBColor(255, 0, 0);
		jetman.turnLEDsOn();
		
		while(true) {}
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
	
	public int changeGolor(int axis,IAccelerometer3D acc) {
		try {
			switch(axis) {
				case 1:
					return (int) (((Math.toDegrees(acc.getTiltX())) + 90) / .706);
				case 2:
					return (int) (((Math.toDegrees(acc.getTiltY())) + 90) / .706);
				case 0:
				default:
					return (int) (((Math.toDegrees(acc.getTiltZ())) + 90) / .706);
			}	
		} catch (Exception e) {
			return 0;
		}
	}

	public void update(IAccelerometer3D ia3d) {
		// TODO Auto-generated method stub
		jetman.setLEDsRGBColor(this.changeGolor(X,ia3d),this.changeGolor(Y,ia3d),this.changeGolor(Z,ia3d));
	}

}
