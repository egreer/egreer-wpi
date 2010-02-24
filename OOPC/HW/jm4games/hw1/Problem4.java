/**
 * Class Problem4.java
 */
package jm4games.hw1;

import java.util.Scanner;

/**
 * @author Josh Montgomery,Eric Greer
 * sn: jm4games,egreer
 *
 */
public class Problem4 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
       
	   //INPUT
	   Scanner keyboard = new Scanner(System.in);
             
       System.out.println("Enter Line 1: ");
       String line1 = keyboard.nextLine();
       
       System.out.println("Enter Line 2: ");
       String line2 = keyboard.nextLine();      
       
       //PROCESSING -> OUTPUT
       if(line1.length() == line2.length() && reverse(line1).equals(line2))
           System.out.println("Line 2 is the reverse of Line 1.");      
       else
	   	   System.out.println("No comparison");
	}
	
	public static String reverse(String s)
	{
		if(!s.equals(""))
			return s.charAt(s.length()-1)+reverse(s.substring(0,s.length()-1));
		else
			return "";
	}
}