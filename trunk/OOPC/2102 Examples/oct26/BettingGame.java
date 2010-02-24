package oct26;

/**
 * Compute the number of ways that I can select k students from a population 
 * of size n. 
 * 
 *    AM class size: 112     I ask for 18 names
 *    PM class size:  55     I ask for 9 names
 * 
 * @author George
 *
 */
public class BettingGame {

	/**
	 * Compute the probability using statistics
	 */
	public static void main(String[] args) {
		
		/**
		 * Need to know C(18) which in mathematics means "from 112 choose 18"
		 * 
		 * The probability is calculated as:
		 * 
		 * 
		 *         112 * 111 * 110 * ... * 96 * 95
		 *   # =  ---------------------------------
		 *          18 * 17 * 16 * 15 * ... * 2 * 1
		 *          
		 */
		
		int n = 112*111*110*109*108*107*106*105*104*103*102*101*100*99*98*97*96*95;
		int d = 18*17*16*15*14*13*12*11*10*9*8*7*6*5*4*3*2*1;
		
		int num = n/d;
		
		// Can you explain why the result is not what you expected?
		System.out.println (num);
		
		/**
		 * Need to know C(18) which in mathematics means "from 112 choose 18"
		 * 
		 * The probability is calculated as:
		 * 
		 * 
		 *         55 * 54 * 53 * 52 * 51 * 50 * 49 * 48 * 47
		 *   # =  --------------------------------------------
		 *                  9 * 8 * 7 * ... * 2 * 1
		 *          
		 */
		
		n = 55*54*53*52*51*50*49*48*47;
		d = 9*8*7*6*5*4*3*2*1;
		
		num = n/d;
		
		System.out.println (num);
		
	}

}
