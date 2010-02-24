package ks.framework.interfaces;

import java.util.Iterator;

/**
 * @beta Ready to go
 */
public interface IUM_DB {
	
	/**
	 * Function returns an IUser object for the given username
	 * @param String representing the username to fetch information on 
	 * @return IUser object for the user, null if user does not exist
     **/
	public IUser getUser(String username);

	/**
	 * Function creates and returns an IUser object for the given username.
	 * 
 	 * Dangerous method to use! Potentially flagged for removal to an IAdmin_UM_DB interface
	 * 
	 * @param String representing the username of the new user 
	 * @return IUser object for the new user, null if creation of user failed.
	 **/
	public IUser addUser(String username, String password);

	/**
	 * Function returns an IUserCursor object for all users
	 * @return IUserCursor object for all users, null if no users
	 **/
	public Iterator<IUser> getUsers();
	
	/**
	 * Function returns an IUserCursor object for all Administrative users
	 * @return IUserCursor object for all admin users, null if no admin users
	**/
	public Iterator<IUser> getAdmins();

	/**
	 * Function returns an IUserCursor object for all users who have not logged into the system within 6 months
	 * @return IUserCursor object for all stale users, null if no stale users
	**/
	public Iterator<IUser> getStaleUsers();
	
	/**
	 * Function removes the given user from the system.
	 * 
	 * Dangerous method to use! Potentially flagged for removal to an IAdmin_UM_DB interface
	 * 
	 * @conditional Admin User may not be removed
	 * @return boolean representing if the removal was successful (true), false otherwise
	**/
	public boolean removeUser(String username);
	
	/**
	 * Updates the user based upon changes made to the IUser object.
	 * 
	 * This method may throw an exception if there was some problem with
	 * the storing of information in the DB.
	 * 
	 * @param user
	 */
	public boolean updateUser (IUser user);

}