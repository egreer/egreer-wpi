package streamRadioTest;
import java.io.DataInputStream;

import javax.microedition.midlet.MIDletStateChangeException;

import JETTQ.JETTQManager;
import JETTQ.Radio.RadioManager;
import JETTQ.Radio.StreamRadio;
import JETTQ.Switch.SwitchManager;

import com.sun.spot.sensorboard.peripheral.ISwitch;

public class DemoMain extends javax.microedition.midlet.MIDlet {
	JETTQManager jetman = JETTQManager.getInstance();
	SwitchManager sm = jetman.getSwitchManager();
	RadioManager rm = jetman.getRadioManager();
	
	String currIEEEAddr = JETTQManager.getIEEEAddress();
	String destIEEEAddr;
	DataInputStream dis;
	
	ISwitch switch1;
	boolean switch1pressed = false;
	
	final String Spot1IEEE = "";	// need IEEE address for both Spots
	final String Spot2IEEE = "";
	
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
		
		dis = rm.getDataInputStream(100);	
		
		try {
			System.out.println(dis.readByte());
		} catch (Exception e) {
			JETTQManager.decho("Failed to sleep ...");
		}
		
		while(true) {
			//Something to demo board
		}
	}
	
	
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		
	}
	
	/**
	 * Called when the application is paused.
	 * We choose not to do anything.
	 */
	protected void pauseApp() {
	}
}
