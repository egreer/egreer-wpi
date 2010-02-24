package egreer.hw2;
import java.util.Scanner;

/*
 * Write a program that reads in a sequence of n numbers from the keyboard. 
 * The user first is prompted "How many numbers are in the sequence", to which they reply with an int value n > 0. 
 * Then your program should read in n int values. The task of your program is to 
 * (a) identify the longest sequence of identical values in a row; and (b) print that value to the console. 
 */


public class Problem5 {
	
	public static void main(String[] args){
		//INPUT
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("How many numbers are in the sequence?");
		int []ar = new int[keyboard.nextInt()];
		
		System.out.println("Please enter " + ar.length + " numbers separated by whitespace");
		for (int i = 0; i < ar.length; i++){
			ar[i] = keyboard.nextInt();
		}
		
		int numbersInRow = 0;
		int theNumber = 0;
		
		
		//PROCESSING
		int i = 1;
		while(i <= ar.length){
			int tempLength = 0;
			
			if (ar[--i] == ar[i]){
				tempLength += 1;
								
				if (numbersInRow < tempLength){
					numbersInRow = tempLength + 1;
					theNumber = ar[i];
				}
				
			}
			
			i++;
		}
		
		//OUTPUT
		System.out.println(" The largest sequence of consecutive values is a sequence of " + numbersInRow + " int with value " + theNumber + ".");
	}

}
