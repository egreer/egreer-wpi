package nov27;

/**
 * Represents a Temperature Value.
 * 
 * Note that abstract classes can hold additional method implementations as required. 
 * For a class to be abstract, it must either (a) contain an abstract method itself; 
 * or (b) claim to implement an interface yet not provide a method from that interface.
 * 
 * @author heineman
 */
public abstract class Temperature implements Comparable {
    /** Value. */
    protected double value;

    /**
     * Return absolute zero in appropriate unit.
     * @return   value of absolute zero.
     */
    public abstract double getAbsoluteZero();

    /**
     * Convert existing temperature value into Celsius.
     * 
     * @return temperature in Celsius units.
     */
    public abstract double toCelsius ();

    /**
     * Provide default Temperature constructor, accessed by subclasses, which
     * protects against absolute zero.
     * 
     * @param value   desired temperature
     */
    public Temperature (double value) {
    	// Wait until Tuesday to see the better way of dealing with this.
    	if (value < getAbsoluteZero()) {
    		System.out.println ("Attempting to set too low a value.");
    		System.exit(1);
    	}
    	
    	this.value = value;
    }
    
    /**
     * Provides Comparable interface.
     * 
     * if (x.compareTo(y)) returns  a negative, then x is "less than" y
     * if (x.compareTo(y)) returns  zero      , then x is "equals" y
     * if (x.compareTo(y)) returns  a positive, then x is "greater than" y
     * 
     * Convert into Temperature prior to comparing. Note that Exceptions may 
     * result if null or non-Temperature object passed in.
     * 
     * @param o    Temperature object against which to compare.
     * @return     negative if less than argument, zero if the same, and positive if greater.
     */
	public int compareTo(Object o) {
		Temperature other = (Temperature) o;
		
		// deal with mixed Temperatures
		double myCelsius = toCelsius();
		double otherCelsius = other.toCelsius();
		
		// NOTE that the following will not work. Do You See Why?
		// return (int) (myCelsius - otherCelsius);
		
		if (myCelsius == otherCelsius) {
			return 0;
		} else if (myCelsius < otherCelsius) {
			return -1;
		} else {
			return +1;   // last case to work with.
		}
	}

    /**
     * Convert value into String.
     */
    public String toString () {
        return "" + value + " " + getUnit();
    }

    /**
     * Return the unit as a string.
     */
    public abstract String getUnit();

 }

