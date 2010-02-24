package radioColorChanger;
/**
 * 
 * @author JETTQ [jettq@wpi.edu]
 * @date 09-28-08
 * CS4233-Team Project
 *
 */

import javax.microedition.midlet.MIDletStateChangeException;

import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import com.sun.spot.sensorboard.peripheral.ISwitch;
import JETTQ.*;
import JETTQ.Accelerometer.*;
import JETTQ.Radio.*;
import JETTQ.Switch.*;

/**
 * Main class for the Homework 5 demo.  Sets up various managers
 * and registers an observer to the AccelerometerManager.
 * 
 * @author Eric Greer
 *
 * Note Switches in emulator have inverted toggle (checked is not pressed)
 * switch 1(left): toggle sending
 * switch 2 (right): toggle display of the other spots. 
 * 
 */
public class RadioColorChangerMain extends javax.microedition.midlet.MIDlet implements IAccelerometerObserver, IRadiogramObserver, ISwitchObserver {
	
	JETTQManager jetman = JETTQManager.getInstance();
	SwitchManager sm = jetman.getSwitchManager();
	RadioManager rm = jetman.getRadioManager();
	AccelerometerManager am = jetman.getAccelerometerManager();
	
	int Z = 0;
	int X = 1;
	int Y = 2;

	ISwitch switch1;
	ISwitch switch2;
	boolean switch1pressed = false;
	boolean switch2pressed = false;
	
	int port = 101; //Port to listen/broadcast on 
	
	protected void startApp() throws MIDletStateChangeException {
		JETTQManager.turnDebugOutputOff();
		//Register Switches
		switch1 = sm.getSwitch(0);
		switch2 = sm.getSwitch(1);
		jetman.registerSwitchObserver(this);
		
		//Register Accelerometer
		jetman.registerAccelerometerObserver(this);
		am.setNotificationInterval(10);
				
		//Turn LEDs on
		jetman.setLEDsRGBColor(255, 0, 0);
		jetman.turnLEDsOn();
		
		//Register Radio
		jetman.registerRadiogramListener(this, port);
		
		while(true) {
			//Utils.sleep(2000); 
		}
	}
	
	/**
	 * Called when the application is stopped (if the SPOT is turned off or reset).
	 * Turns off all LEDs.
	 */
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		rm.removeRadiogramObserver(this, port);
		am.removeObserver(this);
		sm.removeObserver(this);
		jetman.turnLEDsOff();
	}
	
	/**
	 * Called when the application is paused.
	 * We choose not to do anything.
	 */
	protected void pauseApp() {
	}
	
	public int changeColor(int axis,IAccelerometer3D acc) {
		try {
			switch(axis) {
				case 1:
					return acclerometerToColor(acc.getTiltX());
				case 2:
					return acclerometerToColor(acc.getTiltY());
				case 0:
				default:
					return acclerometerToColor(acc.getTiltZ());
			}	
		} catch (Exception e) {
			JETTQManager.decho("Error Changing Colors" + e);
			return 0;
		}
	}

	public void broadcastMessage(IAccelerometer3D ia3d){
		try{
			String accelData = "" + ia3d.getTiltZ() + "," + ia3d.getTiltX() + "," + ia3d.getTiltY();
			jetman.broadcast(accelData, port);
			// Implement a kill or sleep method
			//jetman.resetPort(port); <-- Not need ??
		}catch(Exception e){
			JETTQManager.decho("Error Broadcasting Message Error" + e);
		}
		
		
	}

	/**
	 * Update for the acceleration
	 */		
	public void update(IAccelerometer3D ia3d) {
		if (!switch2pressed){
			jetman.setLEDsRGBColor(this.changeColor(X,ia3d),this.changeColor(Y,ia3d),this.changeColor(Z,ia3d));
		}
		
		if(switch1pressed){
			JETTQManager.decho("Broadcasting Accelerometer Data");
			this.broadcastMessage(ia3d);
		}
	}

	/**
	 * Update for the Radio
	 */
	public void update(Message msg){
		//Needs Some Checking for if switch is toggled
		if(switch2pressed){
			String str = msg.getContents();
			double acceleration;
			int x,y,z;
			acceleration = Double.parseDouble(str.substring(0, str.indexOf(',')));
			z = acclerometerToColor(acceleration);
			str = str.substring(str.indexOf(',')+1, str.length());
			acceleration = Double.parseDouble(str.substring(0, str.indexOf(',')));
			x = acclerometerToColor(acceleration);
			str = str.substring(str.indexOf(',')+1, str.length());
			acceleration = Double.parseDouble(str);
			y = acclerometerToColor(acceleration);
			
			jetman.setLEDsRGBColor(x, y, z); 
		}
	}

	/**
	 * Update for the switch
	 */
	public void update(ISwitch updatedSwitch) throws Exception {
		JETTQManager.decho("A Switch is updated: " + updatedSwitch.toString());
		if (updatedSwitch.equals(switch1)){
			switch1pressed = sm.isSwitchPressed(updatedSwitch);
			JETTQManager.decho("Switch1 is " + ((switch1pressed) ? "Pressed" : "Not Pressed") );
			
		}else if (updatedSwitch.equals(switch2)){
			switch2pressed = sm.isSwitchPressed(updatedSwitch);
			JETTQManager.decho("Switch2 is " + ((switch2pressed) ? "Pressed" : "Not Pressed") );
		}	
	}
	
	public int acclerometerToColor(double tilt){
		tilt = Math.toDegrees(tilt);
		if(tilt < 0){
			return 0;
		}else if (tilt > 0){
			return 255;
		}	
		
		return 128;
	}

}
