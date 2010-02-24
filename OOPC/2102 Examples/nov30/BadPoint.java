package nov30;

/**
 * This is a bad point, because it has no hashCode method.
 * 
 * Watch what happens when it is used with a Hashtable. This class can only be used
 * as a key if it has BOTH the equals(Object o) method and the hashCode() method.
 * 
 * @author George
 */
public class BadPoint {
	/** X value. */
	int x;
	
	/** Y value. */
	int y;
	
	public BadPoint (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * If you use this form of equals (with BadPoint parameter), then Hashtable won't work.
	 * 
	 * @param bp    The BadPoint against which we are comparing ourself
	 * @return
	 */
	public boolean equals (BadPoint bp) {
		return (x == bp.x) && (y == bp.y); 
	}
	
//	/**
//	 * Determine if two BadPoint objects are the same.
//	 * 
//	 * @param  o    Object against which we are being compared.
//	 */
//	public boolean equals (Object o) {
//		if (o == null) return false;
//		
//		// compare like to like only
//		if (o.getClass() == getClass()) {
//			BadPoint bp = (BadPoint) o;
//			return (x == bp.x) && (y == bp.y); 
//		}		
//		
//		return false;  // nope
//	}
//	
//	/** The dreaded hashcode. */
//	public int hashCode() {
//		return x+y;
//	}
}

