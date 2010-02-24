package ks.framework.interfaces;

/**
 * Extends communication to grant ability to sever a connection.
 * <p>
 */
public interface ICommunicator2 extends ICommunicator {

	/**
	 * close connection
	 */
	public void close(); 
}
