package ks.framework.interfaces;

/**
 * @beta  Ready to go
 */
public interface IStat {

	/**
	 * getWins obtains the number of wins for the plugin
	 * @return int is the total number of wins
	**/
	int getWins();

	/**
	 * getLosses obtains the number of losses for the plugin
	 * @return int is the total number of losses
	**/
	int getLosses();

	/**
	 * getTies obtains the number of ties for the plugin
	 * @return int is the total number of ties
	**/
	int getTies();

	/**
	 * getTotalGames obtains the number of completed games for the plugin
	 * @return int is the total number of completed games
	**/
	int getTotalGames();

	/**
	 * setWins changes the number of wins for the plugin
	 * @param wins is the number of wins to set for the plugin
	 * @return int is the total number of completed games
	**/
	int setWins(int wins);

	/**
	 * setLosses changes the number of losses for the plugin
	 * @param wins is the number of losses to set for the plugin
	 * @return int is the total number of completed games
	**/
	int setLosses(int losses);

	/**
	* setTies changes the number of ties for the plugin
	* @param wins is the number of ties to set for the plugin
	* @return int is the total number of completed games
	**/
	int setTies(int ties);

}