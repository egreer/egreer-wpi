package hw6;

/**
 * Represent information about an industry sector.
 * 
 * @author heineman
 */
public class Industry {

	/** Name of industry type. */
	String name;
	
	/** Total of securities sold in this industry. */
	long total;
	
	/** number of securities in this bin. */
	int number;
	
	/** Sum of the percentage of returns. */
	float sumReturns;
	
	/**
	 * Default constructor. 
	 * @param name     industry type
	 */
	public Industry (String name) {
		this.name = name;
		number = 0;
	}
	
	/**
	 * Update information about this industry.
	 * 
	 * @param openingCost          opening Price at beginning of range
	 * @param closingCost          closing Price at end of range
	 * @param numSold              number of lots sold
	 */
	public void addEntry(float openingPrice, float closingPrice, long numSold) {
		total += numSold;
		number++;
		
		
	}
	
	/**
	 * Return the average profit/loss within this industry, over all entries.
	 * 
	 * @return
	 */
	public float getAverageProfit () {
		return sumReturns / number;
	}

}
