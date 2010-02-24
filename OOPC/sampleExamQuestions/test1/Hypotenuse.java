/**
 * egreer
 * Test 1 Practice Problems
 */
package test1;

public class Hypotenuse {

	public static void main(String[] args) {
		//Input
		int x = 5;
		int y = 7;
		double hypotenuse;
		
		//Processing
		hypotenuse = Math.sqrt((Math.pow(x,2.0) + Math.pow(y,2.0)));
		
		//Output
		System.out.println("The Hypotenuse is " + hypotenuse);

	}

}
