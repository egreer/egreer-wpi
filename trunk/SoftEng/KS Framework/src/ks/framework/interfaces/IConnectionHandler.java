package ks.framework.interfaces;

/**
 * Provides callback capability when a connection request (from  
 * IConnection) has succeeded. 
 * 
 * Also responsible for dealing with circumstances when the remote
 * connection has been disconnected.
 * 
 * @author George Heineman
 */
public interface IConnectionHandler {
	
	/** Respond to connections and disconnections. */
	void connected (boolean status);
}
