package ks.tests.model;

import junit.framework.TestCase;
import ks.common.model.Card;

public class CardFactoryTest extends TestCase {
	public void testCard() {
		Card c = ModelFactory.fromString("10S");
		assertEquals (10, c.getRank());
		assertEquals (Card.SPADES, c.getSuit());
		
		c = ModelFactory.fromString("AH");
		assertEquals (1, c.getRank());
		assertEquals (Card.HEARTS, c.getSuit());
		
		c = ModelFactory.fromString("KC");
		assertEquals (13, c.getRank());
		assertEquals (Card.CLUBS, c.getSuit());
		
		c = ModelFactory.fromString("QD");
		assertEquals (12, c.getRank());
		assertEquals (Card.DIAMONDS, c.getSuit());
		
		c = ModelFactory.fromString("JD");
		assertEquals (11, c.getRank());
		assertEquals (Card.DIAMONDS, c.getSuit());
		
		c = ModelFactory.fromString("2D");
		assertEquals (2, c.getRank());
		assertEquals (Card.DIAMONDS, c.getSuit());
		
		c = ModelFactory.fromString("9D");
		assertEquals (9, c.getRank());
		assertEquals (Card.DIAMONDS, c.getSuit());
	}
}
