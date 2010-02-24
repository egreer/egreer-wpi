package tests;

import junit.framework.TestCase;
import ks.common.model.Card;
import ks.common.model.Stack;
import ks.tests.model.ModelFactory;

public class StackTests extends TestCase {

	
	public void testAdd() {
		Stack s =  new Stack();
		assertTrue (s.empty());
		
		//Card c = ModelFactory.fromString("KD");
		Card c = new Card (Card.KING, Card.DIAMONDS);
		s.add(c);
		
		assertFalse (s.empty());
	}
	
	public void testBadAdd() {
		try {
			Card c = new Card (99, 99);
			fail ("Missed!");
		} catch (IllegalArgumentException iae) {
			
		}
		
		
	}

}
