package goldbug.v2;

import java.util.Scanner;

/** Provides screen-based text GUI interaction to Goldbug program. */
public class Screen {
	
	/** Means of getting input. */
	Scanner sc;
	
	/** Represents the code. */
	Code    code;
	
	/** Entity to control logic. */
	IGoldbug controller;
	
	public Screen (Scanner sc, Code c) {
		this.sc = sc;
		this.code =c;
	}

	public void setController(IGoldbug c) {
		this.controller = c;
	}

	public void interact () {
		
		System.out.println ("Welcome to the GoldBug decoding system");
		System.out.println ("Version 1.0");
		while (true) {
			System.out.println ("\nCode is:");
			System.out.println (code.toString());
			System.out.println (code.decoded());
			System.out.println ("\nCurrent coded letters:");
			System.out.println (code.showKey());
			
			System.out.println ("\nOptions:");
			System.out.println (" [f] Show letter frequency");
			System.out.println (" [a] Assign a letter");
			System.out.println (" [c] Clear a letter assignment");
			System.out.println (" [q] Quit!");
			System.out.println ();
			
			String s;
			try {
				while (true) {
					s = sc.nextLine();
					if (s.equals("q")) { controller.requestQuit(); break; }
					if (s.equals("f")) { controller.requestFrequency(); break; }
					if (s.equals ("c")) { 
						System.out.println ("Enter the coded letter you wish to clear");
						s = sc.nextLine();
						controller.requestClear(s.charAt(0)); 
						break; 
					}
					if (s.equals ("a")) {
						System.out.println ("Enter the coded letter you wish to assign");
						s = sc.nextLine();
						char ch1 = s.charAt(0);
						System.out.println ("What code should be given?");
						s = sc.nextLine();
						char ch2 = s.charAt(0);
						
						controller.requestAssign(ch1, ch2); 
						break; 
					}
					
					System.out.println ("\n Enter one of [facq] please");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println ("\nAn error occured. Try again!");
			}
		}
	}
}
