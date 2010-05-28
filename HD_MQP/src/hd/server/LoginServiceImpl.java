package hd.server;

import hd.client.LoginService;
import hd.client.entities.Account;
import hd.client.entities.Permissions;
import hd.client.profile.Employee;
import hd.server.db.PMF;
import hd.server.profile.UserServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/** The server side implementation of the Login RPC service.
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
public class LoginServiceImpl extends RemoteServiceServlet implements
LoginService {

	/** Auto generated serial ID */
	private static final long serialVersionUID = 7264618151876216704L;


	/*
	 * (non-Javadoc)
	 * @see hd.client.LoginService#validateLogin(hd.client.entities.Account)
	 */
	@Override
	public Boolean validateLogin(Account login) {
		Account dbAccount = getAccount(login);

		if (dbAccount != null && dbAccount.getPassword().equals(login.getPassword())){
			return dbAccount.getPassword().equals(login.getPassword());
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.LoginService#changePassword(hd.client.entities.Account, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean changePassword(Account login, String newPassword,
			String confirmPassword) {
		Boolean returner = true;
		if (login == null || !newPassword.equals(confirmPassword)) return false;

		Account returned = getAccount(login);
		if (returned == null) return false;

		//Change password and commit
		returner = returned.changePassword(login.getPassword(), newPassword);
		if (returner){
			PersistenceManager pm = PMF.get().getPersistenceManager();

			Transaction tx = pm.currentTransaction();

			//Try to commit to database
			try
			{
				tx.begin();
				pm.makePersistent(returned);
				tx.commit();
			}
			finally
			{
				if (tx.isActive())
				{
					tx.rollback();
					returner = false;
				}
			}

			pm.close();	
		}

		return returner;
	}

	/** Retrieves account from the database
	 * 
	 * @param a	The account to find
	 * @return	The account from the DB, returns null if account was not found
	 */
	@SuppressWarnings("unchecked")
	public static Account getAccount(Account a){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Account.class,
		"username == nameParam");
		query.declareParameters("String nameParam");

		List<Account> results = (List<Account>) query.execute(a.getUsername());

		Account temp = null;

		if (!results.isEmpty())	temp = results.get(0);
		pm.close();
		return temp;
	}


	/*
	 * 	(non-Javadoc)
	 * @see hd.client.LoginService#createAccount(java.lang.String, java.lang.String, java.lang.String, hd.client.entities.Permissions)
	 */
	@Override
	public Boolean createAccount(String username, String pw, String confirmPW, Permissions permission){
		Boolean returner = Boolean.TRUE;
		//TODO password requirements
		if (!pw.equals(confirmPW)) return Boolean.FALSE;

		Account acct = new Account (username, pw, permission);

		//Ensure user doesn't exist
		if(getAccount(acct) != null) return Boolean.FALSE;;
		Employee profile  = new Employee();  //Employees are created with accounts
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		//Try to add to database
		try
		{
			tx.begin();

			pm.makePersistent(acct);

			tx.commit();
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
				returner = Boolean.FALSE;
			}
		}

		tx = pm.currentTransaction();

		//Try to add profile to database
		try
		{
			tx.begin();
			pm.makePersistent(profile); //Persist employee to DB
			tx.commit();

		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
				returner = Boolean.FALSE;
			}
		}


		if (returner){
			pm.detachCopy(acct);
			pm.detachCopy(profile);
			returner = (new UserServiceImpl()).associateAccountToEmployee(acct, profile); //Associate new employee with account
		}
		pm.close();

		return returner;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.LoginService#getPermissions(java.lang.String)
	 */
	@Override
	public Permissions getPermissions(String username) {
		Account returned = getAccount(new Account(username, "pwd"));

		if (returned != null) return returned.getPermission();
		else return null;
	}

	/*
	 * (non-Javadoc)
	 * @see hd.client.LoginService#changePermissions(java.lang.String, java.lang.String, hd.client.entities.Permissions)
	 */
	@Override
	public Boolean changePermissions(String requester, String username,
			Permissions permissions) {
		Account theChanger = getAccount(new Account(requester, "pwd"));
		Account theChangee = getAccount(new Account(username, "pwd"));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		Boolean returner = Boolean.TRUE;

		if (theChanger == null || theChangee == null || 
				!(theChanger.getPermission() == Permissions.MANAGER)){
			return Boolean.FALSE;

		}else {
			theChangee.setPermission(permissions);
			//Try to add to database
			try
			{
				tx.begin();
				pm.makePersistent(theChangee);
				tx.commit();
			}
			finally
			{
				if (tx.isActive())
				{
					tx.rollback();
					returner = Boolean.FALSE;
				}
			}
			pm.close();
		}

		return returner;
	}


	/*
	 * (non-Javadoc)
	 * @see hd.client.LoginService#retrieveAllAccounts()
	 */
	@Override
	public List<Account> retrieveAllAccounts() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Account> returner = new ArrayList<Account>();
		Extent<Account> accounts = pm.getExtent(Account.class);

		Iterator<Account> iterator = accounts.iterator();
		while (iterator.hasNext()){
			Account a = iterator.next();
			a.changePassword(a.getPassword(), "");//Remove password
			returner.add(a);
		}

		return returner;

	}

}
