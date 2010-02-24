package ks.framework.interfaces;

/**
 * Components that connect to the MessageBus rely on this 
 * interface to declare the messages (either requests or
 * commands) for which it declares an interest.
 * 
 * The communication protocol is decided when the application
 * is initialized and cannot change.
 * 
 * This interface requires the reference to the processor
 * which processes messages (i.e., it implements IProcessMessage).
 * 
 * @author George Heineman
 */
public interface ISubscribe extends IProcessMessage {

	/** 
	 * Subscribe to receive the messages BY NAME that appear
	 * on the message bus.
	 */
	public String [] subscription();
	
}
