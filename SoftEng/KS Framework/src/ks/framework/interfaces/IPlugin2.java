package ks.framework.interfaces;

/**
 * Plugins may have an installed Date that can be returned.
 * 
 * @author George Heineman
 */
public interface IPlugin2 extends IPlugin {
	
	/** Return the date on which the variation was installed. */
	java.util.Date getInstalledDate();
}
