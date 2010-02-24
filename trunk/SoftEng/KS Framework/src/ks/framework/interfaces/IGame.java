package ks.framework.interfaces;

import java.util.Date;

// NOTE: More is to be added here, regarding stats.
/**
 * @beta  Ready to Go
 */
public interface IGame {

	public String getWinner();
	
	/**
	 * Return GameID for unique reference
	 */
	public String getUniqueID(); 
	
	/**
	* Function to get all players' names, who are currently playing the game
	* @return	return all players' names in the array of string
	*/
	public String[] getPlayers();
	
	/**
	* Function to get the name of game
	* @return	name of the game
	*/
	public String getPluginName();
	
	/**
	* Function to get the date of the game played
	* @return	date of the game played; null if a game has no date
	*/
	public Date getDate();
	
	/**
	* Function to get a random seed for the game, used for initializing the game
	* @return	the seed for initializing the game
	*/
	public int getSeed();


}