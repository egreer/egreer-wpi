package ks.framework.common.network;

import java.net.*;
import java.io.*;

/**
 * Base class to manage the client-side of a Socket connection.
 * <p>
 * @author George Heineman (heineman@cs.wpi.edu)
 */
public class SimpleClientConnection {
	/** Socket once connection has been made. */
	private Socket socket;

	/** host to which connection is to be made. */
	private String host;

	/** Port on which to connect. */
	private int port;

	/** How to read from server. */
	private CommunicationAgent agent = null;

	/** Status of connection. */
	private boolean connectState = false;

	/** Protect default construction to subclasses only. */
	protected SimpleClientConnection() {
	}

	/**
	 * Prepare connection on given host and port.
	 * 
	 * @param theHost       host to which connection is requested.
	 * @param thePort       Socket port number to which to connect.
	 * @throws IOException  Any failure is reported as an exception.
	 */
	public SimpleClientConnection(String theHost, int thePort) throws IOException {
		connect(theHost, thePort);
	}

	/**
	 * Connect to given host at specified port.
	 * 
	 * @param theHost       host to which connection is requested.
	 * @param thePort       Socket port number to which to connect.
	 * @throws IOException  Any failure is reported as an exception.
	 */
	void connect(String theHost, int thePort) throws IOException {
		host = theHost;
		port = thePort;

		socket = new Socket(host, port);

		agent = new CommunicationAgent (socket.getInputStream(), socket.getOutputStream());
		connectState = true;
	}

	/**
	 * Terminate the connection to the server.
	 */
	public void disconnect() {
		if (isConnected()) {
			agent.close();

			connectState = false;
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
					System.err.println("SimpleClientConnection: Error in disconnect(), " + e.toString());
				}
			}
		}
		agent = null;
		socket = null;
	}
	/**
	 * Determines if server is open for communication?
	 *
	 * @return  true if server is available for communication; false otherwise.
	 */
	public boolean isConnected() {
		return connectState;
	}

	/**
	 * Return agent responsible for communication to server.
	 * 
	 * @return agent responsible for communication.
	 */
	public CommunicationAgent getAgent() {
		return agent;
	}

}