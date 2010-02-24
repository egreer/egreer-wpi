package group5.db;

import java.util.Iterator;
import ks.framework.interfaces.IPlugin;


/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @beta
 */
public class IPM_DB implements ks.framework.interfaces.IPM_DB{
	/**
	 * addPlugin creates a new plugin in the database
	 * @param pluginName is the file name of the plugin (must be unique)
	 * @return IPlugin is the object representing the plugin, null if command fails
	 **/
	public IPlugin addPlugin(String pluginName){
		return null;
	}

	/** Update stats for the plugin. If not in admin mode, then you can safely ignore
	 * updates that are admin-specific.
	 * */
	public void updatePlugin (IPlugin plugin){
		return;
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
	public boolean removePlugin(String pluginName){
		boolean pluginRemoved = false;
		return pluginRemoved;
	}

	/**
	 * listPlugins allows for incrementing through the entire list of plugins
	 * @return IPluginCursor is an object that represents the first plugin in the database
	**/
	public Iterator<IPlugin> listPlugins(){
		Iterator<IPlugin> pointer = null;
		return pointer;
	}

}