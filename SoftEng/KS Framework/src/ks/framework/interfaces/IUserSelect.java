package ks.framework.interfaces;

public interface IUserSelect {

	/** Select user to be part of the private chat group. */
	void selectUser (String name);
	
	/** de-select user to be part of the private chat group. */
	void deselectUser (String name);
	
	/** de-select all users from the private chat group. */
	void deselectAll();
}
