package ks.framework.interfaces;

import java.util.Iterator;

/**
 * @beta Ready to go
 */
public interface IGM_DB {

	/**
	* Function to allow the GameManager to add the game stats
	* @return	true if the game stats are successfully saved; otherwise, false
	*/
	public boolean add (IGame game);


	/**
	* Function to retrun a cursor to the first the game objects
	* @return	the cursor to the first IGame object; null if no games
	*/
	public Iterator<IGame> getGames();

}