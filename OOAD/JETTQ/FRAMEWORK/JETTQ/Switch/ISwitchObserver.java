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
package JETTQ.Switch;

import com.sun.spot.sensorboard.peripheral.ISwitch;

/**
 * Interface for a switch observer
 * @author JETTQ [jettq@wpi.edu]
 *
 */
public interface ISwitchObserver {

	/** Update is called on any registered observers when the SwitchManager registers a 
	 * change on the observed switch. 
	 * 
	 * @param updatedSwitch	The ISwitch object that has changed
	 * @throws Exception If there are any errors updating the observer
	 */
	public void update(ISwitch updatedSwitch) throws Exception;
}
