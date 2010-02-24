package ks;


import java.util.Iterator;

/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @alpha  WARNING These are alpha interfaces
 */
public class IUM_DB {
		
	/**
	 * Function returns an IUser object for the given username
	 * @param String representing the username to fetch information on 
	 * @return IUser object for the user, null if user does not exist
	**/
	public IUser getUser(String username){
		return null;
	}

	/**
	 * Function creates and returns an IUser object for the given username
	 * @param String representing the username of the new user 
	 * @return IUser object for the new user, null if creation of user failed.
	**/
	public IUser addUser(String username, String Password){
		return null;
	}

	/**
	 * Function returns an IUserCursor object for all users
	 * @return IUserCursor object for all users, null if no users
	**/
	public Iterator<IUser> getUsers(){
		return null;
	}
	
	/**
	 * Function returns an IUserCursor object for all Administrative users
	 * @return IUserCursor object for all admin users, null if no admin users
	**/
	public Iterator<IUser> getAdmins(){
		return null;
	}

	/**
	 * Function returns an IUserCursor object for all users who have not logged into the system within 6 months
	 * @return IUserCursor object for all stale users, null if no stale users
	**/
	public Iterator<IUser> getStaleUsers(){
		return null;
	}
	
	/**
	 * Function removes the given user from the system
	 * @conditional Admin User may not be removed
	 * @return boolean representing if the removal was successful (true), false otherwise
	**/
	public boolean removeUser(String username){
		return false;
	}
	
	/**
	 * Updates the user based upon changes made to the IUser object.
	 * 
	 * This method may throw an exception if there was some problem with
	 * the storing of information in the DB.
	 * 
	 * @param user
	 */
	public boolean updateUser (IUser user){
		return false;
	}

}