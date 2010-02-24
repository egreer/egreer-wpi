package nov03;

public class SayHello {
	
	/**
	 * Just say Hello, World.
	 */
	public static void hello () {
		System.out.println ("Hello, World.");
	}
	
	public static void main (String []args) {
		// invoke the static method defined within this class
		// Name methods so you understand what is happening.
		hello();
		
		// Think about what you have just done. In some respect, you have "extended"
		// the Java language to have a new statement that outputs "Hello, World" whenever
		// you want. No matter how trivial this sounds, you have just witnessed the true
		// power of programming.
	}
}
