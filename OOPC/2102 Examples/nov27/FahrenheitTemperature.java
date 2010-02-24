package nov27;

/**
 * Represents a Fahrenheit Temperature
 * 
 * @author heineman
 */
public class FahrenheitTemperature extends Temperature {

	/** Known as the lowest temperature in Fahrenheit. */
	public static final double absoluteZero = -459.67;

    /**
     * Create new fahrenheit temperature
     * @param fahrenheitValue
     */
    public FahrenheitTemperature (double fahrenheitValue) {
        super (fahrenheitValue);
    }

    /**
     * Return absolute zero in fahrenheit.
     */
    public double getAbsoluteZero() {
        return FahrenheitTemperature.absoluteZero;
    }

    /**
     * Convert into celsius.
     */
    public double toCelsius() {
        return (value-32.0)*5.0/9.0;
    }

    /**
     * Return Fahrenheit
     */
    public String getUnit() {
        return "Fahrenheit";
    }

}