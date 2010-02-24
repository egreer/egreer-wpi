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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Hashtable;

import com.sun.spot.peripheral.radio.IProprietaryRadio;
import com.sun.spot.peripheral.radio.IRadioPolicyManager;

import JETTQ.JETTQManager;

/**
 * Manager class for the SPOT's radio.
 * Used by JETTQManager.
 * @author JETTQ
 */
public class RadioManager {
	
	private final int MAX_PORT_NUMBER = 127;
	private final int MIN_PORT_NUMBER = 0;
	
	private final int MAX_TRANSMIT_POWER = 32;
	private final int MIN_TRANSMIT_POWER = -32;
	
	/** default time interval for receiving a message */
	private final static int DEFAULT_INTERVAL = 50;
	private final static int DEFAULT_HOPS = 0;
	private final static int DEFAULT_PORT = 101;
	
	private final static RadioManager rm = new RadioManager();
	private Hashtable radioConnections = new Hashtable();
	private static IRadioPolicyManager rpm = null;
	
	
	/**
	 * Default Constructor
	**/
	RadioManager() {}
	
	public static RadioManager configureInstance(IRadioPolicyManager irpm) {
		rpm = irpm;
		rpm.setChannelNumber(IProprietaryRadio.DEFAULT_CHANNEL);
		rpm.setPanId(IRadioPolicyManager.DEFAULT_PAN_ID);
		rpm.setOutputPower(IProprietaryRadio.DEFAULT_TRANSMIT_POWER);
		return rm;
	}
	
	/**
	 * Returns an instance of the RadioManager
	 * @return This RadioManager
	 */
	public static RadioManager getInstance() { return rm; }
	
	/**
	 * Check if the given port number is valid
	 * @param portNumber port number to be checked
	 * @return true if the port number is valid; otherwise false
	**/
	private boolean isValidPortNumber(int portNumber) {
		if((portNumber > 0) && (portNumber < 33)) {
			// Warn User about Reserved Area Conflict
			System.out.println("Warning: Reserved Port Requested!");
		}
		return ((portNumber <= MAX_PORT_NUMBER) && (portNumber >= MIN_PORT_NUMBER));
	}
	

	/**
	 * Checks to see if the given port is available
	 * @param portNumber Port to check
	 * @return true if the port is available, false otherwise
	 */
	public synchronized boolean portIsAvailable(int portNumber) {
		JETTQManager.decho("Checking port: " + portNumber);
		if (this.isValidPortNumber(portNumber)) {
			JETTQManager.decho("Valid Port Number");
			return ((IRadio)this.radioConnections.get("" + portNumber) == null);
		} else {
			JETTQManager.decho("Invalid Port Number");
			return false;
		}
	}
	
	/**
	 * Adds a radio connection to the list of connections
	 * @param ir IRadio to connect
	 * @param portNumber Port to connect on
	 */
	public synchronized void addRadioConnection(IRadio ir, int portNumber) {
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("Registering Connection on port: " + portNumber);
			this.radioConnections.put("" + portNumber, ir);
		} else {
			JETTQManager.decho("Port: " + portNumber + " in use!");
		}
	}
	
	/** STREAM RADIO 
	 * @throws IOException **/
	
	/**
	 * Gets the DataInputStream to steam to another SPOT
	 * @param portNumber Port to stream on
	 * @return DataInputStream to write to
	 */
	public DataInputStream getDataInputStream(int portNumber) {
		if(this.portIsAvailable(portNumber)) {
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof StreamRadio) {
				return ((StreamRadio)radio).getDataInputStream();
			} else {
				JETTQManager.decho("Radio is not configured for Streams");
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the DataOutputStream for a stream from another SPOT
	 * @param portNumber Port to stream on
	 * @return DataOutputStream to read from
	 */
	public DataOutputStream getDataOutputStream(int portNumber) {
		if(this.portIsAvailable(portNumber)){
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof StreamRadio) {
				return ((StreamRadio) radio).getDataOutputStream();
			} else {
				JETTQManager.decho("Radio is not configured for Streams");
				return null;
			}
		} else {
			return null;
		}
	}
	
	/** GRAM RADIO **/
	
	/**
	 * Sends a message without re-sending on the default port.
	 * @param msg Message object to send
	 */
	public void sendMessageOnce(Message msg) {this.sendMessageOnce(msg, DEFAULT_PORT);}
	
	/**
	 * Send a message without re-sending on the specified port
	 * @param msg Message object to send.
	 * @param portNumber Port to send on.
	 */
	public void sendMessageOnce(Message msg, int portNumber) {
		JETTQManager.decho("Send Message Once Started");
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("Port " + portNumber + " Available");
			GramRadio irad = new GramRadio(portNumber);
			irad.sendMessageOnce(msg);
		} else {
			JETTQManager.decho("Port " + portNumber + " in use");
			// Port in use
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			JETTQManager.decho("Retrieved Radio");
			if (radio instanceof GramRadio) {
				GramRadio gr = (GramRadio)radio;
				JETTQManager.decho("Casted Radio");
				// Check if not sending
				if (!gr.isSending()) {
					gr.sendMessageOnce(msg);
				} else {
					JETTQManager.decho("RadioGram on Port:" + portNumber + " is already sending!");
				}
			} else {
				JETTQManager.decho("Port is not configured for Grams");
			}
		}
	}
	
	/**
	 * Broadcasts a message with a default re-send interval on the default port.
	 * @param msg Message object to send.
	 */
	public void sendMessage(Message msg) {this.sendMessage(msg, DEFAULT_INTERVAL, DEFAULT_PORT, DEFAULT_HOPS);}
	
	/**
	 * Broadcasts a message with the specified re-send interval on the default port.
	 * @param msg Message object to send
	 * @param interval Time in milliseconds to re-send.
	 */
	public void sendMessage(Message msg, int interval) {this.sendMessage(msg, interval, DEFAULT_PORT, DEFAULT_HOPS);}
	
	/**
	 * Sends a message on the specified port with the specified re-send interval
	 * @param msg Message object to send
	 * @param interval Time in milliseconds to re-send
	 * @param portNumber Port to send on
	 */
	public void sendMessage(Message msg, int interval, int portNumber) {this.sendMessage(msg, interval, portNumber, DEFAULT_HOPS);}
	
	/**
	 * Broadcasts a message on the GramRadio
	 * @param msg Message object to send
	 * @param interval Time in milliseconds to re-send
	 * @param portNumber Port to broadcast on
	 * @param hops Not implemented in this file release.
	 */
	public void sendMessage(Message msg, int interval, int portNumber, int hops) {
		JETTQManager.decho("Send Message Started");
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("Port " + portNumber + " Available");
			GramRadio irad = new GramRadio(portNumber);
			this.radioConnections.put(""+portNumber, irad);
			irad.sendMessage(msg, interval, hops);
			JETTQManager.decho("New GramRadio Created and Sending");
		} else {
			// Port in use
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof GramRadio) {
				GramRadio gr = (GramRadio)radio;
				// Check if not sending
				if (!gr.isSending()) {
					gr.sendMessage(msg, interval, hops);
				} else {
					JETTQManager.decho("RadioGram on Port:" + portNumber + " is already sending!");
				}
			} else {
				JETTQManager.decho("Port is not configured for Grams");
			}
		}
	}
	
	/**
	 * Registers a listener to the GramRadio on a given port
	 * @param irgo IRadiogramObserver to register
	 * @param portNumber Port to listen on
	 */
	public void registerRadiogramListener(IRadiogramObserver irgo, int portNumber) {
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("Configure stealth listener");
			GramRadio irad = new GramRadio(portNumber);
			irad.recieveMessage(DEFAULT_INTERVAL);
			irad.registerObserver(irgo);
			this.radioConnections.put(""+portNumber, irad);
		} else {
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof GramRadio) {
				JETTQManager.decho("Adding Observer");
				((GramRadio)radio).registerObserver(irgo);
			} else {
				JETTQManager.decho("Port not Configured for Gram Communication");
			}
		}
	}
	
	/**
	 * Removes the given listener from the given port
	 * @param irgo IRadiogramObserver to remove
	 * @param portNumber Port to stop listening on
	 */
	public void removeRadiogramObserver(IRadiogramObserver irgo, int portNumber) {
		if(this.isGramReceivingOn(portNumber)){		
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			JETTQManager.decho("Removing Observer");
			((GramRadio)radio).removeObserver(irgo);
		}
	}
	
	/**
	 * Sets the message retrieval interval
	 * @param interval Time in milliseconds to retrieve messages
	 * @param portNumber Port to retrieve on
	 */
	public void setRetrieveInterval(int interval, int portNumber) {
		if(!this.portIsAvailable(portNumber)) {
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof GramRadio) {
				radio.setRetrieveInterval(interval);
			}
		}
	}
	
	/**
	 * Checks if the GramRadio is sending on the given port
	 * @param portNumber Port to check
	 * @return true if the GramRadio is sending on the port, false otherwise
	 */
	public boolean isGramSendingOn(int portNumber)
	{
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("port is not in use");
			return false;
		} else {
			// Port in use
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof GramRadio) {
				return ((GramRadio) radio).isSending();
			} else {
				JETTQManager.decho("Port not Configured for Gram Communication");
				return false;
			}
		}
	}
	
	/**
	 * Checks if the GramRadio is receiving on the given port
	 * @param portNumber Port to check
	 * @return true if the GramRadio is receiving on the port, false otherwise
	 */
	public boolean isGramReceivingOn(int portNumber)
	{
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("port is not in use");
			return false;
		} else {
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof GramRadio) {
				return ((GramRadio) radio).isReceiving();
			}
			else {
				JETTQManager.decho("Port not Configured for Gram Communication");
				return false;
			}
		}
	}
	
	/**
	 * Tells the GramRadio to stop sending on the given port
	 * @param portNumber Port to stop sending on
	 */
	public void stopGramSending(int portNumber)
	{
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("port is not in use");
			return;
		} else {
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof GramRadio) {
				((GramRadio) radio).stopSend();
				return;
			}

			else {
				JETTQManager.decho("Port not Configured for Gram Communication");
				return;
			}
		}
	}
	
	/**
	 * Tells the GramRadio to stop receiving on a given port
	 * @param portNumber Port to stop receiving on
	 */
	public void stopGramReceiving(int portNumber)
	{
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("port is not in use");
			return;
		} else {
			IRadio radio = (IRadio)this.radioConnections.get("" + portNumber);
			if (radio instanceof GramRadio) {
				((GramRadio) radio).stopReceive();
				return;
			}

			else {
				JETTQManager.decho("Port not Configured for Gram Communication");
				return;
			}
		}
	}
	
	/**
	 * Closes the connection on the specified port.
	 * @param portNumber Port to close
	 * @return true if a connection is closed, false otherwise
	 */
	public boolean resetPort(int portNumber) {
		if(this.portIsAvailable(portNumber)) {
			JETTQManager.decho("port is not in use");
			return false;
		} else {
			this.radioConnections.remove("" + portNumber);
			JETTQManager.decho("Clearing IRadio on port: " + portNumber);
			return true;
		}
	}
	
	/**
	 * Function sets the output power of the radio to the given value
	 * @param pow The new output power level
	**/
	public void setOutputPower(int pow) {
		if((pow < this.MAX_TRANSMIT_POWER) && (pow > this.MIN_TRANSMIT_POWER)) {
			rpm.setOutputPower(pow);
		} else {
			JETTQManager.decho("Invalid Output Power Setting, reverting to default");
			rpm.setOutputPower(IProprietaryRadio.DEFAULT_TRANSMIT_POWER);
		}
	}
	
	/**
	 * Function return the current output power of the radio
	 * @return int representing the output power
	**/
	public int getOutputPower() {
		return rpm.getOutputPower();
	}
	
}
