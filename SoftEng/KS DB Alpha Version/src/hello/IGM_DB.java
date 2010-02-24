package hello;


import java.util.Iterator;

/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @alpha  WARNING These are alpha interfaces
 */
public class IGM_DB {

	/**
	* Function to allow the GameManager to save the game stats
	* @return	true if the game stats are successfully saved; otherwise, false
	*/
	public boolean updateGame(IGame game){
		boolean updated = false;
		return updated;
	}

	/**
	* Function to allow the GameManager to retrieve a IGame object
	* 
	* TODO: HACK: I Added this uniqGameId but not sure what it is.
	* 
	* @return	the IGame object containing the game stats; null if game not found
	*/
	public IGame getGame(String uniqGameID){
		return null;
	}

	/**
	* Function to retrun a cursor to the first the game objects
	* @return	the cursor to the first IGame object; null if no games
	*/
	public Iterator<IGame> getGames(){
		return null;
	}

}