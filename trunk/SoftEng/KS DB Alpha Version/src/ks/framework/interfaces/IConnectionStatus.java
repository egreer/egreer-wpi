package ks.framework.interfaces;

/**
 * Exposes on-line status of individual users. 
 * 
 * @author George Heineman
 */
public interface IConnectionStatus {
	/** Determines if user is currently online. */
	boolean isOnline (String user);
}
