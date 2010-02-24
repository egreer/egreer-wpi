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

import JETTQ.JETTQManager;
import JETTQ.Radio.IRadiogramObserver;
import JETTQ.Radio.Message;

/**
 * Tests the GramRadio
 * @author JETTQ
 *
 */
public class MyRadioGramListener implements IRadiogramObserver {
	public boolean status = false;
	
	public void update(Message msg) {
		// TODO Auto-generated method stub
		JETTQManager.turnDebugOutputOn();
		JETTQManager.decho("Message Received!");
		JETTQManager.decho(msg.toString());
		//JETTQManager.turnDebugOutputOff();
		status = true;
	}

}
