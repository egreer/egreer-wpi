package ks.framework.interfaces;
/**
 * @beta ready to go
 */
public interface IPlugin{

	/**
	 * clearStats removes all stats associated with plugin
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean clearStats();

	/**
	 * getStats obtains the statistics associated with the plugin
	 * @return Istat object that contains the individual statistics
	**/
	public IStat getStats();

	/**
	 * getName obtains the name of the current object
	 * @return String returns the name of the plugin
	**/
	public String getName();


	/**
	 * suspendPlugin removes plugin from list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean suspendPlugin();

	/**
	 * isSuspended checks whether the plugin is in the list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean isSuspended();

	/**
	 * resumePlugin readds the plugin to list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean resumePlugin();

}