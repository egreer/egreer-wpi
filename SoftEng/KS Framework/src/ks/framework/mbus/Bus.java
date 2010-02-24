package ks.framework.mbus;

import java.util.ArrayList;
import java.util.Hashtable;

import org.compunit.Provide;
import org.compunit.Require;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;

import ks.framework.common.Message;
import ks.framework.interfaces.ILocal;
import ks.framework.interfaces.IProcessMessage;
import ks.framework.interfaces.ISubscribe;

/**
 * Provides a multi-threaded message bus which receives 
 * a message to be processed and delivers it to a dynamic
 * set of processors that have subscribed for the message.
 * 
 * The Message Bus assumes that the relevant processors
 * have already been connected to an appropriate IOutput.
 * That is, this component does not "pass through" requests
 * to connect to IOutput.
 *  
 * @author George Heineman
 *
 */
@Provide({IProcessMessage.class,ILocal.class})
@Require({ISubscribe.class})
public class Bus implements IComponent, IProcessMessage, ILocal {

	/** Subscribers of messages on the bus. */
	ArrayList<ISubscribe> subscribers = new ArrayList<ISubscribe> ();  
	
	/** Subscribers that want ALL messages. */
	ArrayList<ISubscribe> allMessages = new ArrayList<ISubscribe> ();
	
	/** All is signified by '*'. */
	public static final String ALL = "*";
	
	/** Lookup table. Computed at activation. */
	Hashtable<String,ArrayList<ISubscribe>> lookup = new Hashtable<String,ArrayList<ISubscribe>>(); 
	
	/** Debug output only if requested. */
	boolean debug = true;
	
	/**
	 * Grab all targeted messages and register them here. 
	 */
	public boolean activate(IResourceRetriever handler) throws Exception {
		
		// populate lookup table with relevant entries.
		for (ISubscribe sub : subscribers) {
			for (String key : sub.subscription()) {
				
				if (key.equals (ALL)) {
					allMessages.add(sub);
				} else {
					
					ArrayList<ISubscribe> al = lookup.get(key);
					
					// create if not already there
					if (al == null) {
						al = new ArrayList<ISubscribe>();
						lookup.put(key, al);
					}
					
					// now add
					al.add(sub);
				}
			}
		}
		
		return true;
	}

	/**
	 * All connected ISubscribe components are added to the
	 * subscribers list. 
	 */
	public boolean connect(IComponent unit, String interfaceName)
			throws Exception {
		if (interfaceName.equals (ISubscribe.class.getName())) {
			subscribers.add((ISubscribe) unit);
			return true;
		}
		
		return false;
	}

	/** nothing to do here. */
	public void deactivate() throws Exception {
		// throw all away...
		subscribers = new ArrayList<ISubscribe>();
	}

	/**
	 * Deliver message to all local subscribers.
	 */
	public void process(Message m) {
		process (m, null);
	}
	
	/**
	 * Deliver message to all local subscribers except self, which is
	 * presumably the IProcessMessage component that wants to deliver
	 * this local message to other components connected to the Bus.
	 */
	public void process(Message m, IProcessMessage self) {
		boolean sent = false;
		
		// some subscribers are getting all messages: allMessages
		// make sure same message is not sent TWICE
		for (ISubscribe all: allMessages) {
			if (all != self) {
				all.process(m);
				sent = true;
			}
		}
		
		// now see if individual ones to be processed.
		String key = m.getName();
		ArrayList<ISubscribe> subs = lookup.get(key);
		
		if (subs != null) {
			// send out, one at a time, but only to non-self components that weren't
			// in the 'allMessages' set.
			for (ISubscribe sc : subs) {
				if (sc != self && !allMessages.contains(sc)) {
					sc.process(m);
					sent = true;
				}
			}
		}
		
		if (debug && !sent) {
			System.out.println ("Mbus ignoring " + key);
			return;
		}
	}
}
