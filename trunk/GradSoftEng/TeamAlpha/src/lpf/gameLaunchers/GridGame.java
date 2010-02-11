package lpf.gameLaunchers;

/**
 * Creates a generic GridGame that is runnable
 * 
 * @author Nik Deapen
 * @since 1.0.0
 */
public abstract class GridGame implements Runnable {
	
	private final String name;
	
	/**
	 * Creates a Grid Game
	 * @param name - the name of the game
	 * @pre (name != null)
	 */
	public GridGame(String name){
		if (name == null)
			throw new IllegalArgumentException();
		this.name = name;		
	}
	
	/**
	 * 
	 * @return the name of the game
	 */
	public String getName(){
		return name;
	}
}