package ks.framework.cipc;


/**
 * The Talker class is responsible for all communication with the server.
 * <p>
 * Once the communication to the server's port is set up, Talker issues
 * a "login" command. Thereafter, all commands delivered to talker are
 * simply sent along to the server.
 * <p>
 * Note that the Talker does not actually read this response, since it is
 * responsible only for speaking to the server.
 * <p>
 * After a successful login, all communication through Talker is using Command
 * objects that are linearized and written to be sent to the Server.
 * 
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
import java.io.*;

import org.w3c.dom.Document;

import ks.framework.common.Message;
import ks.framework.common.network.CommunicationAgent;
import ks.framework.common.network.SimpleClientConnection;
import ks.framework.interfaces.IConnectionHandler;

/**
 * Class responsible for sending command objects to the server. 
 */
class Talker implements Runnable {
	
	/** Debugging. */
	static boolean DEBUG = true;

	/** Connection to server. */
	protected SimpleClientConnection me;

	/** Start with accepted default port address. */
	public int port = 7878;

	/** Agent awaiting connection status. */
	private IConnectionHandler connector;

	/** Executing within our own thread. */
	private Thread thread = null;

	/** Target host we are trying to connect with. */
	private String host = null;

	/** name of user. */
	private String who = null;

	/** Password of user. */
	private String password = null;

	/** Are we connected to the server? */
	protected boolean connected;

	/** Need to keep track of who responds to messages from the server. */
	public Talker(IConnectionHandler parent) {
		this.connector = parent;
	}

	// Thread is told to disconnect
	public void disconnect() {
		notifyDisconnect();

		// we are no longer connected.
		connected = false;
	}
	/**
	 * Initiate communication with server.
	 * 
	 * First one to send anything to the server. The first command sent by the 
	 * client is a Login. Note that we cannot wait for the Message response. That
	 * comes through its own channel.
	 */
	private boolean doInitiate(String host, String who, String password) {
		if (DEBUG) System.err.println("Connecting to " + host + ":" + who);

		try {
			me = new SimpleClientConnection(host, port);
			
		} catch (IOException ioe) {
			System.err.println("Talker::doInitiate(). Could not create a network connection:" + ioe.toString());
			return false;
		}

		if (me == null || !me.isConnected()) {
			System.err.println("Talker::doInitiate(). Could not connect to '" + host + ":" + port + "'.");
			return false;
		}
		
		if (DEBUG) System.err.println("trying to connect.");

		// send login command.
		String login = "<command version='1.0' id='0938409238490'>" +
					   "   <login player='" + who + "' password='" + password + "'/>" +
					   "</command>";
			
		Document d = Message.construct(login);
		Message m = new Message(d);

		if (!writeObject (m)) {
			System.err.println("Talker::doInitiate(). Could not connect to '" + host + ":" + port + "'.");
			return false;
		}
		
		// looks good.
		return true;
	}


	/**
	 * Determine if we are connected. Creation date: (9/30/01 12:10:53 AM)
	 * 
	 * @return boolean
	 */
	public boolean isConnected() {
		return connected;
	}

	public void join(long millis) {
		if (thread == null)
			return;
		try {
			thread.join(millis);
		} catch (InterruptedException ie) {

		}
	}

	private synchronized void notifyDisconnect() {
		notify();
	}

	/**
	 * Initiate server connection and wait until we are disconnected.
	 * 
	 * If fail to initiate, disconnect and terminate thread.
	 */
	public void run() {
		try {
			// do the initiate here, in separate thread
			if (!doInitiate(host, who, password)) {
				System.err.println("Talker::run(). Unable to connect within Talker.");
				// actually close down socket
				connector.connected(false);
				me.disconnect();
				return;
			}

		} catch (Exception e) {
			System.err.println("Talker::run(). Unexpected exception in run:" + e.toString());
			connector.connected(false);
			if (me != null) {
				me.disconnect();
			}
			return;
		}

		// we are connected...
		connector.connected(true);
		setConnected(true);

		// At this point, Talker is done, but we need to keep the connection
		// open. So we wait until we get a request to disconnect
		waitForDisconnect();

		// actually close down socket
		connector.connected(false);
		me.disconnect();
	}

	/**
	 * Set our connection state. Creation date: (9/30/01 12:10:53 AM)
	 * 
	 * @param newConnected
	 *            boolean
	 */
	public void setConnected(boolean newConnected) {
		connected = newConnected;
		if (!connected) {
			disconnect();
		}
	}

	// Useful for altering server port. BUT shouldn't be called in mid-stream!

	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Talker needs these three values to begin communicating with the server.
	 */
	public void setvalues(String host, int port, String who, String password) {
		this.host = host;
		this.port = port;
		this.who = who;
		this.password = password;
	}

	/**
	 * Start the main thread running.
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	private synchronized void waitForDisconnect() {
		try {
			wait();
		} catch (InterruptedException ie) {
		}
	}

	/**
	 * Send command to the server.
	 * 
	 * @param msg   Command to be sent.
	 * @return
	 */
	public boolean writeObject(Message msg) {
		CommunicationAgent agent = me.getAgent();
		return agent.writeObject (msg);
	}

	public CommunicationAgent getAgent() {
		return me.getAgent();
	}
}