package hw6;

/**
 * Solve Problem1 for HW6.
 * 
 * @author heineman
 */
public class Problem1 {

	/**
	 * Randomly populate a square array.
	 * 
	 * @param   ar[][] to be populated with random values in the range [0,1)
	 */
	public static void populate (double [][]ar) {
		for (int r = 0; r < ar.length; r++) {
			for (int c = 0; c < ar[r].length; c++) {
				ar[r][c] = Math.random();
			}
		}
	}
	
	/**
	 * Run 10,000 trials of saddlepoint exploration over square and rectangular arrays.
	 */
	public static void main(String[] args) {
		
		// store results of square computation
		double []ratios = new double[15];
		
		// How frequent are saddlepoints in random square matrices? Compute the results
		for (int size = 3; size < 15; size++) {
			double [][]ar = new double[size][size];

			int numSaddlePoints = 0;
			int numTrials = 10000;
			for (int trials = 0; trials < numTrials; trials++) {
				// populate randomly.
				populate(ar);
				
				SaddlePoint sp = SaddlePoint.computeSaddlePoint(ar);
				if (sp != null) {
					numSaddlePoints++;
				}
			}
			
			ratios[size] = numSaddlePoints;
			ratios[size] = ratios[size]/numTrials;
			System.out.println (size + " " + ratios[size]);
		}
		
		// Where are the saddle points in rectangular (long) matrices whose width is twice its height
		System.out.println ("N Square Rectangle");
		for (int size = 3; size <= 5; size++) {
			double [][]ar = new double[4][size*size];

			int numSaddlePoints = 0;
			int numTrials = 10000;
			for (int trials = 0; trials < numTrials; trials++) {
				// populate randomly.
				populate(ar);
				
				SaddlePoint sp = SaddlePoint.computeSaddlePoint(ar);
				if (sp != null) {
					numSaddlePoints++;
				}
			}
			
			double ratio = numSaddlePoints;
			ratio = ratio/numTrials;
			
			System.out.println ((4*size*size) + " " + ratios[2*size] + " " + ratio);
		}
		
		System.out.println ("As you can see, the probability of finding a saddle point in a rectangular array is greater.");
	}
}
