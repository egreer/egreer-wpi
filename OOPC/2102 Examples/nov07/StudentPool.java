package nov07;

import java.util.Scanner;
import java.io.*;

/**
 * Load up a batch of students.
 * 
 * @author heineman
 *
 */
public class StudentPool {

	/**
	 * Load up student file from disk.
	 * 
	 * First line of the file is an int describing total number of students in that file
	 * Next n lines are the student records, of the form:
	 *  
	 *    "B01 Biggs, Jason             10CS" 
	 * 
	 * @param sc    The object by which we are reading the student file.
	 * @return
	 */
	public static Student[] loadStudentFile(Scanner sc) {
		int num = sc.nextInt();
		sc.nextLine();           // Drain the rest of the input on this line and throw away....
		
		Student[] students = new Student[num];
		for (int i = 0; i < students.length; i++) {
			// grab each line, and extract relevant information.
			String s = sc.nextLine();
			
			// create a new Student instance.
			Student st = new Student();
			st.name = s.substring(4, 29);
			st.yog = s.substring(29, 31);
			st.major = s.substring(31, 34);
			st.section = s.substring(0,3);
			
			students[i] = st;
		}
		
		return students;
	}

	/**
	 * Count the number of morning students.
	 * 
	 * @param students    array of Student objects
	 * @return
	 */
	public static int countMorning(Student[] students) {
		int ct = 0;
		for (int i = 0; i < students.length; i++) {
			if (!students[i].isAfternoon()) {
				ct++;
			}
		}
		
		return ct;
	}
	
	
	/**
	 * Load up students, assign them to different slots, and generate daily questions
	 *
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// INPUT
		Scanner sc = new Scanner (new File ("nov07\\students.txt"));
		Student[] students = loadStudentFile(sc);
		
		// PROCESSING
		
		// OUTPUT
		for (int i = 0; i < students.length; i++) {
			System.out.println (students[i]);
		}

	}

}
