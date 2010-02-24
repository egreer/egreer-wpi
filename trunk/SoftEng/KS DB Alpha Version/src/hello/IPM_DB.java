package hello;


import java.util.Iterator;

/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @alpha  WARNING These are alpha interfaces
 */
public class IPM_DB{
	/**
	 * addPlugin creates a new plugin in the database
	 * @param pluginName is the file name of the plugin (must be unique)
	 * @return IPlugin is the object representing the plugin, null if command fails
	 **/
	public IPlugin addPlugin(String pluginName){
		return null;
	}

	/**
	 * getPlugin obtains the plugin with the given name
	 * @param pluginName is the file name of the plugin
	 * @return IPlugin is the object representing the plugin, null if command fails or plugin doesn't exist
	**/
	public IPlugin getPlugin(String pluginName){
		return null;
	}

	/**
	 * removePlugin removes the plugin from the database
	 * @param pluginName is the file name of the plugin (must be unique)
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public Boolean removePlugin(String pluginName){
		boolean pluginRemoved = false;
		return pluginRemoved;
	}

	/**
	 * listPlugins allows for incrementing through the entire list of plugins
	 * @return IPluginCursor is an object that represents the first plugin in the database
	**/
	public Iterator<IPlugin> listPlugins(){
		return null;
	}

}