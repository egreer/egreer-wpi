package ks.framework.interfaces;

/**
 * Provides capability to initiate a connection with a remote service
 * hosted at <hostname@port> with credentials (userName, password).
 * 
 * Note that password is never PLAINTEXT but is hashed according to SHA-1
 * algorithm
 * 
 * @author George Heineman
 */
public interface IConnection {
	
	/** Connect to server. */
	boolean connect (String hostname, int port, String userName, String password);
	
	/** Disconnect from server. */
	boolean disconnect ();
}
