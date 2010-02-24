package ks.framework.interfaces;

/**
 * Enable the registering of users logging in and logging out.
 * 
 * @author George Heineman
 */
public interface IRegistration {
	   
    /**
     * Register with the communicator the means to interact with a user.
     *
     * @param userName   user for whom communication is being managed.
     * @param agent      ICommunicator object managing the communication
     */
    void connectUser(String userName, ICommunicator agent);
    
    /** 
     * Disconnect user.
     * 
     * @param userName
     */
    void disconnectUser(String userName);
}
