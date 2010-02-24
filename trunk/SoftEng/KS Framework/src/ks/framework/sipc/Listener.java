package ks.framework.sipc;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import ks.framework.common.Message;
import ks.framework.common.network.CommunicationAgent;
import ks.framework.common.network.SimpleServerConnection;
import ks.framework.interfaces.IAuthorization;
import ks.framework.interfaces.IRegistration;

/**
 * The Listener class is responsible for processing all Client requests. Each
 * request is a serialized <code>Command</code> object which is read from the
 * client socket connection.
 * <p>
 * Differentiates between administrator login (or normal) by means
 * of 'setAdminOnly'.
 * 
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class Listener extends SimpleServerConnection {
	/** Knows of logout command. */
	public static final String logoutCommand = "logout";
	
	/** Knows of login command. */
	public static final String loginCommand = "login";
	
	/** Knows of player/password attributes. */
	public static final String playerAttribute = "player";
	public static final String passwordAttribute = "password";
	
	/** Know who the server is to process all commands. */
	protected Server server;

	/** Name of user connecting from the client. */
	private String who = null;

	/** Communication port. */
	private int port = 7878;

	/** Default Communication port. */
	public static final int defaultPort = 7878;

	/** Determines when users connect and disconnect. */
	IRegistration reg;
	
	/** Authorization agent. */
	IAuthorization auth;
	
	/** Are admins only allowed in? */
	boolean isAdminOnly = false;
	
	/**
	 * Default Listener constructed for given Server.
	 * 
	 * @param cs     Server
	 */
	public Listener(Server cs, IRegistration reg, IAuthorization auth) {
		this(cs, defaultPort, reg, auth);
	}

	/**
	 * Listener constructed for given Server on given port.
	 * 
	 * @param cs    Server to process requests
	 * @param port  port on which to listen
	 */
	public Listener(Server cs, int port, IRegistration reg, IAuthorization auth) {
		server = cs;
		this.reg = reg;
		this.auth = auth;
		this.port = port;
	}

	/**
	 * All communication from a client occurs within the context of a Listener
	 * thread (which we call a session.)
	 * <p>
	 * Responsible for processing logoutCommands and severing the connection.
	 */
	public void handleSession(CommunicationAgent agent) {
		Message m = (Message) agent.readObject();
		
		// client has already disconnected.
		if (m == null) return;
		
		// the first one in must be the login command
		if (!m.isCommand()) { return; }
		String name = m.getName();
		
		if (!name.equalsIgnoreCase(loginCommand)) { return; }
		
		// must retrieve player/password.
		Node login = m.contents();
        NamedNodeMap nnp = login.getAttributes();
        Node player = nnp.getNamedItem(playerAttribute);
        Node password = nnp.getNamedItem(passwordAttribute);
        
        if (player == null || password == null) { return; }
        who = player.getNodeValue();
        String pwd = password.getNodeValue();
        
        // only attempt to authorize if we have an authorization agent.
		if (auth != null) {
			if (isAdminOnly) {
				if (!auth.authorizeAsAdmin(who, pwd)) {
					agent.writeObject(nack(m, 4, "Unable to authorize user '" + who + "' as administrator to login"));
					reg.disconnectUser(who);
					return;
				}
			} else {
				if (!auth.authorize(who, pwd)) {
					agent.writeObject(nack(m, 4, "Unable to authorize user '" + who + "' to login"));
					reg.disconnectUser(who);
					return;
				}
			}
		}

		// Record persistent connection to this user.
        reg.connectUser(who, agent);
        
        // pass along login message.
        server.process(who, m);
        
		// Enter Main Loop to retrieve information from these clients
        boolean cleanLogout = false;
		try {
			Message incoming;
			while ((incoming = (Message) agent.readObject()) != null) {
				
				// only an exception can break us out of here, or receipt
				// of a logout message.
				server.process(who, incoming);
				
				// if a logout was issued from our client, shut down its communication.
				if (incoming.getName().equalsIgnoreCase(logoutCommand)) {
					cleanLogout = true;
					break;
				}
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		
		// deal with logout as required....
		if (!cleanLogout) {
			String cmd = "<command version='1.0' id='1'><logout/></command>";
			Document d = Message.construct(cmd);
			Message lm = new Message(d);
			server.process(who, lm);
		}
		
		Debug.println(who + " has disconnected.");
		reg.disconnectUser(who);
	}

	/**
	 * Generate NACK for given message.
	 *  
	 * @param agent  agent retrieving command.
	 * @param m      command which failed.
	 */
	public static Message nack(Message m, int severity, String text) {
		// produce request.
        StringBuilder sb = new StringBuilder("<request version='1.0' id='" + m.id + "'>");
        sb.append("<nack severity='" + severity + "'>");
        sb.append("<text>" + text + "</text></nack></request>");
        Document d = Message.construct(sb.toString());
        
        // Generate NACK.
        Message r = new Message (d);
        return r;
	}

	/**
	 * Get listener started on port.
	 */
	public boolean start() {
		Debug.println("Accepting calls on " + port);
		return super.start(port);
	}

	/** Determine whether authorization only enabled for admins. */
	public void setAdminOnly(boolean isAdminOnly) {
		this.isAdminOnly = isAdminOnly;
	}

}