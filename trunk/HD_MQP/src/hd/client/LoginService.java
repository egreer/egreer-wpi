package hd.client;

import hd.client.entities.Account;
import hd.client.entities.Permissions;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side Interface for the RPC service.
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	
	/** Validates a login. Login is passed in as an account with a username and password
	 * 
	 * @param login		The account to validate
	 * @return			True if the login is valid, False if not
	 */
	Boolean validateLogin(Account login);
	
	/** Changes the password on an account
	 * 
	 * @param login				The Account to validate against
	 * @param newPassword		The new password to set for the account
	 * @param confirmPassword	The same as the new password 
	 * @return					True if the password was changed, false if not
	 */
	Boolean changePassword(Account login, String newPassword, String confirmPassword);
	
	/** Creates an account in the database
	 *   
	 * @param username	The desired username
	 * @param pw		The account password
	 * @param confirmPW	The confirmation to the account password
	 * @return			True if account was created, false if not 
	 */
	Boolean createAccount(String username, String password, String confirmPassword, Permissions permissions);
	
	/** Retrieves the permission of the user
	 * 
	 * @param username	The username of the employee 
	 * @return			The Permission
	 */
	Permissions getPermissions(String username);
	
	/** Changes the permissions of the of the accounts
	 * 
	 * @param requester		The username of the person who is changing the password 
	 * @param username		The username of the account to change		
	 * @param permissions	The permissions to change to 
	 * @return				Returns True if the permissions were changed, False if they were not
	 */
	Boolean changePermissions(String requester, String username, Permissions permissions);
		
	/** Returns a list of all accounts 
	 * Does not return passwords with the accounts 
	 */
	List<Account> retrieveAllAccounts();
}
