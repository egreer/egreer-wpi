/**
 * egreer and jm4games
 * Package for HW1
 */
package egreer.hw1;
import java.util.Scanner;

public class MaxMinSum {

	public static void main(String[] args) {
		//Input
		Scanner number = new Scanner(System.in);
		int sum = 0;
		int max = 0;
		int min = 0;
		
		//Processing
		System.out.println("Input Numbers Here:");
		
		while (number.hasNext()) {
			int tempNumber = number.nextInt();
			if (tempNumber > max){
				max = tempNumber;
				sum = sum + tempNumber;
			}else if (tempNumber < min){
				min = tempNumber;
				sum = sum + tempNumber;
			}else 
				sum = sum + tempNumber;
			
		}
		
		//Output
		System.out.println("Minimum: " + min);
		System.out.println("Maximum: " + max);
		System.out.println("Sum: " + sum); 

	}

}
