package unix.sort;

import java.util.Comparator;

/**
 * 
 * Compares strings that contains numbers for numerical sort
 * 
 * Input strings are split into sections (whitespace is delimiter)
 * Compares section by section
 * --Sections that start with numbers are less than sections that start with characters
 * ----e.g. Strings "1", "a", "2", "b" sorts to "1", "2", "a", "b"
 * --Sections that do not contain only numbers will be compared lexicographically
 * Strings with more sections will sort to the bottom if common sections are equal
 * ----e.g. the strings "a a", "a a a", "a" sorts to "a", "a a", "a a a"  
 * 
 * @author Andrew Yee
 *
 */
public class NumberStringComparator implements Comparator<String> {

	/**
	 * Compares two strings by splitting them into sections
	 * based on whitespace
	 */
	@Override
	public int compare(String arg0, String arg1) {
		
		String[] string0 = arg0.split(" ");
		String[] string1 = arg1.split(" ");
		
		int string0size = string0.length;
		int string1size = string1.length;
		int smallersize = Math.min(string0size, string1size);
		int sizecompare = string0size - string1size;
		
		//Compare strings
		for (int i = 0; i < smallersize; i++) {
			int compareresult = compareSections(string0[i], string1[i]);
			if (compareresult != 0) {
				return compareresult;
			}
		}
		
		// if we get here, then everything we checked is the same
		// either they are actually the same, or one string had more sections than the other
		return (sizecompare < 0) ? -1 : (sizecompare > 0) ? 1 : 0;
	}
	
	/**
	 * Checks if the given String is a parsable number
	 * 
	 * @param s			the String being checked
	 * @return true		if the String is a number
	 * 		   false	if the String is not a number	
	 */
	boolean isNumberSection(String s) {
		
		try {
			// if this doesn't throw an exception, then the number is true 
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			// if the string isn't number, then false
			return false;
		}
		
	}
	
	/**
	 * Compares the two string Sections, by checking whether each
	 * is a number or not
	 * 
	 * Numbers will be less than other characters
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	int compareSections(String s1, String s2) {
		boolean isS1Num = isNumberSection(s1);
		boolean isS2Num = isNumberSection(s2);
		
		
		if (isS1Num && isS2Num) {
			int difference = Integer.parseInt(s1) - Integer.parseInt(s2);
			return (difference < 0) ? -1 : (difference > 0) ? 1 : 0;
		} else if (isS1Num && !isS2Num) {
			return -1;
		} else if (!isS1Num && isS2Num) {
			return 1;
		} else {
			return s1.compareToIgnoreCase(s2);
		}
		
		
	}
	
}
