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
public class Problem2 {
	
	public static void main(String[] Args)throws FileNotFoundException{
		//INPUT
		Scanner sc = new Scanner (new File ("input.txt"));
		
		//Variables
		int totalWordCount = 0;
		int aCount = 0;
		int cCount = 0;
		int tCount = 0;
		int gCount = 0;
		int mostTotal=0;
		char mostCommon = 'n';
		
		//PROCESSING
		while (sc.hasNext()) {
			String word = sc.next();
			
			//Counts the total number of each charecter
			while (totalWordCount < word.length()){
				
				if (word.charAt(totalWordCount) == 'a'){
					aCount++;
					
				}else if (word.charAt(totalWordCount) == 'c'){
					cCount++;
					
				}else if (word.charAt(totalWordCount) == 't'){
					tCount++;
					
				}else if (word.charAt(totalWordCount) == 'g'){
					gCount++;
				}
				
				//Update Total Charecters
				totalWordCount++;
			}
		}
		
		//Processes the most common charecter
		mostTotal = Math.max( Math.max(aCount, cCount),	Math.max(tCount, gCount));
		
		if (mostTotal == aCount){
			mostCommon = 'a';
			
		}else if (mostTotal == cCount){
			mostCommon = 'c';
			
		}else if (mostTotal == tCount){
			mostCommon = 't';
			
		}else if (mostTotal == gCount){
			mostCommon = 'g';
			
		}
		
		
		
		//OUTPUT
		System.out.println("a:%" + (100.0*aCount/totalWordCount));
		System.out.println("c:%" + (100.0*cCount/totalWordCount));
		System.out.println("t:%" + (100.0*tCount/totalWordCount));
		System.out.println("g:%" + (100.0*gCount/totalWordCount));
		System.out.println("The most common letter is: " + mostCommon);
		
	}
}