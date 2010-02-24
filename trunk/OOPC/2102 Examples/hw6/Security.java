package hw6;

/**
 * Represents a security being monitored.
 * 
 * @author heineman
 *
 */
public class Security {

	/** Identifier. */
	private String id;
	
	/** Sector in industry. */
	private String sector;
	
	/** Rating for this security. */
	private String rating;

	/**
	 * 
	 * @param id    Identifier for Security.
	 * @param sic   Sector within which security is found
	 * @param spr   Standard and Poor's rating for the security
	 */
	public Security(String id, String sic, String spr) {
		this.id = id;
		this.sector = sic;
		this.rating = spr;
	}

	/** Return identifier as the String representation of this Security. */
	public String toString () {
		return id;
	}
	
	/**
	 * Consider two securities as being equal if they have the same security id.
	 */
	public boolean equals (Object o) {
		if (o == null) return false;
		
		if (o instanceof Security) {
			Security other = (Security) o;
			return other.id.equals (id);			
		}
		
		return false;   // NOPE
	}

	public Object getId() {
		return id;
	}

	public String getSector() {
		return sector;
	}
	
	
	
}
