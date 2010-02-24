/**
 * Class Problem5.java
 */
package jm4games.hw1;

import java.util.Scanner;

/**
 * @author Josh Montgomery,Eric Greer
 * sn: jm4games,egreer
 *
 */
public class Problem5 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		
	  //INPUT	
	  Scanner nums = new Scanner(System.in);
	  int input,max=0 ,min = 0,sum = 0;
	  boolean first = true;
	  
	  System.out.println("Enter a list of numbers <press Ctrl+z to break>: ");
	  
	  //PROCESSING
	  while(nums.hasNext())
	  {
		  input = nums.nextInt();

		  if(first)
		  {
			  max = input;
			  min = input;
			  first = false;
		  }
		  
		  if(input > max)
			  max = input;
		  
		  if(input < min)
			  min = input;
		  
		  sum += input;
		  
	  }
	 
	 //OUTPUT
	 System.out.println("Minimum: "+min);
	 System.out.println("Maximum: "+max);
	 System.out.println("Sum: "+sum);	  
	}	
}