package ks.framework.interfaces;

import ks.framework.common.Message;

/**
 * Interface to declare the processing of a message.
 * <p>
 * The message may be a command, request or adminCommand. Sometimes the
 * class implementing this interface processes the command to generate
 * a request. Other times, the class implementing the interface is passing
 * along the command (without processing) along to another class who is
 * responsible for the actual processing.
 * 
 * @author George Heineman
 */
public interface IProcessMessage {
	/** Takes a command to be processed. */
	void process (Message m);
}
