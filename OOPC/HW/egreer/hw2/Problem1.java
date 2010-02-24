package egreer.hw2;
import java.util.Scanner;
/**
 * @author Eric Greer, Josh Montgomery
 * sn: egreer,jm4games
 *
 */

public class Problem1 {
	
	public static void main(String[] args){
		Scanner line = new Scanner(System.in);
		
		//INPUT
		System.out.println("Enter the line and I will print it in reverse");
		String inputed = line.nextLine();
		
		//PROCESSING
		//Reverses Inputed String 
		int position; 
		int length = inputed.length();
		StringBuffer reversing = new StringBuffer(length);
				
		for (position = (length - 1); position >= 0; position--) {
		    reversing.append(inputed.charAt(position));
		}
		
		//OUTPUT
		System.out.print(reversing.toString());
		
		
	}

}
