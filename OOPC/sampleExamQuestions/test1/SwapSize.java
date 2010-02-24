/**
 * egreer
 * Test 1 Practice Problems
 */
package test1;
import java.util.Scanner;

public class SwapSize {

	public static void main(String[] args) {
		//Input
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Input two intergers here:");
		int a = keyboard.nextInt();
		int b = keyboard.nextInt();
		int c;
		
		//Process
		if (a > b){
			c = a;
			a = b;
			b = c;
			
		} 
		
		//Output
		System.out.println("Now correctly ordered:");
		System.out.println(a);
		System.out.println(b);
	}

}
