package nov13;

import java.util.Scanner;

/**
 * Shows the ability to construct information on-demand in the context of students, sections, and
 * courses. 
 * 
 * Note use of the static variable 'sc' to store the scanner, which can then be used in any static
 * method in this class
 * 
 * @author heineman
 */
public class SmallExampleProgram {
	
	/** Scanner object that manages all user input. */
	static Scanner sc;
	
	/** Student List so far. */
	static StudentList list;
	
	/** 
	 * Add a student to the list (prepended).
	 * 
	 * All input is retrieved from the user via 'sc' and is assumed to be CORRECT!
	 */
	public static void addStudent () {
		System.out.println ("  Enter Student Name: ");
		String name = sc.nextLine();
		System.out.println ("  Enter Student ID: " );
		String id = sc.nextLine();
		System.out.println ("  Enter Student Year of Graduation: " );
		String yog = sc.nextLine();
		System.out.println ("  Enter Student Major: " );
		String major = sc.nextLine();
		
		Student stud = new Student (id, name, yog, major);
		list.prepend(stud);		
	}
	
	/** Find a student in the list by id. */
	public static void findStudent() {
		System.out.println ("  Enter Student ID: " );
		String id = sc.nextLine();
		
		Student stud = list.findById(id);
		if (stud == null) {
			System.err.println ("  Student " + id + " doesn't exist");
		} else {
			System.out.println (stud);
		}
	}
	
	/**
	 * Main text-based GUI screen from which user makes decisions.
	 */
	public static void mainScreen () {
		// Note that this looks like an infinite loop, but we can return from this method, see below...
		while (true) {
			System.out.println ("Student information system, version 1.0");
			System.out.println ();
			System.out.println (" q - Exit the program");
			System.out.println (" a - Add a student");
			System.out.println (" d - Display all students");
			System.out.println (" f - Find a student");
			
			String line = sc.nextLine();
			line = line.toLowerCase();
			
			// Quit? Leave now.
			if (line.startsWith("q")) {
				return;
			}
			
			if (line.startsWith("a")) {
				addStudent();
			} else if (line.startsWith("d")) {
				displayStudents();
			} else if (line.startsWith("f")) {
				findStudent();
			} else {
				System.err.println (line + " is an unknown option");
			}
		}		
	}
	
	/** Show students in the list. */
	private static void displayStudents() {
		System.out.println ("  Students in list:");
		list.output();		
	}

	/**
	 * Launch small program that reads in student information and prints out information.
	 */
	public static void main (String []args) {
		// initialize key static variables.
		sc = new Scanner (System.in);
		list = new StudentList();
		
		// launch the main screen.
		mainScreen();
	}
}
