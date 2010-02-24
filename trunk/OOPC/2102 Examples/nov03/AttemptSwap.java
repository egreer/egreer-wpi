package nov03;

public class AttemptSwap {
	/** Incorrect method to swap variable values. */
	public static void swap (int x, int y) {
		int t = x;
		x = y;
		y = t;
	}
	
	/** Incorrect program that tries to swap variable values. */
	public static void main (String []args) {
		// INPUT
		int a = 5;
		int b = 7;
		
		System.out.println (a);
		System.out.println (b);
		
		// try to swap values (INCORRECT)
		swap (a,b);
		
		System.out.println (a);
		System.out.println (b);
	}
}
