package lps;

import java.util.Arrays;

/** Algorithms HW6, Question 1
 * 
 * @author Eric Greer
 *
 */
public class LinearProbeSort {

	public static void main (String args[]){
		
	
		Float[] A = {new Float(4.07), new Float(2.02), new Float(3.06), new Float(3.97), new Float(3.89), new Float(3.72), new Float(4.24), new Float(3.66), new Float(3.71), new Float(4.44), new Float(2.31), new Float(4.70), new Float(3.35), new Float(4.42), new Float(3.01), new Float(3.67), new Float(3.96), new Float(3.68), new Float(3.22),new Float(2.95), new Float(4.71), new Float(4.20), new Float(3.74), new Float(4.18), new Float(3.02)};
		Float[] B= new Float[(int) Math.round((A.length*(1 + .2)))];
		Float[] C = LinearProbingSort(A, B, A.length, B.length);

		System.out.println("Sorted Values: ");
		printArray(C);
	}
	
	/** Sorts an array using linear probing sort
	 * 
	 * @param A	The array containing values tobe sorted
	 * @param B the array to use as a working space. Included here to emulate algorithm. 
	 * @param n	the length of array A. Included here to emulate algorithm.
	 * @param m the length of array B. Included here to emulate algorithm.
	 * @return	Returns the sorted array
	 */
	static Float[] LinearProbingSort(Float[] A, Float[] B, int n, int m){
		Float x;
		int k;
		int i;
		int f = 5;

		Arrays.fill(B, null);
		
		for(int j =0 ; j <n ; j++){
			x = A[j];
			k = (int) Math.floor((m - f) * edf(A, x));
			if (B[k] == null) B[k] = x;
			else {
				while(B[k] != null){
					if (x < B[k]){
						//Swap
						Float t = B[k]; //temp
						B[k] = x;
						x = t;	
					}
					
					k++;
				}
				B[k] = x;
			}
			
			if (j == 19){
				System.out.println("B after 20 insertions: ");
				printArray(B);
			}
		}
		
		System.out.println("B after 25 insertions: ");
		printArray(B); //print all values
		
		i = k = 0; //put all B to A
		while(i < n){
			while(B[k] == null) k++; //Skip empty space
			A[i] = B[k];
			i++;
			k++;
		}
		resetedf();
		return A;
	}
	
	static boolean edfSet = false;
	static float a = Float.MAX_VALUE;
	static float z = Float.MIN_VALUE;
	static float intervalSize;
	static int[] ranges;
	
	static double edf(Float[] A, Float x){
		double returner = .1;
		
		//Creates the ranges if they have not been set. 
		if(!edfSet){
			//Find min(a) and max (z)
			for(int o = 0 ; o < A.length; o++){
				if (A[o] < a) a = A[o];
				if (A[o] > z) z = A[o];
			}

			//Create ranges of size k intervals
			intervalSize = (z - a) / 8;
			ranges = new int[8];

			//Count number in each range
			for(int o = 0 ; o < A.length; o++){
				if(A[o]== z) ranges[ranges.length-1]++; //Special case since (z-a/intervalSize) is out of range of the array 
				else {
					ranges[(int) Math.floor((A[o]-a)/intervalSize)]++;
				}
			}
			edfSet = true;
		}
		
		//Find interval where x should be
		int xPosition = (int) Math.floor((x-a)/intervalSize);
		if (x == z) xPosition = ranges.length - 1; //Special case since (z-a/intervalSize) is out of range of the array
		
		//Calculate the total number of elements under the position
		int elementsUnder = 0;
		for (int i = 0 ; i < xPosition; i++){
			elementsUnder += ranges[i];
		}

		returner = ( (elementsUnder  / (double) A.length) + ( (ranges[xPosition] / (double)A.length) * ((x - (a + xPosition*intervalSize)) / (z - a))));

		return returner;
	}
		
	 static void printArray(Float[] B){
		for (int i = 0; i < B.length ; i++){
			System.out.print(B[i] + ", ");
		}
		System.out.println();
	}
	 
	 
	 static void resetedf(){
			edfSet = false;
			a = Float.MAX_VALUE;
			z = Float.MIN_VALUE;
			intervalSize = Float.NaN;
			ranges = null;
	 }
}
