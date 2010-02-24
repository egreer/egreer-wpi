package ks.framework.common.network;

import java.net.*;
import java.io.*;  

/**
 * Base class to manage the server-side of a Socket connection.
 * <p>
 * @author George Heineman (heineman@cs.wpi.edu)
 */
public abstract class SimpleServerConnection implements Runnable, Cloneable {
	// primary server fielding all accept requests.
	private ServerSocket server = null;

	// returned by each successful accept attempt.
	private Socket socket = null;

	private Thread life = null;

	private Thread twin = null;

	// means of communication with client.
	private CommunicationAgent agent = null;

	private boolean original = true;

	private boolean runState = false;

	private boolean connectState = false;

	final int defaultPort = 4646;
   
	/**
	 * Keep default constructor protected for subclasses.
	 */
    protected SimpleServerConnection() {
    	
	}

	public CommunicationAgent getAgent() {
		return agent;
	}

	
   public void close() {
		shutdown();
	}

   /**
    * Subclasses should provide their own handler for
    * new data appearin on the input
    * 
    * @param agent    Agent managing the communication session.
    */
	public abstract void handleSession(CommunicationAgent agent);

	public boolean isConnected() {
		return connectState;
	}

	public boolean isRunning() {
		return runState;
	}

	/** Forces a manual shutdown of the original ServerSocket. */
	public void killServer() {
		try {
			if (server != null) {
				server.close();
			}
		} catch (IOException ioe) {
			System.err.println("SimpleServerConnection::killServer() fails:" + ioe.toString());
		}
		server = null;
	}

	public void run() {
		if (original) // this is the primary server thread.
		{
			runState = true;
			while (true) {
				try {
					socket = server.accept();
				} catch (IOException e) {
					// we are the primary socket thread. BAD NEWS!
					shutdown();
					return;
				}

				SimpleServerConnection copy = null;
				try {
					copy = (SimpleServerConnection) clone();
				} catch (CloneNotSupportedException e) {
					trouble("Unexpected 'CloneNotSupported' exception...");
				}

				copy.life = null;
				///copy.server = null; Keep original server available to allow
				// any child thread to kill it using the killServer() method
				// call. Dangerous and powerful
				copy.original = false;
				copy.runState = false;
				twin = new Thread(copy);
				twin.start();
			}
		} else {
			// this is the twin clone coming to life!
			runState = true;
			connectState = true;
			try {
				agent = new CommunicationAgent (socket.getInputStream(), socket.getOutputStream());
			} catch (IOException e) {
				trouble("Could not create data streams...");
				//shutdown();
				if (agent != null) agent.close();
				return;
			}
			handleSession(agent);
			//shutdown();
			if (agent != null) {
				agent.close();
				agent = null;  // close out.
			}
			
		}
	}      
	
	/**
	 * Cleanly shutdown connection.
	 */
	public void shutdown() {
		life = null;
		twin = null;
		runState = false;
		if (connectState) {
			agent.close();

			connectState = false;
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
				}
			}
		}
		
		// too aggressive? HEINEMAN 3-16-2005
		try {
			if (socket != null) socket.close();
			if (server != null) server.close();
		} catch (IOException ioe) {
			System.err.println ("SimpleServerConnection:" + ioe);
		}
		
		socket = null;
		server = null;
		agent = null;
	}

	/**
	 * Start listening on the default port.
	 * 
	 * @return true when connection is valid.
	 */
	public boolean start() {
		return start(defaultPort);
	}

	/**
	 * Start listening on the given port.
	 * 
	 * @param thePort
	 * @return true when connection is valid.
	 */
	public boolean start(int thePort) {
		if (life == null) {
			life = new Thread(this);
			life.setPriority(Thread.MAX_PRIORITY);
		}
		try {
			server = new ServerSocket(thePort);
		} catch (IOException e) {
			trouble("Could not create new server socket...");
			return false;
		}
		life.start();
		return true;
	}


	/**
	 * Handle error messages.
	 * 
	 * @param message
	 */
	void trouble(String message) {
		System.err.println("SimpleServerConnection::" + message);
	}
}
