package group5.db;


/**
 * @author Eric Greer
 * @author Jason Codding
 * @author Steven Washington 
 * @beta
 */
public class IPlugin implements ks.framework.interfaces.IPlugin{

	
	/**establish private variables*/
	private boolean suspended;
	private String name;
	private IStat statistics;
	
	/** Constructor for IPlugin */
	protected IPlugin(String name, Boolean suspended, IStat statistics){
		this.name = name;
		this.suspended = suspended;
		this.statistics = statistics;
	}
	
	/**
	 * clearStats removes all stats associated with plugin
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean clearStats(){
		boolean statsCleared = false;
		statistics = new IStat(0,0,0);
		return statsCleared;
	}

	/**
	 * getStats obtains the statistics associated with the plugin
	 * @return Istat object that contains the individual statistics
	**/
	public IStat getStats(){
		return statistics;
	}

	/**
	 * getName obtains the name of the current object
	 * @return String returns the name of the plugin
	**/
	public String getName(){
		return name;
	}


	/**
	 * suspendPlugin removes plugin from list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean suspendPlugin(){
		suspended = true;
		if (suspended)return suspended;
		else return false;
	}

	/**
	 * isSuspended checks whether the plugin is in the list of playable variants
	 * @return Boolean representing the status of suspended
	**/
	public boolean isSuspended(){
		return suspended;
	}

	/**
	 * resumePlugin readds the plugin to list of playable variants
	 * @return Boolean representing if the command was successful (true) false otherwise
	**/
	public boolean resumePlugin(){
		suspended = false;
		if (!suspended)return true;
		else return false;
	}

}