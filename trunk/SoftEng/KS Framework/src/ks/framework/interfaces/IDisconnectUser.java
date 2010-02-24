package ks.framework.interfaces;

/**
 * <code>IDisconnectUser</code> interface provides the functionality to forcibly
 * disconnect a logged-in user from the system.
 * <p>
 * All methods within <code>IDisconnectUser</code> interface should only be 
 * invoked by an administrator (and must be validated by the caller of this
 * method).
 * <p>
 * <code>UserManager</code> component should invoke this interface to execute
 * necessary logic after receiving a <code>kick</code> command.
 *
 * @author Yi Wang (Neakor)
 * @version Creation date: 04-24-08 16:41 EST
 * @version Modified date: 04-24-08 16:45 EST
 */
public interface IDisconnectUser {

    /**
     * Disconnect the user with given username from the system.
     * 
     * @param username The username of the user to be disconnected.
     * @return True if the specified user is disconnected. False if user is not 
     * logged in.
     */
    public boolean disconnect(String username);
}