package oct30;

public class Gotcha {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double d = 35.49;
		double x = 19;
		
		// WHAT is going on here! Can you figure it out? Note that the problem
		// goes away if you replace ',' with ",".
		System.out.println (d + ',' + x);
		
		// the problem is that character constants (',') are sometimes treated
		// as int values, because they are drawn from the ASCII character set, which
		// has values '0' .. '255'.  Here, you can see that COMMA (',') is ASCII
		// value 44!
		//
		// Lesson? Never use ',' characters within System.out.println. Always use ","
		// even if only a single character is in the string.
	}

}
