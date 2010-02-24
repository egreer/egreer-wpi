/**
 * egreer and jm4games
 * Package for HW1
 */
package egreer.hw1;

public class StartEndSame {
	
	public static void main(String[] args) {
		String test = "Test";
		test = test.toLowerCase(); 
		int lastPosition = test.length() - 1;
		String firstCharecter = test.substring(0 , 1);
		int lastEqual = test.lastIndexOf(firstCharecter);
				
		boolean a = (lastEqual == lastPosition);
		
		if (a){
			System.out.println ("The word starts and ends with the same letter");
		} 
		else {
			System.out.println ("The word does not start and end with the same letter");
		}
		
	}

}
