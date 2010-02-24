package nov27;

/**
 * Represents a Kelvin Temperature value
 * 
 * @author heineman
 */
public class KelvinTemperature extends Temperature {

	/** Known as the lowest temperature in Kelvin. */
	public static final double absoluteZero = 0;

    /**
     * Constructor for kelvin objects.
     *
     * @param kvalue
     */
    public KelvinTemperature (float kvalue) {
       super (kvalue);
    }

    /**
     * Absolute zero for kelvin.
     */
    public double getAbsoluteZero() {
        return KelvinTemperature.absoluteZero;
    }

    /* (non-Javadoc)
     * @see heineman.cs2102.Temperature#toCelsius()
     */
    public double toCelsius() {
        // Add absolute zero value of celsius
        return value + CelsiusTemperature.absoluteZero;
    }

    /* (non-Javadoc)
     * @see heineman.cs2102.Temperature#getUnit()
     */
    public String getUnit() {
        return "Kelvin";
    }

}
