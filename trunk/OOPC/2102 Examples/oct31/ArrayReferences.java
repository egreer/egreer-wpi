package oct31;

public class ArrayReferences {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// INPUT
		int []ar1;
		int []ar2;
		
		// at this point, ar1 and ar2 are both null.
		
		// short cut for 
		//
		//   ar1 = new int[5];
		//   ar1[0] = 2;
		//   ar1[1] = 4;
		//   ar1[2] = 6;
		//   ar1[3] = 8;
		//   ar1[4] = 10;
		ar1 = new int [] {2, 4, 6, 8, 10}; 
		
		System.out.println ("The third element is " + ar1[2]);
		
		// what does the following statement do?
		ar2 = ar1;
		
		// If you know what the above does, then what is printed below
		ar2[2] = 99;
		System.out.println ("The third element is " + ar1[2]);
	}

}
