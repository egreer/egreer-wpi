package hello;


import java.util.Date;

// NOTE: More is to be added here, regarding stats.
/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @alpha  WARNING These are alpha interfaces
 */
public class IGame {

	
	/**
	 * Return GameID for unique reference
	 */
	public String getUniqueID(){
		String gameID = null;
		return gameID;
	}
	
	/**
	* Function to get all players' names, who are currently playing the game
	* @return	return all players' names in the array of string
	*/
	public String[] getPlayers(){
		return null;
	}
	
	/**
	* Function to get the name of game
	* @return	name of the game
	*/
	public String getPluginName(){
		String pluginName = null;
		return pluginName;
	}
	
	/**
	* Function to get the date of the game played
	* @return	date of the game played; null if a game has no date
	*/
	public Date getDate(){
		Date datePlayed = null; 
		return datePlayed;
	}
	
	/**
	* Function to get a random seed for the game, used for initializing the game
	* @return	the seed for initializing the game
	*/
	public int getSeed(){
		int seed = 0;
		return seed;
	}


}