package oct31;

/**
 * Show simple array access.
 * 
 * Make sure you understand the difference between the two statements
 * 
 *    String[] seasons;              Defines type of variable, but not its value
 *    
 *    seasons = new String[4];       Defines the value of seasons to be a 4-element array of String 
 *    
 * @author heineman
 */
public class SimpleArrayAccess {

	/**
	 * Output elements from an array.
	 */
	public static void main(String[] args) {
		// Define the array variable seasons
		String []seasons;
		
		// define seasons to be composed of four elements [0..3].
		// Note that the number [4] below could be any number >=4 ... do you see why?
		seasons = new String[4];
		
		// set values as appropriate.
		seasons[0] = "Winter";
		seasons[1] = "Spring";
		seasons[2] = "Summer";
		seasons[3] = "Fall";
		
		// canonical loop over array...
		int i = 0;
		while (i < seasons.length) {
			System.out.println (seasons[i]);
			
			i++;  // ADVANCE
		}
		
	}

}
