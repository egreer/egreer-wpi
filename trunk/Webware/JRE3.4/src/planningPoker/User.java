package planningPoker;

/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
/**
 * The user class is used for the storage and manipulation of the user objects
 */
public class User {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;

	@Persistent
	String userName;
	
	@Persistent
	String firstName;
	
	@Persistent
	String lastName;
	
	@Persistent
	String password;
	
	@Persistent
	String permissions;
	
	/**
	 * 
	 * @return	The permissions the user has
	 */
	public String getPermissions() {
		return permissions;
	}

	/**
	 * 
	 * @param permissions	The permissions to give the user
	 */
	public boolean setPermissions(String permissions) {
		if (permissions.equals("admin") || permissions.equals("user")){
			this.permissions = permissions;
			return true;
	
		}else return false;
		
	}

	/**
	 * @param userName	The username
	 * @param firstName	The first name
	 * @param lastName	The last name
	 * @param password	The encrypted password
	 */
	public User(String userName, String firstName, String lastName, String password){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.permissions = "user";
	}
	
	/**
	 * @return	The database key for the User
	 */
	public Long getKey(){
		return key; 
	}

	/**
	 * @return	Returns the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return	returns the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	
	/**
	 * @return	returns the last name of the user
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @return	Returns the encrypted password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param oldPassword	The old password to validate against the stored password
	 * @param password		The new password to change to 
	 * @return				Returns true if the password was set, 
	 * 						Returns false if the password was not set. 
	 */
	public boolean setPassword(String oldPassword, String password) {
		if(this.password.equals(oldPassword)){
			this.password = password;
			return true;
		}
		return false;
	}

}

