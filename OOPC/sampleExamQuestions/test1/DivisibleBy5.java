/**
 * egreer
 * Test 1 Practice Problems
 */
package test1;

public class DivisibleBy5 {

	public static void main(String[] args) {
		//Input
		String test = "15";
		
		//Processing & Output
		if ((test.charAt(test.length()-1) == '5') || (test.charAt(test.length()-1) == '0')){
			System.out.println(test +" is divisible by 5.");
		}
		
		else {
			System.out.println(test +" is not divisible by 5.");
		}
				
	}

}
