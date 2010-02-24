package egreer.lab3;

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
	 * Find the sequence of 'additions' that will 
	 *
	 * @author USERNAME
	 */
	public void testYourCode() {
		FunctionList list = new FunctionList();

		Function id1 = new Function();         	// the identify function  F(x) = x
		Function id2 = new TripleFunction();   	// the triple function    G(x) = x*3
		Function id3 = new DivideByFunction(2); // the divide by function H(x) = x/2
		
		list.add(id1);    // now F(x)
		assertEquals(10, list.compute(10));
		
		list.add(id2);    // now G(F(x))
		assertEquals(30, list.compute(10));
		
		list.add(id3);    // now H(G(F(x)))    
		assertEquals(15, list.compute(10));	
	}
}
