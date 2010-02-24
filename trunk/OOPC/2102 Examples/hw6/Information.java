package hw6;

public class Information {

	/** Maintains low and high values. */
	float low;
	
	/** High value. */
	float high;
	
	/** Opening price. */
	float open;
	
	/** Closing price. */
	float close;
	
	/** Total number of sold securities. */
	long volume;
	
	/**
	 * Construct Value object.
	 * 
	 * @param open    opening price
	 */
	public Information (float open) {
		this.low = open;
		this.high = open;
		this.open = open;
	}
	
	/** Representation of stock value in terms of [low,high]. */
	public String toString () {
		return "[" + low + "," + high + "]";
	}
	
	/**
	 * Update the low value for this Value
	 * 
	 * @param l  the new low value.
	 */
	public void setLow (float l) {
		if (l < this.low) {
			this.low = l;
		}
	}
	
	/**
	 * Return the low for this value.
	 * 
	 * @return   low value
	 */
	public float getLow() {
		return low;
	}
	
	/**
	 * Update the high value for this Value
	 * 
	 * @param h  the new low value.
	 */
	public void setHigh (float h) {
		if (h > this.high) {
			this.high= h;
		}
	}
	
	/**
	 * Return the high for this value.
	 * 
	 * @return   high value
	 */
	public float getHigh() {
		return high;
	}

	/**
	 * Return improvement as a float percentage.
	 * 
	 * @return
	 */
	public float getImprovement() {
		return (close - open) / open;
	}
	
	/**
	 * Set the closing price.
	 * 
	 * @param close
	 */
	public void setClose(float close) {
		this.close = close;
	}
	
	/** 
	 * Return the closing price.
	 * @return
	 */
	public float getClose() {
		return close;
	}

	/** Get the open price. */
	public float getOpen() {
		return open;
	}

	/**
	 * Increment sales by total sold on particular day.
	 * 
	 * @param volume
	 */
	public void aggregateSales(long volume) {
		this.volume += volume;		
	}
	
	/**
	 * Return volume of sales.
	 * @return
	 */
	public long getVolume() {
		return volume;
	}
	
}
