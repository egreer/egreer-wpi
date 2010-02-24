package group5.db;

/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @beta
 */
public class IStat implements ks.framework.interfaces.IStat{

	/**establish private variables*/
	private int wins;
	private int losses;
	private int ties;
	
	/**iStat Object Constructor */
	protected IStat(int theWins, int theLosses, int theTies){
		wins = theWins;
		losses = theLosses;
		ties = theTies;
	}
	
	/**
	 * getWins obtains the number of wins for the plugin
	 * @return int is the total number of wins
	**/
	public int getWins(){
		return wins;
	}

	/**
	 * getLosses obtains the number of losses for the plugin
	 * @return int is the total number of losses
	**/
	public int getLosses(){
		return losses;
	}

	/**
	 * getTies obtains the number of ties for the plugin
	 * @return int is the total number of ties
	**/
	public int getTies(){
		return ties;
	}

	/**
	 * getTotalGames obtains the number of completed games for the plugin
	 * @return int is the total number of completed games
	**/
	public int getTotalGames(){
		return wins + losses + ties;
	}

	/**
	 * setWins changes the number of wins for the plugin
	 * @param wins is the number of wins to set for the plugin
	 * @return int is the total number of wins
	**/
	public int setWins(int newWins){
		wins = newWins;
		return wins;
	}

	/**
	 * setLosses changes the number of losses for the plugin
	 * @param wins is the number of losses to set for the plugin
	 * @return int is the total number of losses
	**/
	public int setLosses(int newLosses){
		losses = newLosses;
		return losses;
	}

	/**
	* setTies changes the number of ties for the plugin
	* @param wins is the number of ties to set for the plugin
	* @return int is the total number of ties
	**/
	public int setTies(int newTies){
		ties = newTies;
		return ties;
	}

}