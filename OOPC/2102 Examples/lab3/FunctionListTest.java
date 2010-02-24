package lab3;

import junit.framework.TestCase;

public class FunctionListTest extends TestCase {
	
	/**
	 * These tests should run *as is*. 
	 *
	 * @author heineman
	 */
	public void testSimpleExample () {
		FunctionList list = new FunctionList();
		
		Function id1 = new Function();         // the identify function F(x) = x
		Function id2 = new SquareFunction();   // the square function   G(x) = x^2
		Function id3 = new SquareFunction();   // again square          H(x) = x^2
		
		list.add(id1);    // now F(x)
		assertEquals(10, list.compute(10));
		
		list.add(id2);    // now G(F(x))
		assertEquals(100, list.compute(10));
		
		list.add(id3);    // now H(G(F(x)))    
		assertEquals(10000, list.compute(10));
	}
	
	/**
	 * Satisfy that timesSix works.
	 *
	 * @author heineman
	 */
	public void testTimesSix () {
		FunctionList list = new FunctionList();
		
		Function id1 = new TimesSixFunction();   // the *6 function   G(x) = 6*x
		
		list.add(id1);
		
		assertEquals (60, list.compute(10));
	}
	
	/**
	 * Find the sequence of 'additions' that will 
	 *
	 * @author heineman
	 */
	public void testYourCode() {
		FunctionList list = new FunctionList();
		
		Function id1 = new SquareFunction();   // the square function   G(x) = x^2
		Function id2 = new TripleFunction();   // the square function   F(x) = 3*x
		Function id3 = new DivideByFunction(2);//                       H(x) = x/2
		
		// insert your code here
		// 8 -> 64 - > 32 -> 96
		list.add(id1);
		list.add(id3);
		list.add(id2);
		
		assertEquals (96, list.compute(8));
	}
}
