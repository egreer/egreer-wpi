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
import java.io.IOException;

import javax.microedition.io.Connector;
import JETTQ.JETTQManager;
import com.sun.spot.io.j2me.radiostream.RadiostreamConnection;
import com.sun.spot.peripheral.NoRouteException;
import com.sun.spot.peripheral.TimeoutException;

/**
 * StreamRadio class handles the data stream through the Radio Stream Connection
 * @author JETTQ
 */
public class StreamRadio extends IRadio {
	
	private final int DEFAULT_TIMEOUT = 2000;
	
	private String IEEEaddress;
	private int portNumber;
	private RadiostreamConnection conn;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private boolean connected;
	
	/**
	 * Default constructor. Starts a RadioStreamConnection between this SPOT
	 * and the one pointed to by the ieeeAddress
	 * @param ieeeAddress String address of the SPOT to stream  to
	 * @param portNum representing the port to connect on
	 * @throws Exception if cannot establish a RadiostreamConnection
	**/
	public StreamRadio(String ieeeAddress, int portNum) {
		JETTQManager.decho("Creating Stream Object");
		this.IEEEaddress = ieeeAddress;
		this.portNumber = portNum;
		try {
			this.conn = (RadiostreamConnection)Connector.open("radiostream://"+this.IEEEaddress+":"+this.portNumber);
			this.conn.setTimeout(DEFAULT_TIMEOUT);
			JETTQManager.decho("RadioStream: " + (this.conn == null));
		} catch (Exception e) {
			JETTQManager.decho("Unable to open RadioStreamConnection");
		}
		
		String tmp = null;

		try {
			dis = (DataInputStream)conn.openDataInputStream();
			dos = (DataOutputStream)conn.openDataOutputStream();
		} catch (IOException e) {
			// e.printStackTrace();
		}
		
		while(!connected) {
			try {
				dos.writeUTF("GO");
				dos.flush();
			} catch (IOException e1) {
				//e1.printStackTrace();
				System.out.println("Exception on writeUTF");
			}
			
			try {
				tmp = dis.readUTF();
			} catch (TimeoutException e) {
				System.out.println("Timeout... other end is not responding");
			} catch (IOException e1) {
				//e1.printStackTrace();
				System.out.println("Exception on readUTF");
			}
			
			if(tmp != null) {
				if(tmp.equalsIgnoreCase("GO")) {
					connected = true;
					System.out.println("Connected to " + this.IEEEaddress);
				} else {
					connected = false;
					System.out.println("NOT connected to " + this.IEEEaddress);
				}
			}
		}
		
	}
	
	/**
	 * Function retrieves the Data Input Stream for the Stream Radio
	 * @return DataInputStream 
	**/
	public DataInputStream getDataInputStream() {
		JETTQManager.decho("Retrieving Data Input Stream");
		try {
			return dis;
		} catch (Exception e) {
			JETTQManager.decho("Unable to open Data Input Stream");
			return null;
		}
	}
	
	/**
	 * Function retrieves the Data Output Stream for the Stream Radio
	 * @return DataOutputStream 
	**/
	public DataOutputStream getDataOutputStream() {
		JETTQManager.decho("Retrieving Data Output Stream");
		try {
			return dos;
		} catch (Exception e) {
			JETTQManager.decho("Unable to open Data Input Stream");
			return null;
		}
	}
	
	/**
	 * Close a RadiostreamConnection
	 */
	public void closeConnection() {
		if(connected == false) {
			return;
		}
		connected = false;
		try {
			dos.close();
			dis.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function sends a stream to the Spot on a RadiostreamConnection
	 * @param message a message to be sent
	 * @param value time interval for sending a message
	 * @throws IOException	if an error in the stream
	 * @throws NoRouteException
	 */
    public void send(String message, int value) throws IOException, NoRouteException {
    	dos.writeUTF(message);
    }
    
    /**
     * Function retrieves a stream from another Spot
     * @return the received stream
     */
    public String receive() {
    	String recv = null;
    	try {
			recv = dis.readUTF();
		} catch (IOException e) {
			//e.printStackTrace();
			recv = "nothing received...";
		}
		return recv;
    }
    
    /**
     * This thread class handles the sending of messages
     */
    public void startSendingThread() {
        Runnable r = new Runnable(){
            public void run() {
            	conn.setTimeout(500);
        		while(connected) {
        			try {
						send("Ya!", (int)System.currentTimeMillis());
					} catch (NoRouteException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
        			try {
        				Thread.sleep(100);
        			} catch (InterruptedException e) {
        				e.printStackTrace();
        			}
        		}
        	};
        };
        (new Thread(r)).start();
    }
	
	/**
	 * Function sets the time interval for receiving messages
	 */
	void setRetrieveInterval(int interval) {
		// Not implemented in this class
	}

	/**
	 * Check if the destination IEEE Address is valid
	 * @return	true if the given IEEE address is valid; otherwise false
	 */
	boolean isGuaranteed() {
		return (this.IEEEaddress != "");
	}

}
