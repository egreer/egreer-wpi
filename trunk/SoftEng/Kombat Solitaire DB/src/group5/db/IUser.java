package group5.db;

import java.util.Date;

/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * 
 * The IUser interface has no setXXX method that returns a value.
 * The reason is that changes are not made permanent until the IUM_DB
 * method updateUser(IUser iu) is invoked.
 * 
 * @beta
 */
public class IUser implements ks.framework.interfaces.IUser{
	
	//Private variables for the IUser Object 
	private String username;
	private String password;
	private Date creationDate;
	private Date lastLogin;
	private int wins;
	private int losses;
	private int ties;
	private int rating;
	private boolean adminStatus;
	private String icon;
	private Date blockDate;
	
	/** Default constructor for a new IUser object for a new user*/
	protected IUser(String username, String password){
		this.username = username;
		this.password = password;
		this.creationDate = new Date();
		setLastLogin(null);
		setWins(0);
		setLosses(0);
		setTies(0);
		setRating(0);
		this.adminStatus = false;	
		this.icon = null;
		this.blockDate = null;
	}
	
	/** Constructor for a new IUser object for an existing user */
	protected IUser(String username, String password, Date creationDate, Boolean adminStatus){
		this.username = username;
		this.password = password;
		this.creationDate = creationDate;
		this.adminStatus = adminStatus;
	}
	/**
	 * Return the name of the user.
	 * @return   name of user.
	 */
	public String getUserName(){
		return username;
	}
	
	/**
	 * Function returns the total number of wins the user has achieved
	 * @return total number of wins
	**/
	public int getWins(){
		return wins;
	}

	/**
	 * Function returns the total number of losses the user has achieved
	 * @return total number of losses
	**/
	public int getLosses(){
		return losses;
	}

	/**
	 * Function returns the total number of ties the user has achieved
	 * @return total number of ties
	**/
	public int getTies(){
		return ties;
	}

	/**
	 * Function returns the total number of games the user has played
	 * @return total number of games played
	**/
	public int getNumberOfGamesPlayed(){
		return wins + losses + ties;
	}

	/**
	 * Function determines if the given password matches the one stored for the user
	 * @param password String representing provided password of the user
	 * @return boolean true if password matched, false otherwise
	 **/
	public boolean confirmPassword(String password){
		return this.password.equals(password);
	}

	/**
	 * Function sets the password for the user
	 * @param oldPassword String representing the encrypted key of the old password
	 * @param newPassword String representing the encrypted key of the new password
	**/
	public void setPassword(String oldPassword, String newPassword){
		
		if(confirmPassword(oldPassword)){
			this.password = newPassword;
		}
		return;
	}                                                              

	/**
	 * Function returns the rating for the user
	 * @return user's current rating
	**/
	public int getRating(){
		return rating;
	}

	/**
	 * Function returns the name of icon used by the user
	 * @return String the name of the icon, null if not set
	**/
	public String getIcon(){
		return icon;
	}
	
	/**
	 * Function returns the Administrator status of the user
	 * @return boolean representing if the user is an administrator (true), false otherwise 
	 **/
	public boolean isAnAdmin(){
		return adminStatus;
	}

	/**
	 * Function returns the blocked status of the user
	 * @return boolean representing if the user is blocked (true), false otherwise 
 	**/
	public boolean isBlocked(){
		return blockDate.after(new Date());
	}

	/**
	 * Function to set player's new rating.
	 * 
	 * @param rating
	 */
	public void setRating (int rating){
		this.rating = rating;
		return;
	}
	
	/**
	 * Function sets the icon used by the user
	 * @param iconName String representing the new value for the icon to be stored
	**/
	public void setIcon(String iconName){
		this.icon = iconName;
		return;
	}

	/**
	 * Function sets the date the user is blocked until
	 * @param blockedDate Date representing the date the user will become unblocked
	**/
	public void setBlockedUntil(Date unBlockedDate){
		
		//Checks to see if the block date is after the current date  
		if (unBlockedDate.after(new Date())){
			this.blockDate = unBlockedDate;
		}
		return;
	}

	/**
	 * Function returns the date the user will become unblocked
	 * @return Date representing the date the user will be unblocked, null if not blocked
	**/
	public Date getBlockedUntil(){
		return blockDate;
	}

	/**
	 * Function returns the date the user account was created
	 * @return Date representing the date the user joined the system
	**/
	public Date getCreationDate(){
		return creationDate;
	}

	/**
	 * Function returns the date the user last logged in
	 * @return Date representing the date the user was last on the system, null if never on system
	**/
	public Date getLastLogin(){
		return lastLogin;
	}
	
	/**
	 * Function sets the number of wins a user has achieved
	 * @param numWins Integer representing the new value of wins to be stored
	**/
	public void setWins(int numWins){
		this.wins = numWins;
		return;
	}

	/**
	 * Function sets the number of losses a user has achieved
	 * @param numLosses Integer representing the new value of losses to be stored
	**/
	public void setLosses(int numLosses){
		this.losses = numLosses;
		return;
	}

	/**
	 * Function sets the number of ties a user has achieved
	 * @param numTies Integer representing the new value of ties to be stored
	**/
	public void setTies(int numTies){
		this.ties = numTies;
		return;
	}
	
	/**
	 * Function sets the date the user last logged in
	**/
	public void setLastLogin(Date d){
		this.lastLogin = d;
		return;
	}
}