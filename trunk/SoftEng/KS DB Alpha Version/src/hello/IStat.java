package hello;


/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @alpha  WARNING These are alpha interfaces
 */
public class IStat {

	/**
	 * getWins obtains the number of wins for the plugin
	 * @return int is the total number of wins
	**/
	public int getWins(){
		int wins = -1;
		return wins;
	}

	/**
	 * getLosses obtains the number of losses for the plugin
	 * @return int is the total number of losses
	**/
	public int getLosses(){
		int losses = -1;
		return losses;
	}

	/**
	 * getTies obtains the number of ties for the plugin
	 * @return int is the total number of ties
	**/
	public int getTies(){
		int ties = -1;
		return ties;
	}

	/**
	 * getTotalGames obtains the number of completed games for the plugin
	 * @return int is the total number of completed games
	**/
	public int getTotalGames(){
		int totalGames = -1;
		return totalGames;
	}

	/**
	 * setWins changes the number of wins for the plugin
	 * @param wins is the number of wins to set for the plugin
	 * @return int is the total number of completed games
	**/
	public int setWins(int wins){
		return wins;
	}

	/**
	 * setLosses changes the number of losses for the plugin
	 * @param wins is the number of losses to set for the plugin
	 * @return int is the total number of completed games
	**/
	public int setLosses(int losses){
		return losses;
	}

	/**
	* setTies changes the number of ties for the plugin
	* @param wins is the number of ties to set for the plugin
	* @return int is the total number of completed games
	**/
	public int setTies(int ties){
		return ties;
	}

}