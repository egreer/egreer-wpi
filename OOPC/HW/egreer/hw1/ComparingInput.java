/**
 * egreer and jm4games
 * Package for HW1
 */
package egreer.hw1;
import java.util.Scanner;

public class ComparingInput {
	public static void main(String[] args){
		//Input & Processing
		Scanner line = new Scanner(System.in);
		
		System.out.println("Enter Line 1:");
		String line1 = line.nextLine();
		System.out.println("Enter Line 2:");
		String line2 = line.nextLine();
		
		int position; 
		int length = line1.length();
		StringBuffer reversing = new StringBuffer(length);
				
		for (position = (length - 1); position >= 0; position--) {
		    reversing.append(line1.charAt(position));
		}
		
		// Output
		
		if (line2.equals(reversing.toString())){
			System.out.println("Line 2 is a reverse of Line 1");
		}
		else {
			System.out.println("No Comparison");
		}
	
	}

}
