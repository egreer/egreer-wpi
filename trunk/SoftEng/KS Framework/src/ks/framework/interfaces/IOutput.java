package ks.framework.interfaces;

import java.util.Iterator;

import ks.framework.common.Message;

/**
 * Interface for components to communicate with users at their client.
 * <p>
 * @author George Heineman (heineman@cs.wpi.edu)
 */
public interface IOutput {

    /**
     * Distribute Message to intended recipients. 
     * <p>
     * Each message is either a broadcast message or has a recipient.
     * 
     * @param m   XML message as a Document.
     */
    void distribute (Message m);
    
    /**
     * Distribute message to the given set of users (EXCEPT self).
     * <p>
     * This request overrides the 'recipient' as found in the Message. 
     * 
     * @param users Iterator an Enumeration of User objects to deliver message to
     * @param m Message the Message to be delivered.
     */
    void distribute (Iterator<String> users, Message m);
 

}
