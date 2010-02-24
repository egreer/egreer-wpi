package nov27;

/**
 * Represents a Celsius temperature
 *
 * http://en.wikipedia.org/wiki/Celsius
 * 
 * @author heineman
 */
public class CelsiusTemperature extends Temperature {
	/** Known as the lowest temperature in Celsius. */
	public static final double absoluteZero = -273.15;

    /**
     * Constructor for celsius objects.
     *
     * @param celsiusValue
     */
    public CelsiusTemperature (double celsiusValue) {
        super (celsiusValue);
    }

    /**
     * Return value as celsius.
     * 
     * @return     value of temperature in Celsius.
     */
    public double toCelsius() {
        return value;
    }

    /**
     * Return absolute zero in celsius.
     */
    public double getAbsoluteZero() {
        return CelsiusTemperature.absoluteZero;
    }

    /**
     * Return Celsius
     */
    public String getUnit() {
        return "Celsius";
    }


}