package lpf.lib.designPatterns.observer;

/**
 * 
 * @author Nik Deapen
 * @since 1.0.0
 */
public interface IObserver {
	/**
	 * Tells the observer an object has been updated
	 * @param o - the objec that has been updated.
	 */
	public void objectUpdated(Object o);
}
