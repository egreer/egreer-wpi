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
package JETTQ.Radio;

/**
 * Abstract class for using the SPOT's radio.
 * @author JETTQ
 *
 */
abstract class IRadio {

	abstract void setRetrieveInterval(int interval);
	
	//abstract void destroy();
	
	/**
	 * Returns whether the radio transmission is guaranteed or not.
	 * The transmission is guaranteed if it is a StreamRadio or a GramRadio that is not broadcasting
	 * @return guaranteed true if ACKing
	 */
	abstract boolean isGuaranteed();

}
