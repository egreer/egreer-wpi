package nov07;

/**
 * Throughout our discussions on arrays, we have been using zero-based indexing as the
 * standard by which we access elements in an array.
 * 
 * This is as it should be, yet there is an interesting twist that you will see in my
 * solution for HW2. Specifically, HW2-problem2 asked to keep a count of the number of
 * 'a', 'c', 't', and 'g' characters seen in an input string.
 * 
 * We make the decision to create an array, and that we will define:
 * 
 *    count[0]  as the number of 'a' characters seen
 *    count[1]  as the number of 'c' characters seen
 *    count[2]  as the number of 't' characters seen
 *    count[3]  as the number of 'g' characters seen    
 * 
 * But we need to, then, write some code that "converts" an 'a' to a 0, a 'c' to a 1, a
 * 't' to a 2, and a 'g' to a 3.  Here is what we can do:
 * 
 * Consider the variable: String bases = "actg".
 * 
 *   bases.indexOf('a') is 0
 *   bases.indexof('c') is 1
 *   bases.indexOf('t') is 2
 *   bases.indexOf('g') is 3.
 *   
 * Note that this works well because each of the values is a single character!
 *
 * @author George
 *
 */
public class IndexingExample {

	/**
	 * Launch small example.
	 */
	public static void main(String[] args) {
		String bases = "actg";
		
		char ch = 't';
		
		int a_idx = bases.indexOf(ch);
		
		System.out.println ("Can map \'" + ch + "\' into " + a_idx);

	}

}
