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
package radioTest;

import javax.microedition.midlet.MIDletStateChangeException;

import com.sun.spot.sensorboard.peripheral.ISwitch;
import JETTQ.*;
import JETTQ.Radio.Message;
import JETTQ.Radio.RadioManager;
import JETTQ.Switch.ISwitchObserver;
import JETTQ.Switch.SwitchManager;

/**
 * Main class for the Homework 5 demo.  Sets up various managers
 * and registers an observer to the AccelerometerManager.
 * 
 * @author JETTQ [jettq@wpi.edu]
 */
public class RadioTestMain extends javax.microedition.midlet.MIDlet implements ISwitchObserver{
	
	JETTQManager jetman = JETTQManager.getInstance();
	SwitchManager sm = jetman.getSwitchManager();
	RadioManager rm = jetman.getRadioManager();
	
	ISwitch switch1;
	boolean switch1pressed = false;
	Message msg = new Message("", 1, "Message Body");
	
	
	protected void startApp() throws MIDletStateChangeException {
		switch1 = sm.getSwitch(0);
		MyRadioGramListener my = new MyRadioGramListener();
		jetman.registerRadiogramListener(my, 111);
		jetman.registerSwitchObserver(this);
		
		jetman.setLEDsRGBColor(0, 255, 255);
		
		
		JETTQManager.decho(msg.toString());
		
		try {
			JETTQManager.pause(3000);
		} catch (Exception e) {
			JETTQManager.decho("Failed to sleep ...");
		}
		
		rm.sendMessage(msg, 500, 111);
	
		JETTQManager.decho("Is Sending: " + rm.isGramSendingOn(111));
		JETTQManager.decho("Is Receiving: " + rm.isGramReceivingOn(111));
		
		
		
		while(true) {
		
			if(my.status) {
				jetman.setLEDsRGBColor(0, 255, 0);
				my.status = false;
			} else {
				jetman.setLEDsRGBColor(255, 0, 0);
			}
			JETTQManager.pause(500);
		}
	}
	
	/**
	 * Called before the application exits.
	 * We choose not to do anything.
	 */
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		
	}
	
	/**
	 * Called when the application is paused.
	 * We choose not to do anything.
	 */
	protected void pauseApp() {
	}

	public void update(ISwitch updatedSwitch) throws Exception {
		JETTQManager.decho("A Switch is updated: " + updatedSwitch.toString());
		if (updatedSwitch.equals(switch1)){
			switch1pressed = sm.isSwitchPressed(updatedSwitch);
			if(switch1pressed){
				rm.stopGramSending(111);
			}else{
				rm.sendMessage(msg, 500, 111);
			}
			
			JETTQManager.decho("Switch1 is " + ((switch1pressed) ? "Pressed" : "Not Pressed") );
			
		}	
	}
	
}
