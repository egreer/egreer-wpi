package egreer.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Display input straight from a file.
 * 
 * @author Eric Greer, Josh Montgomery
 * sn: egreer, jm4games
 */


public class Problem3 {
	
	
	public static void main(String[] Args)throws FileNotFoundException{
		//INPUT
		Scanner input = new Scanner (new File ("input.txt"));
		Scanner testers = new Scanner (new File ("sequences.txt"));
		
		String longString = input.next();
		
		
		//PROCESSING & OUTPUT
		while (testers.hasNext()){
			String temp = testers.next();
			
			if ( -1 == longString.indexOf(temp)){
				System.out.println("UNMATCHED");
			
			}else if (temp.length() >  10){
				System.out.print(longString.indexOf(temp));
				System.out.print(" .. ");
				System.out.print(longString.indexOf(temp) + temp.length() - 1);
				System.out.print(" ");
				System.out.print(temp.substring(0 , 11));
				System.out.print("...");
				System.out.println();
								
			}else{
			System.out.print(longString.indexOf(temp) + 1);
			System.out.print(" .. ");
			System.out.print(longString.indexOf(temp) + temp.length());
			System.out.print(" ");
			System.out.print(temp);
			System.out.println();	
			}
			
			
		}
		
	}

}
