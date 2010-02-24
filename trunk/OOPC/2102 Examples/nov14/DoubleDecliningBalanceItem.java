package nov14;

import java.util.Calendar;

/**
 * Double declining balance method (DDB). This is a form of accelerated depreciation
 * that prescribes twice an annual rate of depreciation twice that of the straight 
 * line method. Under the DDB method, twice the straight line rate is applied each 
 * year to the remaining undepreciated value of the asset.
 * 
 * @author heineman
 */
public class DoubleDecliningBalanceItem extends BusinessItem {
	/** Year when item purchased. */
	int baseYear;
	
	/** Term of depreciation (i.e., number of years). */
	int term;
	
	/** Our current year. */
	int currentYear;
	
	/**
	 * Construct item that formally encodes its value based upon depreciation.
	 * 
	 * @param name       name of item
	 * @param weight     weight of item
	 * @param value      value of item
	 * @param term       term of depreciation period
	 * @param baseYear   bas year in which item was purchased.
	 */
	public DoubleDecliningBalanceItem(String name, double weight, double value, int term, int baseYear) {
		super(name, weight, value);
		
		this.term = term;
		this.baseYear = baseYear;
		
		// Little bit of magic to get the current year.
		Calendar c = Calendar.getInstance();
		currentYear = c.get(Calendar.YEAR);
	}

	/**
	 * Return value of item, depreciated using StraightLine method.
	 * 
	 * @return
	 */
	public double getValue() {
		// same year or earlier? Nothing has depreciated yet.
		if (currentYear <= baseYear) {
			return super.getValue();
		}

		double perct = 2.0 / term;
		double value = super.getValue() * perct;
		if (currentYear == baseYear + 1) {
			return value;
		}

		// if we are within the term limits, then we return fractional value over that period.
		if (currentYear - baseYear <= term) {
			for (int y = baseYear+1; y < currentYear; y++) {
				double diff = value * perct;
				value = value - diff;
			}
			
			return value;
		}
		
		// outside of this term, the item has no value!
		return 0.0;
	}
	

}
