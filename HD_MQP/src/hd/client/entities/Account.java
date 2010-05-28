package hd.client.entities;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Accounts for an employees, and managers to use for login
 * 
 * @author Eric Greer (egreer@alum.wpi.edu)
 * @author Jason Codding (jcodding@alum.wpi.edu)
 * WPI Helpdesk MQP 2009-2010
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Account implements Serializable{
	
	@Persistent
	private static final long serialVersionUID = 57L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long accountID;
	
	@Persistent
	String username;
	
	@Persistent
	String password;
	
	@Persistent
	Long employeeID;

	@Persistent
	Permissions permission;
	
	/** Empty Constructor for Serializable interface */
	public Account(){};

	/** An account must be created with a username and password
	 * Defaults to Employee permissions 
	 * 
	 * @param username	The username of the Employee
	 * @param password	The associated password with the account
	 */
	public Account(String username, String password){
		this(username, password, Permissions.EMPLOYEE);
	}
	
	/**An account must be created with a username and password and 
	 * allows for specification of the permissions 
	 * 
	 * @param username	The username of the Employee
	 * @param password	The associated password with the account
	 * @param permissions Permissions object for the system
	 */
	public Account(String username, String password, Permissions permissions){
		this.username = username;
		this.password = password;
		this.permission = permissions;
	}
	
	/**
	 * @return Returns the username of the account
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return Returns the Password for the account
	 */
	public String getPassword() {
		return password;
	}

	/** Changes the password on the account, must have the old password
	 * 
	 * @param oldPassword	The password currently associated with the account
	 * @param newPassword	The password to change to
	 * @return	Returns True if the password was changed, or False if not
	 */
	public boolean changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(this.password)){
			this.password = newPassword;
			return true;
			
		}else return false;
	}

	/**
	 * @return The key of the Employee object the account is associated with
	 */
	public Long getEmployeeID() {
		return employeeID;
	}

	/** Associates an employee profile with the account
	 * @param employeeID 	The Key of the Employee object 
	 * 						to associate the Account with. 
	 */
	public void setEmployeeID(Long employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return returns the Account's database key
	 */
	public Long getAccountID() {
		return accountID;
	}


	/**
	 * @return The permission object for the Account
	 */
	public Permissions getPermission() {
		return permission;
	}


	/** Changes the permissions of the account
	 * @param permission	The permissions to change to
	 */
	public void setPermission(Permissions permission) {
		this.permission = permission;
	}
}
