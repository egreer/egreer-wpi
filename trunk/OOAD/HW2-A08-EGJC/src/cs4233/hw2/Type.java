package cs4233.hw2;




/**
 * Enumerated type for the guitar types.
 * 
 * @author gpollice
 */
public enum Type {

	ACOUSTIC, ELECTRIC;

	public String toString()
	{
		switch (this) {
			case ACOUSTIC:
				return "acoustic";
			case ELECTRIC:
				return "electric";
			default:
				return "unspecified";
		}
	}
}
