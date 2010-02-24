package ks.framework.interfaces;

/** 
 * There may be some client-side components that need to know
 * the name of the current logged-in user.
 * 
 * If this is the case, then they provide this interface and it 
 * will be provided to them.
 * 
 * @author George Heineman
 */
public interface IUserContext {

	/** Once client has successfully logged in, name is valid. */
	public void setUserName(String name);
}
