package nov27;

public class SortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Temperature[] temps = new Temperature[3];
		temps[0] = new CelsiusTemperature (16.233);
		temps[1] = new FahrenheitTemperature(10);
		temps[2] = new KelvinTemperature (5);
		
		// show values
		GeneralizedSort.output(temps);
		
		// sort
		GeneralizedSort.sort(temps);
		
		// now show sorted values
		GeneralizedSort.output(temps);
		
	}

}
