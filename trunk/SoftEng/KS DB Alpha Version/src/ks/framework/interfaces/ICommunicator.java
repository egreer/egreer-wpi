package ks.framework.interfaces;

/**
 * Provides basic communication capability.
 * <p>
 * Entity provides capability to write an object that will 
 * be read by some other entity.
 * <p>
 * Entity provides capability to read an object and return it.
 * <p>
 * The writing and reading take place over networked streams
 * and so there will be issues regarding reads and writes.
 * The order in which these read/writes take place is incredibly
 * important, but is outside of the control of this interface.
 * <p>
 * @author George Heineman (heineman@cs.wpi.edu)
 */
public interface ICommunicator {
	/**
	 * Writes an object to be retrieved by a remote entity.
	 *
	 * @param o    Object to be written.
	 */
	public boolean writeObject (Object o);
	
	/**
	 * Blocks for an object to be read from remote entity. 
	 * 
	 * @return  Object read or null if an exception occurred.
	 */
	public Object readObject ();	
}
