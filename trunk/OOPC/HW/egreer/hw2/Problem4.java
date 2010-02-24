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
public class Problem4 {
	
	public static void main(String[] Args)throws FileNotFoundException{
		//INPUT
		Scanner input = new Scanner (new File ("input.txt"));
		Scanner testers = new Scanner (new File ("sequences.txt"));
		
		String longString = input.next();
		
		
		//PROCESSING & OUTPUT
		while (testers.hasNext()){
			String temp = testers.next();
			
			int position; 
			int length = temp.length();
			
			StringBuffer reversingSwitch = new StringBuffer(length);
						
			for (position = (length - 1); position >= 0; position--) {
			    if (temp.charAt(position) == 'a'){
			    	reversingSwitch.append('t');
			    	
			    }else if (temp.charAt(position) == 't'){
			    	reversingSwitch.append('a');
			    	
			    }else if (temp.charAt(position) == 'c'){
			    	reversingSwitch.append('g');
			    	
			    }else if (temp.charAt(position) == 'g'){
			    	reversingSwitch.append('c');
			    }
			}
			
			//OUTPUT		
			if ( -1 == longString.indexOf(reversingSwitch.toString())){
				System.out.println("UNMATCHED");
			
			}else if (temp.length() >  10){
				System.out.print(longString.indexOf(reversingSwitch.toString()) + temp.length() - 1);
				System.out.print(" .. ");
				System.out.print(longString.indexOf(reversingSwitch.toString()));
				System.out.print(" ");
				System.out.print(temp.substring(0 , 11));
				System.out.print("...");
				System.out.println();
								
			}else{
			System.out.print(longString.indexOf(reversingSwitch.toString()) + temp.length());
			System.out.print(" .. ");
			System.out.print(longString.indexOf(reversingSwitch.toString()) + 1);
			System.out.print(" ");
			System.out.print(temp);
			System.out.println();	
			}
		}
	}
}
