package ks;


import java.util.Date;


/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @alpha  WARNING These are alpha interfaces
 */
public class IUser {

	/**
	 * Return the name of the user.
	 * @return   name of user.
	 */
	public String getUserName(){
		String username= null;
		return username;
	}
	
	
	/**
	 * Function returns the total number of wins the user has achieved
	 * @return Int total number of wins
	**/
	public int getWins(){
		int wins = -1;
		return wins;
	}

	/**
	 * Function returns the total number of loses the user has achieved
	 * @return Int total number of losses
	**/
	public int getLoss(){
		int losses = -1;
		return losses;
	}

	/**
	 * Function returns the total number of ties the user has achieved
	 * @return Int total number of ties
	**/
	public int getTies(){
		int ties = -1; 
		return ties;
	}

	/**
	 * Function returns the total number of games the user has played
	 * @return Int total number of games played
	**/
	public int getNumberOfGamesPlayed(){
		int numberGamesPlayed = -1;
		return numberGamesPlayed;
	}

	/**
	 * Function sets the number of wins a user has achieved
	 * @param numWins Integer representing the new value of wins to be stored
	 * @return boolean representing if set of number of wins was successful (true), false otherwise
	**/
	public boolean setWins(int numWins){
		return false;
	}

	/**
	 * Function sets the number of losses a user has achieved
	 * @param numLosses Integer representing the new value of losses to be stored
	 * @return boolean representing if set of number of losses was successful (true), false otherwise
	**/
	public boolean setLosses(int numLosses){
		return false;
	}

	/**
	 * Function sets the number of ties a user has achieved
	 * @param numTies Integer representing the new value of ties to be stored
	 * @return boolean representing if set of number of ties was successful (true), false otherwise
	**/
	public boolean setTies(int numTies){
		return false;
	}

	/**
	 * Function sets the username for the user
	 * @param newUserName String representing the new value for the username to be stored
	 * @return boolean representing if set of username was successful (true), false otherwise
	**/
	public boolean setUserName(String newUserName){
		return false;
	}

	/**
	 * Function determines if the given password matches the one stored for the user
	 * @param password String representing provided password of the user
	 * @return boolean true if password matched, false otherwise
	**/
	public boolean confirmPassword(String password){
		return false;
	}

	/**
	 * Function sets the password for the user
	 * @param oldPassword String representing the encrypted key of the old password
	 * @param newPassword String representing the encrypted key of the new password
	 * @return boolean representing if set of password was successful (true), false otherwise
	**/
	public void setPassword(String oldPassword, String newPassword){
		return;
	}

	/**
	 * Function returns the rating for the usre
	 * @return Int user's current rating
	**/
	public int getRating(){
		int rating = -1;
		return rating;
	}

	/**
	 * Function returns the name of icon used by the user
	 * @return String the name of the icon, null if not set
	**/
	public String getIcon(){
		String iconName = null;
		return iconName;
	}

	/**
	 * Function to set player's new rating.
	 * 
	 * @param rating
	 */
	public void setRating (int rating){
		return;
	}
	
	/**
	 * Function sets the icon used by the user
	 * @param iconName String representing the new value for the icon to be stored
	 * @return boolean representing if set of icon was successful (true), false otherwise
	**/
	public boolean setIcon(String iconName){
		return false;
	}

	/**
	 * Function returns the Administrator status of the user
	 * @return boolean representing if the user is an administrator (true), false otherwise 
	**/
	public boolean isAnAdmin(){
		return false;
	}

	/**
	 * Function returns the blocked status of the user
	 * @return 0 is not blocked 
	**/
	public int isBlocked(){
		int blocked = 0;
		return blocked;
	}

	/**
	 * Function sets the date the user is blocked until
	 * @param blockedDate Date representing the date the user will become unblocked
	 * @return boolean representing if set of the block was successful (true), false otherwise
	**/
	public boolean setBlockedUntil(Date unBlockedDate){
		return false;
	}

	/**
	 * Function returns the date the user will become unblocked
	 * @return Timestamp representing the date the user will be unblocked, null if not blocked
	**/
	public Date getBlockedUntil(){
		Date blockedDate = null;
		return blockedDate;
	}

	/**
	 * Function returns the date the user account was created
	 * @return Timestamp representing the date the user joined the system
	**/
	public Date getCreationDate(){
		Date createDate = null;
		return createDate;
	}

	/**
	 * Function returns the date the user last logged in
	 * @return Timestamp representing the date the user was last on the system, null if never on system
	**/
	public Date getLastLogin(){
		Date createDate = null;
		return createDate;
	}

}