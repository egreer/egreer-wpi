package nov27;

/**
 * Modified from book example, p. 719
 * 
 * @author heineman
 * @author savitch
 */
public class GeneralizedSort {

	/**
	 * Search through a[] from position idx to the end to find smallest one
	 * and return that index.
	 * 
	 * @param idx    starting point for comparison
	 * @param a      array of Comparables
	 * @return       index position (>= 0 and < a.length)
	 */
	public static int indexOfSmallest (int idx, Comparable []a) {
		Comparable min = a[idx];
		int minIndex = idx;
		
		for (int i = idx+1; i < a.length; i++) {
			// have we found a new min?
			if (a[i].compareTo(min) < 0) {
				min = a[i];
				minIndex = i;
			}
		}
		
		return minIndex;
	}
	
	/**
	 * Output array of comparables to the screen.
	 * 
	 * @param a
	 */
	public static void output (Comparable []a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print (a[i]);
			if (i < a.length-1) { System.out.print(","); }
		}
		System.out.println();
	}
	
	/**
	 * Sort a[] to be in increasing order. 
	 */
	public static void sort (Comparable[]a) {
		for (int i = 0; i < a.length-1; i++) {
			int smallestIndex = indexOfSmallest(i, a);
			
			// swap
			Comparable tmp = a[smallestIndex];
			a[smallestIndex] = a[i];
			a[i] = tmp;
		}
	}

}
