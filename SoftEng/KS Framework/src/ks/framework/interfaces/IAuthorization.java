package ks.framework.interfaces;

/**
 * Authorization interface to enable proper authorization of both ordinary users
 * as well as administrators. 
 * 
 * @author George Heineman
 */
public interface IAuthorization {

	/** Authorize the given user with SHA-1 encoded password. */
	boolean authorize(String who, String pwd);

	/** Authorize the given user with SHA-1 encoded password as an administrator. */
	boolean authorizeAsAdmin(String who, String pwd);

}
