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

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
//import javax.microedition.io.DatagramConnection;
import com.sun.spot.io.j2me.radiogram.*;
import com.sun.spot.peripheral.TimeoutException;

import JETTQ.JETTQManager;

/**
 * GramRadio class 
 * 4 States
 *  - Null
 *  - Just Sending
 *  - Just Receiving
 *  - Sending and Receiving
 * @author navien21
 */
public class GramRadio extends IRadio {

	protected int sendInterval,receiveInterval;
	protected sendThread sendThread;
	protected recieveThread receiveThread;
	/** port number */
	int portNumber;
	int spotHops;
	/** a list of devices which will receive the message */
	Vector messageReceivers;
	/** status of sending and receiving */
	protected boolean isSending,isReceiving;
	private Message sendMSG;
	
	/**
	 * Constructor of a gram radio connection
	 * @param pn port number used for the gram radio connection
	 */
	GramRadio(int pn) {
		JETTQManager.decho("Creating Gram Radio Connection");
		this.portNumber = pn;
		this.messageReceivers = new Vector();
		this.isSending = false;
		this.isReceiving = false;
	}
	
	/**
	 * Broadcast a single message.  This method does not try to re-send the message.
	 * @param msg Message object to broadcast
	 */
	void sendMessageOnce(Message msg) {
		// We create a DatagramConnection
		JETTQManager.decho("Initiating Send Once");
        DatagramConnection dgConnection = null;
        Datagram dg = null;
        try {
            // The Connection is a broadcast so we specify it in the creation string
            dgConnection = (DatagramConnection) Connector.open("radiogram://" + msg.getTo() + ":" + this.portNumber);
            // Then, we ask for a datagram with the maximum size allowed
            dg = dgConnection.newDatagram(dgConnection.getMaximumLength());
        } catch (IOException ex) {
            JETTQManager.decho("Could not open radiogram broadcast connection");
            ex.printStackTrace();
            return;
        }
        try {
            // We send the message (UTF encoded)
            dg.reset();
            dg.writeUTF(msg.getTo());
            dg.writeInt(msg.getType());
            dg.writeUTF(msg.getContents());
            dgConnection.send(dg);
            JETTQManager.decho("Message Sent");
            dgConnection.close();
        } catch (IOException ex) {
        	JETTQManager.decho("Failed to Send Message once: ");
            ex.printStackTrace();
            return;
        }
	}
	
	/**
	 * Function sends a Datagram message to another device
	 * @param msg	message to be sent
	 * @param interval	time interval of sending a message
	 * @param hops **IGNORED** Not implemented in this file release.
	 */
	void sendMessage(Message msg, int interval, int hops) {
		JETTQManager.decho("Beginning to Send Message every " + interval + " milliseconds");
		JETTQManager.decho("Send Connection = radiogram://" + msg.getTo() + ":" +  this.portNumber);
		
		this.sendMSG = msg;
		this.sendInterval = interval;
		
        this.sendThread = new sendThread(sendMSG, sendInterval, this.portNumber);
        sendThread.start();
        this.isSending = true;
	}
	
	/**
	 * Function retrieves a Datagram message from other devices
	 * @param interval	time interval of receiving a message
	 */
	void recieveMessage(int interval) {
		JETTQManager.decho("Beginning to Receive Message every " + interval + " milliseconds");
		JETTQManager.decho("Receive Connection = radiogram://:" +  this.portNumber);
	
			try {
				// establish a new Datagram Connection for receiving
				this.receiveInterval = interval;
				this.receiveThread = new recieveThread(interval, portNumber);
				this.receiveThread.start();
				
			} catch (Exception e) {
				JETTQManager.decho("Failed to Receive Datagram and Create Message");
			}
	}
	
		
	/** Accessor Methods **/
	
	/**
	 * Function stops sending the message
	 */
	void stopSend() {
		this.sendThread.interrupt();
		this.isSending = false;
	}
	
	/**
	 * Function stops receiving the message
	 */
	void stopReceive() {
		this.receiveThread.interrupt();
		this.isReceiving = false;
	}
	
	/**
	 * Check if the device is still sending the message
	 * @return	true if the device is sending a message; otherwise false
	 */
	boolean isSending() {
		return this.isSending;
	}
	
	/**
	 * Check if the device is still receiving the message
	 * @return	true if the device is receiving a message; otherwise false
	 */
	boolean isReceiving() {
		return this.isReceiving;
	}
	
	/**
	 * Function sets the time interval for retrieving the message
	 * @param interval new time interval for retrieving the message
	 */
	void setRetrieveInterval(int interval) {
		if(this.isReceiving()) {
			this.receiveThread.setInterval(interval);
		}			
	}
	
	/** Observation Methods**/
	/**
	 * Register a Radiogram observer
	 * @param irgo a RadiogramObserver to be registered
	 */
	void registerObserver(IRadiogramObserver irgo) {
		this.messageReceivers.addElement(irgo);
		JETTQManager.decho("Observer Registered");
	}
	
	/**
	 * Remove a Radiogram observer 
	 * @param irgo	a RadiogramObserver to be remove
	 */
	void removeObserver(IRadiogramObserver irgo) {
		this.messageReceivers.removeElement(irgo);
		JETTQManager.decho("Observer Removed");
	}
	
	/**
	 * Notify the Observer
	 * @param msg  new message
	 */
	void notifyObservers(Message msg) {
		JETTQManager.decho("Notifying Observers");
		Enumeration obs = this.messageReceivers.elements();
		while(obs.hasMoreElements()){
			((IRadiogramObserver)obs.nextElement()).update(msg);
		}
	}

	/**
	 * Check if the destination IEEE Address is valid
	 * Is not implemented in this release
	 * @return	true if a valid IEEE address is specified; otherwise false
	 */
	boolean isGuaranteed() {
		return false;
	}
	
	/**
	 * This thread class handles the sending of messages.
	 * @author JETTQ
	 */
	private class sendThread extends Thread {

		Message msg;
		int interval;
		int portNumber;
		private boolean running = true;

		/**
		 * Default constructor
		 * @param msg Message object to send
		 * @param interval Time in milliseconds to resend
		 * @param portNumber Port to send on
		 */
		sendThread(Message msg, int interval, int portNumber){
			this.msg = msg;
			this.interval = interval;
			this.portNumber = portNumber;
		}

		/**
		 * The run method sets up the connection and sends the message.
		 */
		public void run() {
            // We create a DatagramConnection
            DatagramConnection dgConnection = null;
            Datagram dg = null;
            try {
                // The Connection is a broadcast so we specify it in the creation string
                dgConnection = (DatagramConnection) Connector.open("radiogram://" + this.msg.getTo() + ":" + this.portNumber);
                // Then, we ask for a datagram with the maximum size allowed
                dg = dgConnection.newDatagram(dgConnection.getMaximumLength());
            } catch (IOException ex) {
                System.out.println("Could not open radiogram broadcast connection");
                ex.printStackTrace();
                return;
            }
            
            while(this.running){
                try {
                    // We send the message (UTF encoded)
                    dg.reset();
                    dg.writeUTF(this.msg.getTo());
                    dg.writeInt(this.msg.getType());
                    dg.writeUTF(this.msg.getContents());
                    dgConnection.send(dg);
                    System.out.println("Broadcast is going through");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                JETTQManager.pause(interval);
            }
        }
    
		/**
		 * Stops the sending
		 */
		public void interrupt(){
			JETTQManager.decho("Ending Sending");
			this.running = false;
		}
	}
	
	/**
	 * This thread class handles the receiving of messages
	 * @author JETTQ
	 */
	private class recieveThread extends Thread {
		int interval;
		int portnumber;
		
		/**
		 * Default constructor
		 * @param interval Time in milliseconds to check for a message again
		 * @param portnumber Port to check for messages
		 */
		public recieveThread(int interval, int portnumber){
			this.interval = interval;
			this.portnumber = portnumber;
		}
		
		public boolean running = true;

		/**
		 * The run method sets up the connection, checks for messages,
		 * and notifies any observers when a message is received.
		 */
		public void run () {
			RadiogramConnection recvConn = null;
			Datagram recvDG = null;
			
			try {
				recvConn = (RadiogramConnection) Connector.open("radiogram://:" +  this.portnumber);
				recvDG = recvConn.newDatagram(recvConn.getMaximumLength());
				
			} catch (Exception e) {
				JETTQManager.decho("Failed to Create Send Connection: " + e);
				return;
			}
			
			while (running){
				try {
					JETTQManager.decho("Checking for Message ...");
					recvDG.reset();
					recvConn.receive(recvDG);
					isReceiving = true;
					notifyObservers(new Message(recvDG.getAddress(),recvDG.readUTF(),recvDG.readInt(),recvDG.readUTF()));
					JETTQManager.pause(this.interval);
				} catch (TimeoutException e) {
					//e.printStackTrace();
					JETTQManager.decho("No Datagram Received");
				} catch (IOException e) {
					// ignore
					JETTQManager.decho("IOException when attempting to Receive message");
				}
				
			}
		
		}
		
		/**
		 * Stops checking for messages.
		 */
		public void interrupt(){
			JETTQManager.decho("Ending Receiving");
			this.running = false;
		}
		
		/**
		 * Sets the interval to check for messages.
		 * @param interval Time in milliseconds to re-check.
		 */
		public void setInterval(int interval){
			this.interval = interval;
		}
	}
}