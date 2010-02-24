package ks;

/** 
 * Interface to enable Plugin Manager to update stats for given variation.
 */
public interface IUpdateStats {

	/** Declare the wins/losses for given variation. */
	void update(String variation, int numWins, int numLosses);
}
