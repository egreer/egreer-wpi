package ks.framework.interfaces;

import ks.framework.common.Message;

/**
 * Interface to declare message should not be communicated to server
 * or client, but rather should be delivered back to the MessageBus
 * to see if any other connected processor wants it. 
 * 
 * @author George Heineman
 */
public interface ILocal {
	/** Deliver message to all local subscribers except self */
	void process (Message m, IProcessMessage self);
}
