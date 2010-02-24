package ks.framework.interfaces;

/**
 * Interface proposed by TableManagerGUI for retrieving icon string name for a player.
 * 
 * @author Frank Williams
 * @author George Heineman
 */
public interface IUserIcon {
	
	/** Return the name of the given user's icon. */
	String getUserIcon (String userName);
	
}
