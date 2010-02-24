package ks.framework.cipc;

import ks.framework.common.Message;
import ks.framework.interfaces.IConnection;
import ks.framework.interfaces.IConnectionHandler;
import ks.framework.interfaces.IProcessMessage;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;

/**
 * Client inter-process communication component.
 * <p>
 * Responsible for initiating a connection to the server inter-process
 * communication component.  
 * 
 * 
 * @author George Heineman
 */
@Provide({IConnection.class, IProcessMessage.class,IConnectionHandler.class})
@Require({IProcessMessage.class, IConnectionHandler.class})
public class Client implements IComponent, IProcessMessage, IConnection, IConnectionHandler {

	/** Entity to whom we deliver messages that we received from server. */
	IProcessMessage processor;
	
	/** Entity to whom we contact as server connections are made/unmade. */
	IConnectionHandler connector;
	
	/** Talker to communicate via network. */
	Talker talker;
	
	/** Reader to process returning Messages. */
	Reader reader;
	
	/** Are we connected? */
	boolean isConnected = false;
	
	/** CompUnit components are activated prior to their execution. */
	public boolean activate(IResourceRetriever handler) throws Exception {
		return true;
	}

	/** All required interfaces are to be satisfied here. */
	public boolean connect(IComponent unit, String interfaceName) throws Exception {
		if (unit == null) { return false; }
		if (interfaceName == null) { return false; }
		
		if (interfaceName.equals (IProcessMessage.class.getName())) {
			processor = (IProcessMessage) unit;
			return true;
		}
		
		if (interfaceName.equals (IConnectionHandler.class.getName())) {
			connector = (IConnectionHandler) unit;
			return true;
		}
		
		return false;
	}

	/** CompUnit components are de-activated upon completion. */
	public void deactivate() throws Exception {	}

	/** Initiate the connection to the server. */
	public boolean connect(String hostname, int port, String user, String password) {
		talker = new Talker (this);
		talker.setvalues(hostname, port, user, password);
		talker.start();
		return true;
	}

	/** Disconnect from the server. */
	public boolean disconnect() {
		if (talker != null) {
			talker.disconnect();
			talker = null;
			return true;
		}
		
		return false;
	}
	
	/** pass along to server. */
	public void process(Message m) {
		if (!isConnected) return;
		
		talker.writeObject(m);
	}

	/** Once we are connected, then we can start up a reader. */
	public void connected(boolean status) {
		if (status) {
			reader = new Reader (talker.getAgent(), processor, connector);
			reader.start();
		}
		
		// pass along to higher ones...
		isConnected = status;
		connector.connected(status);
	}

}
