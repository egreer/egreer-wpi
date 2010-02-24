package oct26;

/**
 * Write a Java program to determine if a String (containing only digits) represents a number divisible by 5.
 * @author heineman
 */
public class DivisibleBy5 {

	/**
	 * Main
	 */
	public static void main(String[] args) {
		// INTPUT
		String s = "182739852";
		
		// PROCESSING
		char lastDigit = s.charAt(s.length()-1);
		boolean isFive = (lastDigit == '5');
		
		// OUTPUT
		System.out.print ("Is " + s + " divisible by five? ");
		System.out.println (isFive);
	}

}
