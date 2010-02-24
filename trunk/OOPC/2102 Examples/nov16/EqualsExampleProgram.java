package nov16;

/**
 * Shows the problems with equals(T) methods
 * 
 * @author heineman
 *
 */
public class EqualsExampleProgram {

	/**
	 * Show danger in equals
	 */
	public static void main(String[] args) {
		Item it1 = new Item ("Book", 2.0);
		BusinessItem bi1 = new BusinessItem ("Book", 2.0, 100.0);
		Item it2 = new Item ("Book", 2.0);
		BusinessItem bi2 = new BusinessItem ("Book", 2.0, 100.0);
		
		// now, we would expect it1.equals(it2)
		System.out.println ("The following is expected.");
		System.out.println ("it1.equals(it2): " + it1.equals(it2));
		System.out.println ("it2.equals(it1): " + it2.equals(it1));
		
		// now, we would expect bi1.equals(bi2)
		System.out.println ("bi1.equals(bi2): " + bi1.equals(bi2));
		System.out.println ("bi2.equals(bi1): " + bi2.equals(bi1));
		
		// what of the following:
		System.out.println ("But this is unexpected");
		System.out.println ("bi1.equals(it1): " + bi1.equals(it1));
		System.out.println ("it1.equals(bi1): " + it1.equals(bi1));
		
	}

}
