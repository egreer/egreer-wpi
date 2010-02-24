package hello;

/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @alpha  WARNING These are alpha interfaces
 */
public class IPlugin{

	/**
	 * clearStats removes all stats associated with pulgin
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean clearStats(){
		boolean statsCleared = false;
		return statsCleared;
	}

	/**
	 * getStats obtains the statistics associated with the plugin
	 * @return Istat object that contains the individual statistics
	**/
	public IStat getStats(){
		return null;
	}

	/**
	 * getName obtains the name of the current object
	 * @return String returns the name of the plugin
	**/
	public String getName(){
		String name = null;
		return name;
	}


	/**
	 * suspendPlugin removes plugin from list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean suspendPlugin(){
		boolean suspended = false;
		return suspended;
	}

	/**
	 * isSuspended checks whether the plugin is in the list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean isSuspended(){
		boolean suspended = false;
		return suspended;		
	}

	/**
	 * resumePlugin readds the plugin to list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean resumePlugin(){
		boolean resumed = false; 
		return resumed;
	}

}