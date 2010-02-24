package planningPoker;
/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
/**
 * The userManager class is used to process the logic of manipulating the user object
 */
public class UserManager {

	/**
	 * @param username the username of the user for which the object is being retrieved
	 * @return user object to give the user
	 */
	@SuppressWarnings("unchecked")
	
	public static User getUser(String username){

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(User.class, "userName == nameParam");
		query.declareParameters("String nameParam");

		List<User> results = (List<User>) query.execute(username);

		if (results.isEmpty()){
			return null;
		}	
		pm.detachCopy(results.get(0));
		pm.close();
		return results.get(0);

	}

	/** 
	 * @param firstName first name of user
	 * @param lastName last name of user
	 * @param username desired username for the user
	 * @param password desired password for the user (already encrypted in MD5)
	 * @return newUser the newly created user object or null if name already exists
	 */
	public static User createUser(String firstName, String lastName, String username, String password){

		User newUser = new User(username, firstName, lastName, password);
		
		if (getUser(username)== null){
			if (username.equals("admin")) newUser.setPermissions("admin"); //GOD MODE ENABLED
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Transaction tx = pm.currentTransaction();

			try
			{
				tx.begin();
				pm.makePersistent(newUser);
				tx.commit();
			}
			finally
			{
				if (tx.isActive())
				{
					tx.rollback();
				}
			}
			pm.detachCopy(newUser);
			pm.close();
			return newUser;
		}else{
			return null;
		}
	}

	/**
	 * Overloaded method to allow for getAllUserNames to be called without params for all users 
	 **/
	public static Collection<String> getAllUsernames(){
		return getAllUsernames("ALL");
	}
	
	/** 
	 * @param specifier to determine which usernames should be returned
	 * @return a collection of all usernames
	 */
	@SuppressWarnings("unchecked")
	public static Collection<String> getAllUsernames(String mode){
			
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(User.class);

		List<User> results = (List<User>) query.execute();
		
		ArrayList<String> usernames = new ArrayList<String>();
		
		Iterator<User> iterator= results.iterator();
		while(iterator.hasNext()){
			User temp = iterator.next();
			if ((temp.getPermissions().equals(mode))|| (mode == "ALL")){
			usernames.add(temp.getUserName());
			}
		}	
		return usernames;		
	}

	
	/**
	 * Allows for reset of password if old password is known
	 * @param username username of user resetting password
	 * @param password new password that will be set
	 * @param oldPassword old password used for verification
	 * @return true if successful, otherwise false
	 */
	public static boolean updatePassword(String username, String password, String oldPassword){
		boolean returner = true;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Transaction tx = pm.currentTransaction();
			User theUser;
			
			try {
				tx.begin();
				theUser = getUser(username);
				String oldStoredPassword = theUser.getPassword();
				if (oldStoredPassword.equals(oldPassword)){
				theUser.setPassword(oldPassword, password);
				}
				pm.makePersistent(theUser);
			tx.commit();
			}finally{
				if (tx.isActive()){
					tx.rollback();
					returner = false;
				}
			}
					
			pm.close();
		
		return returner;
	}

	public static boolean setPermissions(String username, String permission){
		boolean returner = false;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User user = UserManager.getUser(username);
		returner = user.setPermissions(permission);
		if (returner) {
			pm.makePersistent(user);
		}
		pm.close();
		return returner;
	}

}

