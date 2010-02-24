/**
 * egreer
 * Test 1 Practice Problems
 */
package test1;

public class ReadingArrays {

	public static void main(String[] args) {
		//Input
		int []ar = new int[3];
		ar[0] = 2;
		ar[1] = 4;
		ar[2] = 7;
		
		int i = 0;
		int last = ar.length;
		
		//Output & Processing
		while(i < ar.length){
			last--;
			
			System.out.println(ar[last]);
			
			i++;
		}
	}

}
