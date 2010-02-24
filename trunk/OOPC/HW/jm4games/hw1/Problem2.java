package jm4games.hw1;


	/**
	 * @author Josh Montgomery,Eric Greer
	 * sn: jm4games,egreer
	 *
	 */
public class Problem2 {

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			
			//INPUT
			String word = "Booya";
			
			//PROCESSING -> OUTPUT
			if(compare(word))
				System.out.println("The word starts and ends with the same letter");
			else
				System.out.println("The word oes not start an end with the same letter");

		}
		
		//method compare compares the first and last letter of a string and return true if they are the same
		// and false if they aren't.
		//@param string
		public static boolean compare(String s)
		{
			 s = s.toLowerCase();
			if(s.charAt(0) == s.charAt(s.length()-1))
				return true;
			else 
				return false;
		}
		
		

	}