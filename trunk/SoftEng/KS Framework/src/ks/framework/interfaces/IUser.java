package ks.framework.interfaces;

import java.util.Date;

/**
 * The IUser interface has no setXXX method that returns a value.
 * The reason is that changes are not made permanent until the IUM_DB
 * method updateUser(IUser iu) is invoked.
 * 
 * @beta ready to go
 */
public interface IUser {

	/**
	 * Return the name of the user.
	 * @return   name of user.
	 */
	public String getUserName();
	
	/**
	 * Function returns the total number of wins the user has achieved
	 * @return total number of wins
	**/
	public int getWins();

	/**
	 * Function returns the total number of losses the user has achieved
	 * @return total number of losses
	**/
	public int getLosses();

	/**
	 * Function returns the total number of ties the user has achieved
	 * @return total number of ties
	**/
	public int getTies();

	/**
	 * Function returns the total number of games the user has played
	 * @return total number of games played
	**/
	public int getNumberOfGamesPlayed();

	/**
	 * Function determines if the given password matches the one stored for the user
	 * @param password String representing provided password of the user
	 * @return boolean true if password matched, false otherwise
	 **/
	public boolean confirmPassword(String password);

	/**
	 * Function sets the password for the user
	 * @param oldPassword String representing the encrypted key of the old password
	 * @param newPassword String representing the encrypted key of the new password
	**/
	public void setPassword(String oldPassword, String newPassword);

	/**
	 * Function returns the rating for the user
	 * @return user's current rating
	**/
	public int getRating();

	/**
	 * Function returns the name of icon used by the user
	 * @return String the name of the icon, null if not set
	**/
	public String getIcon();
	
	/**
	 * Function returns the Administrator status of the user
	 * @return boolean representing if the user is an administrator (true), false otherwise 
	 **/
	public boolean isAnAdmin();

	/**
	 * Function returns the blocked status of the user
	 * @return boolean representing if the user is blocked (true), false otherwise 
 	**/
	public boolean isBlocked();

	/**
	 * Function to set player's new rating.
	 * 
	 * @param rating
	 */
	public void setRating (int rating);
	
	/**
	 * Function sets the icon used by the user
	 * @param iconName String representing the new value for the icon to be stored
	**/
	public void setIcon(String iconName);

	/**
	 * Function sets the date the user is blocked until
	 * @param blockedDate Date representing the date the user will become unblocked
	**/
	public void setBlockedUntil(Date unBlockedDate);

	/**
	 * Function returns the date the user will become unblocked
	 * @return Date representing the date the user will be unblocked, null if not blocked
	**/
	public Date getBlockedUntil();

	/**
	 * Function returns the date the user account was created
	 * @return Date representing the date the user joined the system
	**/
	public Date getCreationDate();

	/**
	 * Function returns the date the user last logged in
	 * @return Date representing the date the user was last on the system, null if never on system
	**/
	public Date getLastLogin();
	
	/**
	 * Function sets the number of wins a user has achieved
	 * @param numWins Integer representing the new value of wins to be stored
	**/
	public void setWins(int numWins);

	/**
	 * Function sets the number of losses a user has achieved
	 * @param numLosses Integer representing the new value of losses to be stored
	**/
	public void setLosses(int numLosses);

	/**
	 * Function sets the number of ties a user has achieved
	 * @param numTies Integer representing the new value of ties to be stored
	**/
	public void setTies(int numTies);
	
	/**
	 * Function sets the date the user last logged in
	**/
	public void setLastLogin(Date d);
}