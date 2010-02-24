package ks.tests.model;

import junit.framework.TestCase;
import ks.common.model.Card;

public class CardTests extends TestCase {

	// This ensures 46.4% coverage.
	public void testCardBasics() {
		Card c = new Card(7,Card.CLUBS);
		assertEquals ("7C", c.toString());
	
		Card c2 = new Card (7,Card.CLUBS);
		
		Card c3 = new Card (10, Card.DIAMONDS);
		assertTrue ( c.compareTo(c3) < 0 );
		assertEquals (0, c.compareTo(c2));
		
		assertEquals (7, c.getRank());
		assertEquals (Card.CLUBS, c.getSuit());
		
		assertFalse (c.isAce());
		assertFalse (c.isFaceCard());
		assertTrue (c.isFaceUp());
		assertFalse (c.isSelected());
		assertTrue (c.oppositeColor(c3));
		assertTrue (c.oppositeColor(Card.HEARTS));
		assertFalse(c.sameColor(c3));
		assertTrue (c.sameColor(Card.SPADES));
		assertTrue (c.sameRank(c2));
		assertFalse (c.sameSuit(c3));
		
		c3.setFaceUp(false);
		assertFalse (c3.isFaceUp());
		
		c3.setSelected(true);
		assertTrue(c3.isSelected());
	}
	
	// How much effort to make it to 80% And how important is this to you?
	// update to 55.1%
	public void testSecondReview() {
		Card cards[] = { new Card(7,Card.CLUBS), new Card(5, Card.SPADES) };
		Card cards2[] = { new Card(7,Card.DIAMONDS), new Card(5, Card.HEARTS) };
		for (Card c : cards) {
			for (Card c2 : cards) {
				assertFalse (c.oppositeColor(c2));
				assertFalse (c2.oppositeColor(c));
				assertFalse (c.oppositeColor(c2.getSuit()));
				assertFalse (c2.oppositeColor(c.getSuit()));
				
				
				assertTrue (c.sameColor(c2));
				assertTrue (c2.sameColor(c));
				assertTrue (c.sameColor(c2.getSuit()));
				assertTrue (c2.sameColor(c.getSuit()));
			}
		}

		for (Card c : cards2) {
			for (Card c2 : cards2) {
				assertFalse (c.oppositeColor(c2));
				assertFalse (c2.oppositeColor(c));
				assertFalse (c.oppositeColor(c2.getSuit()));
				assertFalse (c2.oppositeColor(c.getSuit()));
				
				
				assertTrue (c.sameColor(c2));
				assertTrue (c2.sameColor(c));
				assertTrue (c.sameColor(c2.getSuit()));
				assertTrue (c2.sameColor(c.getSuit()));
			}
		}
		
		for (Card c : cards) {
			for (Card c2 : cards2) {
				assertTrue (c.oppositeColor(c2));
				assertTrue (c2.oppositeColor(c));
				assertTrue (c.oppositeColor(c2.getSuit()));
				assertTrue (c2.oppositeColor(c.getSuit()));
				
				assertFalse (c.sameColor(c2));
				assertFalse (c2.sameColor(c));
				assertFalse (c.sameColor(c2.getSuit()));
				assertFalse (c2.sameColor(c.getSuit()));
			}
		}

	}


}
