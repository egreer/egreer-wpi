package group5.db;
import java.util.Date;

/**
* @author Eric Greer
* @author Jason Codding
* @author Steven Washington 
* @beta 
*/
public class IGame implements ks.framework.interfaces.IGame{

	/**The winner of this game*/
	private String winner;
	
	/**The unique game ID for this game*/
	private String gameID;
	
	/**An array of strings containing the usernames of the players*/
	private String[] players;
	
	/**The name of the plugin played in this game*/
	private String pluginName;
	
	/**The date this game was played*/
	private Date datePlayed;
	
	/**The seed for this game*/
	private int seed;
	
	
	protected IGame(String winner, String gameID, String[] players, String pluginName, Date datePlayed, int seed)
	{
		this.winner = winner;
		this.gameID = gameID;
		this.players = players;
		this.pluginName = pluginName;
		this.datePlayed = datePlayed;
		this.seed = seed;
	}
	
	/**
	 * Function to get the winner of this game
	 * @return winner The username of the winner of the game
	 */
	public String getWinner(){
		return winner;
	}
	
	/**
	 * Return GameID for unique reference
	 */
	public String getUniqueID(){
		return gameID;
	}
	
	/**
	* Function to get all players' names, who are currently playing the game
	* @return	return all players' names in the array of string
	*/
	public String[] getPlayers(){
		return players;
	}
	
	/**
	* Function to get the name of game
	* @return	name of the game
	*/
	public String getPluginName(){	
		return pluginName;
	}
	
	/**
	* Function to get the date of the game played
	* @return	date of the game played; null if a game has no date
	*/
	public Date getDate(){
		return datePlayed;
	}
	
	/**
	* Function to get a random seed for the game, used for initializing the game
	* @return	the seed for initializing the game
	*/
	public int getSeed(){
		return seed;
	}

}