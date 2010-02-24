package dec04;

/**
 * Show how to create a multi-dimensional array.
 * 
 * @author heineman
 */
public class TwoDimensionalExample {

	/**
	 * Write program to manipulate integer arrays.
	 */
	public static void main(String[] args) {
		// create 4x5 array
		int [][]ar = new int [4][5];
		
		// populate array so ar[r][c] equals r*c
		for (int r = 0; r < ar.length; r++) {
			for (int c = 0; c < ar[r].length; c++) {
				ar[r][c] = r*c;
			}
		}
		
		// output to screen
		for (int r = 0; r < ar.length; r++) {
			for (int c = 0; c < ar[r].length; c++) {
				System.out.print (ar[r][c] + " ");
			}
			
			System.out.println ();
		}
		
		// sum values in a column
		int sum = 0;
		for (int r = 0; r < ar.length; r++) {
			sum += ar[r][3];
		}
		
		System.out.println ("sum of fourth column from the left is " + sum);
		
		// sum in a row
		sum = 0;
		for (int c = 0; c < ar[2].length; c++) {
			sum += ar[2][c];
		}
		
		System.out.println ("sum of the third row from the top is " + sum);
		
		

	}

}
