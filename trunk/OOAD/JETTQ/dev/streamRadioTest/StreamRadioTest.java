package streamRadioTest;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.midlet.MIDletStateChangeException;

import JETTQ.JETTQManager;
import JETTQ.LED.LEDManager;
import JETTQ.Radio.RadioManager;
import JETTQ.Radio.StreamRadio;
import JETTQ.Switch.SwitchManager;

import com.sun.spot.sensorboard.peripheral.ISwitch;

public class StreamRadioTest extends javax.microedition.midlet.MIDlet {
	JETTQManager jetman = JETTQManager.getInstance();
	SwitchManager sm = jetman.getSwitchManager();
	RadioManager rm = jetman.getRadioManager();
	LEDManager lm = jetman.getLEDManager();
	String currIEEEAddr = JETTQManager.getIEEEAddress();
	String destIEEEAddr;
	DataInputStream dis;
	DataOutputStream dos;
	
	ISwitch switch1;
	boolean switch1pressed = false;
	
	final String Spot1IEEE = "82d7.a83c.0000.1001";	// need IEEE address for both Spots
	final String Spot2IEEE = "82d7.a83c.0000.1002";
	
	protected void startApp() throws MIDletStateChangeException {
		if (currIEEEAddr.equals(Spot1IEEE)) {
			destIEEEAddr = Spot2IEEE;
		} else {
			destIEEEAddr = Spot1IEEE;
		}
		StreamRadio sr = new StreamRadio(destIEEEAddr, 100);
		rm.addRadioConnection(sr, 100);	// add the radiostream connection
		
		switch1 = sm.getSwitch(0);
		// jetman.registerSwitchObserver((ISwitchObserver) this);
		
		
		while(true) {
			int number = 0;
			//Something to demo board
			number = Integer.parseInt(sr.receive());
			
			try {
				lm.displayNumberAsBinary(number);
			} catch (Exception e) {
				JETTQManager.decho("Error Displaying Number to LEDs");
			}
			try {
				sr.send("" + (number + 1), number);
			} catch (IOException e) {
				JETTQManager.decho("IOExpection when writing Int from stream");
			}
			
		}
	}
	
	/**
	 * Called when the application is stopped (if the SPOT is turned off or reset).
	 */
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		
	}
	
	/**
	 * Called when the application is paused.
	 * We choose not to do anything.
	 */
	protected void pauseApp() {
	}
}
