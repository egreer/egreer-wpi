package dec11;

import hw6.SaddlePoint;

/**
 * Instead of generating random arrays, what if we generate every possible array 
 * with a specific number of values?
 * 
 * For example, all possible 2x2 arrays are:
 * 
 * 12  12  13  13  14  14  21  21  23  23  24  24  31  31  32  32  34  34  41  41  42  42  43  43
 * 34  43  24  42  23  32  34  43  14  41  13  31  12  14  14  41  12  21  23  32  31  13  12  21
 * 
 * The total number is (nxn)! which in this case is 4! = 24.
 *  
 * @author George
 *
 */
public class CombinatorialCheck {

	/** Visited array: If integer i has already been used in the array, then visited[i] is true. */
	static boolean [] visited; 
	
	/** Board containing the information. */
	static double [][]board;
	
	/** Count the number of saddlepoints found. */
	static long numSaddlePoints = 0;
	
	/**
	 * Compute n! for small n
	 * 
	 * @param n
	 * @return
	 */
	static long fact (long n) {
		if (n == 0) return 1;
		if (n == 1) return 1;
		
		return n * fact(n-1);
	}
	
	/**
	 * A recursive method that tries to place an available integer at board[r][c]
	 * 
	 * @param r    being filled
	 * @param c    being filled
	 */
	public static void construct (int r, int c) {
		// repeatedly try integer 'i' at this location, if it hasn't already been used.
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;     // USE it!
				board[r][c] = (i+1);   // simply to get ints from 1..n in the board. Could just have been i for same effect.
				
				// if we are at the FINAL position, then we are done and we check for saddle point
				// otherwise, we must keep on recursing inside this method...
				if (r == board.length-1 && c == board[r].length-1) {
					if (SaddlePoint.computeSaddlePoint(board) != null) {
						numSaddlePoints++;
					}
				} else {
					// advance to the next cell in the column, resetting back to zero if we have 
					// run out for this row.
					c = c + 1;
					if (c == board[r].length) {
						c = 0;
						r++;
					}
					
					construct (r, c);   // dive on in!
				}
				
				// when (if) we get here, we have exhausted the use of i at board[r][c]. 
				visited[i] = false;  
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for (int size = 2; size < 6; size++) {
			// construct all possible arrays of size x size using the integers from 1 .. size*size;
			// initial values of the array are all false.
			visited = new boolean [size*size];
			board = new double[size][size];
			numSaddlePoints = 0;
			
			// start by filling the first value
			construct(0, 0);
			
			float ratio = numSaddlePoints;
			long num = fact(size*size);
			ratio = ratio / num;
			
			System.out.println (size + "\t" + numSaddlePoints + "\t" + num + "\t" + ratio);
		}

	}

}
