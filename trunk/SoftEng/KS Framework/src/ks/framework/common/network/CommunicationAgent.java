/*
 * Protocol communication.
 */
package ks.framework.common.network;

import ks.framework.interfaces.ICommunicator2;

import java.io.*;

/**
 * Maintains Input and Output streams, and knows how 
 * to writeObject to the Output and readObject from Input, on 
 * demand.
 * <p>
 * @author George Heineman (heineman@cs.wpi.edu)
 */
public class CommunicationAgent implements ICommunicator2 {
	/** input. */
	InputStream in;
	
	/** output. */
	OutputStream out;
	
	/** Semaphore objects for reading and writing. */
	Object readSemaphore = new Object();
	Object writeSemaphore = new Object();
	
	/**
	 * Agent to manage communication where writing an object
	 * to OutputStream while reading from InputStream.
	 * 
	 * @param in
	 * @param out
	 */
	public CommunicationAgent (InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}
	
	/**
	 * Shutdown communication agent.
	 */
	public void close () {
		try {
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Blocks for an object to be read. 
	 * 
	 * Note that the very fact of opening an ObjectInputStream
	 * actually forces a read of the stream header; this is
	 * why we need to cleanly encapsulate it here.
	 * 
	 * @return  Object read or null if an exception occurred.
	 */
	public Object readObject () {
		if (in == null) return null;
		
		// make sure that only one thread is reading at a time.
		synchronized (readSemaphore) {
			ObjectInputStream ois = null;
			Object retValue = null;
			try {
				ois = new ObjectInputStream (in);
				retValue = ois.readObject();
			} catch (EOFException ee) {
				System.err.println ("Client lost connection with server.");
			} catch (IOException ioe) {
				System.err.println ("Client lost connection with server.");
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}

			// close this one down.
			try {
				if (retValue == null) {
					if (ois != null) {
						ois.close();
					}
				}
			} catch (IOException ioe) {
				// nothing to say...
			}
			
			return retValue;
		}
	}
	
	/**
	 * Writes an object to the OutputStream.
	 * 
	 * Note that the very fact of opening an ObjectOutputStream
	 * actually forces a write of a stream header; this is
	 * why we need to cleanly encapsulate it here.
	 * 
	 * @param o     Object to be written.
	 */
	public boolean writeObject (Object o) {
		if (out == null) return false;
		
		// make sure that only one thread is writing at a time.
		synchronized (writeSemaphore) {
			ObjectOutputStream oos = null;
			boolean retValue = true;
			try {
				oos = new ObjectOutputStream (out);
				oos.writeObject(o);
				oos.flush();
			} catch (IOException ioe) {
				// nothing to do!
				System.err.println ("Agent lost connection:" + ioe.getMessage());
				retValue = false;
			}
			
			try {
				if (!retValue) {
					if (oos != null) {
						oos.close();
					}
				}
			} catch (IOException e) {
				// nothing to do.
			}
			return retValue;
		}
	}
}
