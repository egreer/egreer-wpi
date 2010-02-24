package nov27;

/**
 * Represents the Rankine Temperature scale (Degrees Rankine).
 *
 * The Rankine temperature scale is to the Fahrenheit scale what the Kelvin scale
 * is to the Celsius scale.
 *
 * @author George
 */
public class RankineTemperature extends Temperature {


    public RankineTemperature (double rankineValue) {
        super(rankineValue);
    }
    
    /**
     * Absolute zero for Rankine Temperature.
     */
    public double getAbsoluteZero() {
        return 0;
    }

    /**
     * Convert to celsius.
     */
    public double toCelsius() {
    	// convert into Fahrenheit and then subtract absolute zero to get
    	// to zero-based scale.
    	double tValue = FahrenheitTemperature.absoluteZero;

       return new FahrenheitTemperature (tValue).toCelsius();
    }

    /** Return Kelvin Units
     */
    public String getUnit() {
        return "Kelvin";
    }

}



