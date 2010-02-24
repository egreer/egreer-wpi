package oct31;

import java.util.Scanner;

/**
 * Read in a set of (at least one) numbers. Then compute their average and standard deviation.
 * 
 * mean = (1/N) SUM (Xi) for all numbers
 * stdev = Sqrt ((1/(N-1)) * SUM (Xi-mean)^2)
 * 
 * http://en.wikipedia.org/wiki/Standard_deviation
 *
 * Compare the output of your program with, say, Microsoft Excel and its 'AVERAGE' and 'STDEV'
 * functions.
 * 
 * @author heineman
 */
public class ArrayExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("Enter in up to 10 integers. When you are done, press Control-z");
		int []nums = new int[10];
		
		// Read in numbers while (a) less than ten entered; and (b) user hasn't pressed control-z
		int num = 0;
		while ((num < 10) && sc.hasNext()) {
			// read in the next number
			nums[num] = sc.nextInt();
			
			num++;  // ADVANCE
		}
		
		// when we get here, num is exactly equal to the number of ints typed in. 
		// take advantage of that!
		
		// PROCESSING
		// compute mean
		double mean = 0;
		int i = 0;
		while (i < num) {
			mean += nums[i];
			
			i++;  // ADVANCE
		}
		mean = mean/num;
		
		// compute stdev: Sqrt ((1/(N-1)) * SUM (Xi-mean)^2)
		
		double partial = 0;
		i = 0;
		while (i < num) {
			partial += (nums[i]-mean)*(nums[i]-mean);
			
			i++;  // ADVANCE
		}
		double stdev = partial/(num-1);
		stdev = Math.sqrt(stdev);
		
		// OUTPUT
		System.out.println ("Average:" + mean);
		System.out.println ("Stdev:" + stdev);
	}

}

