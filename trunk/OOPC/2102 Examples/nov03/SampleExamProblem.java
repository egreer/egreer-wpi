package nov03;

import java.util.Scanner;

/**
 * Criticize this program, which has been proposed as a solution to the 
 * following question:
 * 
 * Write a program where the user types in on a single line a positive number
 * n greater than zero, then types in n numbers separated by spaces followed
 * by pressing Enter. Then output, one per line, each number that had been
 * typed in which was less than 50.
 * @author George
 *
 */
public class SampleExamProblem {
	public static void main (String []args) {
		// INPUT & PROCESSING & OUTPUT
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			int num = sc.nextInt();
			if (num < 50) {
				System.out.println (num);
			}
		}
		
	}
}
