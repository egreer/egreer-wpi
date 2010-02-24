package group5.db;

import java.util.Iterator;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import ks.framework.interfaces.IGame;

/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @beta
 */
public class IGM_DB implements ks.framework.interfaces.IGM_DB{
	private String d = ", ";  
	
	/**
	* Function to allow the GameManager to add the game stats
	* @return	true if the game stats are successfully saved; otherwise, false
	*/
	public boolean add(IGame game){
		Connection connection = null; 
				
		//Loop while no connection exists
		while (connection == null){
			connection = DBComponent.DBConnect();
		}
		
		String id = game.getUniqueID();
		String name = game.getPluginName();
		int seed = game.getSeed();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = game.getDate();
		String pdate = dateFormat.format(date);

		
		
		String command = "INSERT INTO games VALUES('"+ id + "'" + d + pdate + d + "'" + name + "'" + d + seed + ")";
		
		try{
		connection.prepareStatement(command).executeUpdate();

		}catch (Exception e){
			return false;
		}
		
		return true;
	}

   
	/**
	* Function to return a cursor to the first the game objects
	* @return	the cursor to the first IGame object; null if no games
	*/
	public Iterator<IGame> getGames(){
		Iterator<IGame> pointer = null;
		return pointer;
	}

}