package egreer;
import java. util.Scanner;

/**
 * This is the first lab. It outputs the average of a user defined 
 * set of numbers after removing the highest and the lowest values 
 * 
 * @author egreer
 */


public class Lab1 {
	public static void main(String[] args){
		// INPUT
		Scanner keyboard = new Scanner(System.in);
		
		
		System.out.println("Enter the size of the set?");
		int size = keyboard.nextInt();
		
		int []array;
		array = new int[size];
		
		System.out.println("Now enter the " + size + " numbers, one per line");
		
		int i;
		for(i= 0; i < size; i++){
			array[i] = keyboard.nextInt();
		}
				
		
		int highNumber = 0;
		int lowNumber = 0;
		int highNumberPosition = -1;
		int lowNumberPosition = -1;
		int sum = 0;
		
		//PROCESSING
		//Checks for high and low numbers
		for (i = 0; i < size; i++){
			if (highNumber < array[i]){
				highNumber = array[i];
				highNumberPosition = i;
								
			}else if (array[i] < lowNumber){
				lowNumber = array[i];
				lowNumberPosition = i;
								
			}else{} 
				 
		}
		
		
		//Adds the numbers without the high and low numbers to the integer "sum"
		for (i = 0; i < size; i++){
			if ((i == highNumberPosition) || (i == lowNumberPosition)){
				
				
			}else {
				sum += array[i];
				
			}
		}
		
		//OUTPUT
		System.out.println("The average of these numbers after removing the high value ("+ highNumber +") and the low value ("+ lowNumber +") is " + sum/(size - 2.0));
				
	}
}
