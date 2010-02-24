package ks.framework.sipc;

import java.util.*;

import ks.framework.common.Message;
import ks.framework.interfaces.IAuthorization;
import ks.framework.interfaces.IProcessMessage;
import ks.framework.interfaces.IRegistration;

import org.compunit.Property;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.ICustomizableComponent;
import org.compunit.interfaces.IResourceRetriever;


/**
 * Server processes requests received from client.
 */
@Require({IRegistration.class,IProcessMessage.class,IAuthorization.class})
@Property({Server.portVariable,Server.adminVariable})
public class Server implements ICustomizableComponent {
	/**
	 * Server Version.
	 * 
	 * @since V1.5.1
	 */
	public static final String version = "2.0";

	/**
	 * Server Version Date.
	 * 
	 * @since V1.5.1
	 */
	public static final String versionDate = "2008-Mar-19-14-35-23";

	/** Listener for client requests. */
	Listener listener = null;
	
	/** Server knows its registrant. */
	protected IRegistration reg = null;
	
	/** Server knows its authorizer. */
	protected IAuthorization auth = null;
	
	/** Responsible for dealing with commands. */
	protected IProcessMessage processor = null;

	/** Property for port. */
	public static final String portVariable = "port";
	
	/** Property to determine whether to allow admin only logins. */
	public static final String adminVariable = "adminOnly";

	/** Port to be listened for [default=7878]. */
	String portString = "7878";
	
	/** Administrator logins only. */
	boolean isAdminOnly = false;
	
	/** Default Constructor. */
	public Server() { }
	
	/**
	 * Properties come here.
	 * 
	 * Known props: port and admin
	 */
	public void customize(Properties properties) throws Exception {
		portString = properties.getProperty (portVariable);
		if (portString == null) portString = "7878";  // default
		
		String av = properties.getProperty(adminVariable);
		if (av != null) {
			isAdminOnly = Boolean.valueOf(av);
		}
	}

	/**
	 * Activate component.
	 */
	public boolean activate(IResourceRetriever handler) throws Exception {

		int port = -1;
		try {
			port = Integer.valueOf(portString).intValue();
		} catch (Exception e) {
			System.err.println("Server::main() unable to set port to:" + portString);
			return false;
		}

		// start up listener
		try {
			listener = new Listener(this, port, reg, auth);
			listener.setAdminOnly (isAdminOnly);
			boolean response = listener.start();
			if (!response) return false;
		} catch (Exception error) {
			System.out.println("Could not start listener server:" + error);
		}

		return true;
	}

	/**
	 * Connect to our required interfaces.
	 */
	public boolean connect(IComponent unit, String interfaceName) throws Exception {
		if (unit == null) return false;
		if (interfaceName == null) return false;

		if (interfaceName.equals (IRegistration.class.getName())) {
			reg = (IRegistration) unit;
			return true;
		}
		
		if (interfaceName.equals (IProcessMessage.class.getName())) {
			processor = (IProcessMessage) unit;
			return true;
		}
		
		if (interfaceName.equals (IAuthorization.class.getName())) {
			auth = (IAuthorization) unit;
			return true;
		}
				
		// NOPE
		return false;
	}

	/**
	 * Not much to do here.
	 */
	public void deactivate() throws Exception {
		if (listener != null) {
			System.err.println ("Shutting down server.");
			listener.killServer();
			listener = null;
		}
	}

	/**
	 * Receive message from client and hand off to our controller.
	 */
	public void process(String who, Message incoming) {
		incoming.setOriginator(who);
		try {
			processor.process(incoming);
		} catch (Exception e) {
			System.err.println ("Server detected error processing " + incoming.getName() + " message for " + who);
			e.printStackTrace();
		}
	}
}