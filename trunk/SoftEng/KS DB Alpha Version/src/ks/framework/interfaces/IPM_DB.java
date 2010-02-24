package ks.framework.interfaces;

import java.util.Iterator;

/**
 * @beta ready to go
 */
public interface IPM_DB{
	/**
	 * addPlugin creates a new plugin in the database
	 * @param pluginName is the file name of the plugin (must be unique)
	 * @return IPlugin is the object representing the plugin, null if command fails
	 **/
	IPlugin addPlugin(String pluginName);

	/** Update stats for the plugin. If not in admin mode, then you can safely ignore
	 * updates that are admin-specific.
	 * */
	void updatePlugin (IPlugin plugin);
	
	/**
	 * getPlugin obtains the plugin with the given name
	 * @param pluginName is the file name of the plugin
	 * @return IPlugin is the object representing the plugin, null if command fails or plugin doesn't exist
	**/
	IPlugin getPlugin(String pluginName);

	/**
	 * removePlugin removes the plugin from the database
	 * @param pluginName is the file name of the plugin (must be unique)
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	boolean removePlugin(String pluginName);

	/**
	 * listPlugins allows for incrementing through the entire list of plugins
	 * @return IPluginCursor is an object that represents the first plugin in the database
	**/
	Iterator<IPlugin> listPlugins();

}