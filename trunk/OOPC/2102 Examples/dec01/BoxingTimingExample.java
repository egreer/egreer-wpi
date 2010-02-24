package dec01;

/**
 * Show timing issues for boxing/unboxing
 * 
 * @author heineman
 */
public class BoxingTimingExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// What is the only visible difference here?
		Integer x, y;
		int a, b;
		long start, end;
		
		start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			a = 99;
			b = 137;
			a = a + b;
		}
		end = System.currentTimeMillis();
		System.out.println ("Time required for int arithmetic: " + (end - start));
		
		
		// Is there any reason why it is 20x slower? Yes!
		start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			x = 99;     // equivalent to x = new Integer(99);
			y = 137;    // equivalent to y = new Integer(137);
			x = x + y;  // equivalent to x = new Integer (x.intValue() + y.intValue());
		}
		end = System.currentTimeMillis();
		System.out.println ("Time required for Integer arithmetic: " + (end - start));


	}

}
