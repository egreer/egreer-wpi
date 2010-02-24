package hw6;

/**
 * Skeleton class to represent the SaddlePoint program.
 * 
 * @author heineman
 *
 */
public class SaddlePoint {

	/** The row of the saddle point. */
	int row;
	
	/** The column of the saddle point. */
	int column;
	
	/**
	 * Constructs the SaddlePoint, if it exists.
	 * 
	 * Only this class can invoke this constructor.
	 * 
	 * @param r    row coordinate of saddle point
	 * @param c    column coordinate of saddle point
	 */
	protected SaddlePoint (int r, int c) {
		this.row = r;
		this.column = c;
	}
	
	/**
	 * Return String representation of saddle point.
	 * 
	 * @return    String of the form "[r][c]"
	 */
	public String toString () {
		return "[" + row + "][" + column + "]";
	}
	
	/**
	 * Computes the saddle point. A saddle point is defined as an array entry which is the
	 * minimal in its row and the maximal in its column.
	 * 
	 * Note that an array can have only one saddle point. Use "<" (lesser than) as the means
	 * for determining if a number is less than another number (rather than lesser-than-or-equal)
	 * when comparing for minimality. The same is true for ">" rather than greater-than-or-equal.
	 * 
	 * @param ar    the array containing the desired information. There must be at least two
	 *              rows in the array and two columns
	 * @return      null if no saddle point exists or the array is ill-formed; the SaddlePoint (r,c) otherwise.
	 */
	public static SaddlePoint computeSaddlePoint (double [][]ar) {
		for (int r = 0; r < ar.length; r++) {
			// find the minimal value in this row; and then check to see if that location is
			// the minimal one in its column.
			double min = ar[r][0];
			int idx = 0;
			boolean repeat = false;
			for (int c = 1; c < ar[r].length; c++) {
				if (ar[r][c] < min) {
					idx = c;
					min = ar[r][c];
					repeat = false;
				} else if (ar[r][c] == min) {
					repeat = true;
				}
			}
			
			// at this point, it is known that ar[r][idx] is the smallest in that row. If repeat
			// is not true, we check for saddlepoint
			if (!repeat) {
				// check to see if 'min' is strictly the largest in its column.
				boolean fails = false;
				for (int r2 = 0; r2 < ar.length; r2++) {
					if (r2 != r) {
						if (ar[r2][idx] >= min) {
							fails = true;
						}
					}
				}
				
				// we have found the saddle point!
				if (!fails) {
					return new SaddlePoint (r, idx);
				}
			}
		}
		
		// no saddle point!
		return null;
	}

}
