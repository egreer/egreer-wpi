package oct27;

/**
 * Write a program that defines an int variable x. Set x to some integer value of your choosing.
 * Then define an if statement that will (a) output "Negative" if x is less than zero; 
 * (b) output "Zero" if x is equal to zero; (c) output "Positive" if x is greater than zero
 * 
 * @author heineman
 */
public class DailyQuestion {

	/**
	 * Execute program
	 */
	public static void main(String[] args) {
		// INPUT
		int x = -3;

		// PROCESSING
		String type;
		if (x < 0) {
			type = "Negative";
		} else if (x > 0) {
			type = "Positive";
		} else {
			type = "Zero";
		}
		
		// OUTPUT
		System.out.println (type);
	}

}
