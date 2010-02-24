/**
 * The package name 'oct24' declares the ownership context
 */
package oct24;

/**
 * Compute PI using various known approximations and compare against the pre-calculated
 * value of PI available to any Java program.
 * 
 * @author heineman
 */
public class ComputePi {

	/** Compute and print the value of PI */
	public static void main (String []args) {

		// Elliptic curves:
		// PI =~ log (640320^3+744)/Sqrt(163)
		double pi_e = Math.log(640320.0*640320.0*640320.0+744)/Math.sqrt(163.0);
		
		// PI can be computed by several sequences. Here is one
		// 
		//  PI = Sqrt(12) * (1 - 1/3*3 + 1/5*3^2 - 1/7*3^3 + ...)
		//
		// This was known by Madhava of Sangamagrama in the 14th century
		double pi = Math.sqrt(12.0)*(1.0 
								   - 1.0/(3.0*3.0)
								   + 1.0/(5.0*3.0*3.0)
								   - 1.0/(7.0*3.0*3.0*3.0) 
								   + 1.0/(9.0*3.0*3.0*3.0*3.0)
								   - 1.0/(1.0*3.0*3.0*3.0*3.0*3.0)
								   + 1.0/(13.0*3.0*3.0*3.0*3.0*3.0*3.0)
								   - 1.0/(15.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0)
								   + 1.0/(17.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0)
								   - 1.0/(19.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0)
								   + 1.0/(21.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0)
								   - 1.0/(23.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0*3.0));
		System.out.println (pi);   
		System.out.println (pi_e);
		System.out.println (Math.PI);
	}
}
