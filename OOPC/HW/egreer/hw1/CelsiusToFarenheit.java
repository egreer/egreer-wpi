/**
 * egreer and jm4games
 * Homework 1
 */
package egreer.hw1;

public class CelsiusToFarenheit {
	public static void main(String[] args) {
		//Input
		int celsius = 40;
		
		//Processing
		double fahrenheit = (celsius * (9.0 / 5.0) + 32.0);
		
		//Output
		System.out.println("Celsius : Fahrenheit");
		System.out.println("--------------------");
		
		while (celsius >= -10){
			System.out.print(celsius + " : ");
			System.out.printf("%.1f", fahrenheit);
			System.out.println();
			
			celsius = celsius - 1;
			fahrenheit = (celsius * (9.0 / 5.0) + 32.0);
		}
	}

}
