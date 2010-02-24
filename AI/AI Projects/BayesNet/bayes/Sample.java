package bayes;

/** A samnple from the network, each variable gets set individually.  
 * 
 * @author Eric Greer
 *
 * 0 = not set
 * 1 = true 
 * 2 = false
 *
 */
public class Sample {
	int a;
	int b;
	int c;
	int d;
	
	Sample(int a, int b, int c, int d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	
	/** Returns the value of the variable of the given character.
	 * 
	 * @param which		The variable to look for
	 * @return			Returns 0, 1, 2 or The minimum value of an integer for invalid query 
	 */
	int valueOf(char which){
		int returner = Integer.MIN_VALUE;
		
		if (which == 'a') returner = this.a;
		if (which == 'b') returner = this.b;
		if (which == 'c') returner = this.c;
		if (which == 'd') returner = this.d;
		
		return returner;
	}
	
	/**Checks object for a valid solution given the solution.
	 * 
	 * @param solution  The Sample to compare to 
	 * @return			Returns true if the object is a valid solution 
	 */
	boolean valid(Sample solution){
		boolean returner = true;
		
		//Check A
		if(returner){
			if (solution.a == 0);
			else if(solution.a != this.a) returner = false;
		}
		
		//Check B
		if(returner){
			if (solution.b == 0);
			else if(solution.b != this.b) returner = false;
		}
		//Check C
		if(returner){
			if (solution.c == 0);
			else if(solution.c != this.c) returner = false;
		}
		//Check D
		if(returner){
			if (solution.d == 0);
			else if(solution.d != this.d) returner = false;
		}
		
		return returner;
	}
}
