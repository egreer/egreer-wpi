package ks.framework.interfaces;

/**
 * Exposed notion of a number of ties.
 * 
 * @author George Heineman
 */
public interface IUpdateStats2 extends IUpdateStats {

	/**
	 * Declare the wins/losses for given variation. 
	 *
	 * @deprecated A variation can have no ties. This is incorrect functionality.
	 */
	void update(String variation, int numWins, int numLosses, int numTies);
}
