package ks.framework.communicator;

import java.util.*;

import ks.framework.common.Message;
import ks.framework.interfaces.ICommunicator;
import ks.framework.interfaces.ICommunicator2;
import ks.framework.interfaces.IConnectionStatus;
import ks.framework.interfaces.IDisconnectUser;
import ks.framework.interfaces.IOutput;
import ks.framework.interfaces.IRegistration;

import org.compunit.*;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;

/**
 * Responsible for all communication from the server to its clients.
 * <p>
 * Communicator will need access to an IUserManager object to be able to
 * identify the set of active uers during a broadcast.
 * 
 * Note that we read Commands from the client and write messages
 * to the client.
 * 
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
@Provide({IOutput.class, IRegistration.class, IConnectionStatus.class, IDisconnectUser.class})
public class Communicator implements IComponent, IConnectionStatus, IOutput, IRegistration, IDisconnectUser {

	/** Record ICommunicator entities on behalf of the server with regard to a client. */
	private Hashtable<String,ICommunicator> communicationHash = new Hashtable<String,ICommunicator>();

	/**
	 * Every CompUnit component must have a no-argument constructor.
	 */
	public Communicator() {	}

	/**
	 * Distribute command to all users EXCEPT self.
	 * 
	 * @param m     Message to be delivered to all active users
	 */
	public void distribute(Message m) {
		distribute(communicationHash.keySet().iterator(), m);
	}

	/**
	 * Distribute command to given users EXCEPT self.
	 * 
	 * @param users     Iterator (of String) of users
	 * @param m         Message to be delivered to all active users
	 */
	public void distribute(Iterator<String> users, Message m) {

		// for each user, send message
		String from = m.getOriginator();
		while (users.hasNext()) {
			String toUser = users.next();

			// Skip SELF
			if (toUser.equals(from)) {
				continue;
			}
			
			output(toUser, m);
		}
	}

	/**
	 * Register with the communicator the means to interact with given userName.
	 * 
	 * @param userName    String representing user connecting to server
	 * @param agent       ICommunicator object managing our communication.
	 */
	public void connectUser(String userName, ICommunicator agent) {
		communicationHash.put (userName, agent);
	}
	
	/**
	 * Remove user from communication set.
	 * 
	 * @param userName    String representing user connecting to server
	 */
	public void disconnectUser(String userName) {
		communicationHash.remove (userName);
	}

	/**
	 * Helper method to ensure delivery of message to agent for given player.
	 * 
	 * @param player      designated recipient
	 * @param m           message for delivery
	 */
	private void output(String player, Message m) {
		ICommunicator agent = (ICommunicator) communicationHash.get (player);
		if (agent == null) {
			return;  // not much to do!
		}

		// consider it delivered.
		agent.writeObject (m);
	}
	
	/**
	 * Delivers message to intended receiver as specified in message.
	 * <p>
	 * The routing of the message is determined by the message's attributes,
	 * including its scope, from, and to user.
     *
	 * @param m    Message containing all information including designated recipient.
	 */
	public void output(Message m) {
		output (m.getRecipient(), m);
	}

	/** This component has no required elements. */
	public boolean connect(IComponent block, String interfaceName) throws Exception {
		return false;
	}

	/** Nothing to do here. */
	public void deactivate() throws Exception {
		
	}

	/** Activate has no functionality either. */
	public boolean activate(IResourceRetriever handler) throws Exception {
		return true;
	}

	/** Simply determine if user is online. */
	public boolean isOnline(String user) {
		return communicationHash.containsKey(user);
	}

	/** Disconnect if user is logged on. */
	public boolean disconnect(String username) {
		ICommunicator ic = communicationHash.get(username);
		if (ic == null) { return false; }
		
		// see if we can expose logic here. Awkward to say least since we
		// don't want to change interface.
		if (ic instanceof ICommunicator2) {
			((ICommunicator2)ic).close();
			return true;
		}
		
		// can't handle this!
		return false;		
	}	
}