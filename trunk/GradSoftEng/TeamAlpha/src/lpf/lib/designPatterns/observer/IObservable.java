package lpf.lib.designPatterns.observer;

/**
 * 
 * @author Nik Deapen
 * @since 1.0.0
 */
public interface IObservable {
	
	/**
	 * Adds the observer to the list of observers that are updated 
	 * 	when the state of the observable object changes
	 * @param o - the observer
	 */
	public void addObserver(IObserver o);
	
	/**
	 * Removes the observer from the list of the objects observers
	 * @param o - the observer
	 */
	public void removeObserver(IObserver o);
}
