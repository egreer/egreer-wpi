package hd.client;

import hd.client.entities.Account;
import hd.client.entities.Permissions;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 * 
 * @see LoginService
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public interface LoginServiceAsync {
	void validateLogin(Account login, AsyncCallback<Boolean> callback);
	void changePassword(Account login, String newPassword, String confirmPassword, AsyncCallback<Boolean> callback);
	void createAccount(String username, String password, String confirmPassword, Permissions permissions, AsyncCallback<Boolean> callback);
	void getPermissions(String username, AsyncCallback<Permissions> callback);
	void changePermissions(String requester, String username, Permissions permissions, AsyncCallback<Boolean> callback);
	void retrieveAllAccounts(AsyncCallback<List<Account>> callback);
}
