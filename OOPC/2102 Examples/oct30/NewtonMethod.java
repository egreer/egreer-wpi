package oct30;

/**
 * Show Newton's Method for finding a root.
 * 
 *    http://en.wikipedia.org/wiki/Newton%27s_method
 *   
 * For 'double' types, produces:  
 * 
 * Guess:1.1121416370972725
 * Guess:0.9096726937368068
 * Guess:0.8672638182088165
 * Guess:0.8654771352982646
 * Guess:0.8654740331109566
 * Guess:0.8654740331016144
 * Guess:0.8654740331016144
 * 
 * For 'float' types produces:
 * 
 * Float Guess:1.1121416
 * Float Guess:0.9096727
 * Float Guess:0.8672638
 * Float Guess:0.86547714
 * Float Guess:0.86547405
 * Float Guess:0.86547405
 * 
 * @author heineman
 */
public class NewtonMethod {

	/**
	 * Locate root for:
	 *    F(x)  = cos(x) - x^3
	 *    
	 * Note that
	 * 
	 *    F'(x) = -sin(x) - 3*x^2
	 */
	public static void main(String[] args) {
		// initialize Loop
		double x = 0.5;
		double xOld = 0;  // some value != x. Doesn't matter.
		
		// Stay in loop until there is no observable difference
		while (x != xOld) {
			// compute: 
			//    xNew = x - F(x)/F'(x)
			xOld = x;
			x = x - (Math.cos(x) - Math.pow(x,3)) / (-Math.sin(x) - 3*Math.pow(x,2));     
			
			// Note that above computation ADVANCES the loop
			System.out.println ("Double Guess:" + x);
		}
		
		System.out.println();
		
		// no produce for floats.
		// initialize Loop
		float xf = 0.5f;
		float xOldF = 0;  // some value != x. Doesn't matter.
		
		// Stay in loop until there is no observable difference
		while (xf != xOldF) {
			// compute: 
			//    xNew = x - F(x)/F'(x)
			xOldF = xf;
			xf = (float) (xf - (Math.cos(xf) - Math.pow(xf,3)) / (-Math.sin(xf) - 3*Math.pow(xf,2)));     
			
			// Note that above computation ADVANCES the loop
			System.out.println ("Float Guess:" + xf);
		}
	}

}
