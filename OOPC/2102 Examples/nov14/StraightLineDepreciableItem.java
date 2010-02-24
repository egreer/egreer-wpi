package nov14;

import java.util.Calendar;

/**
 * A 
 * 
 * "Straight line depreciation (SL). The simplest schedule, so-called "straight line" 
 * depreciation spreads depreciation expenses evenly across an asset’s depreciable life: 
 * A $100 asset fully depreciated over 5 years (and having no residual value) would allow
 * the owner to claim $20 depreciation expense each year for five years. Other depreciation
 * schedules call for different percentages in each year, usually "accelerating" depreciation
 * by charging relatively more in early years, and relatively less in later years."
 * 
 * http://www.solutionmatrix.com/depreciation-schedule.html
 * 
 * @author heineman
 *
 */
public class StraightLineDepreciableItem extends BusinessItem {

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
	public StraightLineDepreciableItem (String name, double weight, double value, int term, int baseYear) {
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
		
		// if we are within the term limits, then we return fractional value over that period.
		if (currentYear - baseYear < term) {
			return super.getValue() / term;
		}
		
		// outside of this term, the item has no value!
		return 0.0;
	}
	

}
