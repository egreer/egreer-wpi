package ebc.model;

/**
 * Defines a read-only interface for accessing model elements.
 * 
 * @author George Heineman
 */
public interface IValue {
	
	/** Retrieve value without enabling update rights. */
	int getValue();
	
	/** Return minimum allowed value. */
	int getMinimum ();
	
	/** Return maximum allowed value. */
	int getMaximum ();
}
