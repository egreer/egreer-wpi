package ebc.model;

/**
 * Whenever the model changes, it contacts listeners who have previously registered with
 * the value object to alert them of the change.
 * <p>
 * In most cases, the update event would be tied to an individual model element, as opposed
 * to the case here where we just claim that the model has updated in some fashion.
 * 
 * @author George Heineman
 */
public interface IModelUpdated {
	
	/** The value object has changed its value. */
	public void modelChanged ();
}
