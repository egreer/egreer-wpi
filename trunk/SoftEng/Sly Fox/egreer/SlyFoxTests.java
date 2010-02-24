package egreer;

import java.awt.event.MouseEvent;
import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.view.Widget;
import ks.launcher.Main;
import ks.tests.model.ModelFactory;

/** Implements testing of the Sly Fox solitaire game.
 * 
 * @author Eric Greer
 *
 */
public class SlyFoxTests extends TestCase {
	
	//MouseEvent Methods created by George Heineman
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createPressed (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createReleased (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}

	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createClicked (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 1, false);
		return me;
	}
	
	public void testInitialDeal() {
		// Seed is to ensure we get the same initial cards every time.
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
		
		assertFalse (sf.pile1.empty());
		assertFalse (sf.pile2.empty());
		assertFalse (sf.pile3.empty());
		assertFalse (sf.pile4.empty());
		assertFalse (sf.pile5.empty());
		assertFalse (sf.pile6.empty());
		assertFalse (sf.pile7.empty());
		assertFalse (sf.pile8.empty());
		assertFalse (sf.pile8.empty());
		assertFalse (sf.pile9.empty());
		assertFalse (sf.pile10.empty());
		assertFalse (sf.pile11.empty());
		assertFalse (sf.pile12.empty());
		assertFalse (sf.pile13.empty());
		assertFalse (sf.pile14.empty());
		assertFalse (sf.pile15.empty());
		assertFalse (sf.pile16.empty());
		assertFalse (sf.pile17.empty());
		assertFalse (sf.pile18.empty());
		assertFalse (sf.pile19.empty());
		assertFalse (sf.pile20.empty());
		
		assertTrue(sf.reserveDeck.empty());
	
		
		//Checks to make sure the correct cards are on the Left foundation
		Card testCard = new Card(1, 1);
		
		assertTrue( sf.flpile1.peek().sameRank(testCard));
		assertTrue( sf.flpile1.peek().sameSuit(testCard));

		testCard = new Card(1, 2);
		
		assertTrue( sf.flpile2.peek().sameRank(testCard));
		assertTrue( sf.flpile2.peek().sameSuit(testCard));	
	
		testCard = new Card(1, 3);
		
		assertTrue( sf.flpile3.peek().sameRank(testCard));
		assertTrue( sf.flpile3.peek().sameSuit(testCard));	
	
		testCard = new Card(1, 4);
		
		assertTrue( sf.flpile4.peek().sameRank(testCard));
		assertTrue( sf.flpile4.peek().sameSuit(testCard));	
	
		//Checks to make sure the correct cards are on the Right foundation
		testCard = new Card(13, 1);
		
		assertTrue( sf.frpile1.peek().sameRank(testCard));
		assertTrue( sf.frpile1.peek().sameSuit(testCard));

		testCard = new Card(13, 2);
		
		assertTrue( sf.frpile2.peek().sameRank(testCard));
		assertTrue( sf.frpile2.peek().sameSuit(testCard));	
	
		testCard = new Card(13, 3);
		
		assertTrue( sf.frpile3.peek().sameRank(testCard));
		assertTrue( sf.frpile3.peek().sameSuit(testCard));	
	
		testCard = new Card(13, 4);
		
		assertTrue( sf.frpile4.peek().sameRank(testCard));
		assertTrue( sf.frpile4.peek().sameSuit(testCard));		
	
		//Checks the phase
		assertTrue(sf.getPhase() == 1);
	}
	
	//Test moving a card to the left
	public void testMoveCardLeftMove() {
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);

			
		//Test the actual move
		ModelFactory.init(sf.pile1, "5S 2C");
		ModelFactory.init(sf.pile2, "7C 6S");
		ModelFactory.init(sf.flpile1, "AC");
		
		assertEquals ("2C", sf.pile1.peek().toString());
		assertEquals ("6S", sf.pile2.peek().toString());
		assertEquals ("AC", sf.flpile1.peek().toString());
		
		
		Card movingCard = new Card(2,1);
		
		MoveCardLeftMove mclm = new MoveCardLeftMove(sf.pile2, movingCard, sf.flpile1);
		assertTrue (mclm.valid(sf));
		
		// make move.
		assertTrue (mclm.doMove(sf));
		
		//checks piles
		assertEquals ("6S", sf.pile2.peek().toString());
		assertEquals ("2C", sf.flpile1.peek().toString());

		// undo move
		assertTrue (mclm.undo(sf));
		assertEquals ("2C", sf.pile2.peek().toString());
		assertEquals ("AC", sf.flpile1.peek().toString());
		
		
		//Try to do invalid move with rank
		movingCard = new Card(4,1);
		assertEquals ("AC", sf.flpile1.peek().toString());
		
		mclm = new MoveCardLeftMove(sf.pile2, movingCard, sf.flpile1);
		assertFalse (mclm.doMove(sf));
		assertFalse (mclm.valid(sf));
		assertEquals ("AC", sf.flpile1.peek().toString());
		
		//Try to do invalid move with suit
		movingCard = new Card(2,2);	
		assertEquals ("AC", sf.flpile1.peek().toString());
		
		mclm = new MoveCardLeftMove(sf.pile2, movingCard, sf.flpile1);
		assertFalse (mclm.doMove(sf));
		assertFalse (mclm.valid(sf));
		assertEquals ("AC", sf.flpile1.peek().toString());
		
		//Test undo when pile is empty
		movingCard = new Card(2,1);
		mclm = new MoveCardLeftMove(sf.pile2, movingCard, sf.flpile1);
		assertTrue (mclm.valid(sf));
		sf.flpile1.removeAll();
		assertFalse(mclm.undo(sf));	
		

	}
	
	//Test Moving a card to the Right
	public void testMoveCardRightMove() {
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);

			
		//Test the actual move
		ModelFactory.init(sf.pile1, "5S QC");
		ModelFactory.init(sf.pile2, "7C 6S");
		ModelFactory.init(sf.frpile1, "KC");
		
		assertEquals ("QC", sf.pile1.peek().toString());
		assertEquals ("6S", sf.pile2.peek().toString());
		assertEquals ("KC", sf.frpile1.peek().toString());
		
		
		Card movingCard = new Card(12,1);
		
		MoveCardRightMove mcrm = new MoveCardRightMove(sf.pile2, movingCard, sf.frpile1);
		assertTrue (mcrm.valid(sf));
		
		// make move.
		assertTrue (mcrm.doMove(sf));
		
		//checks piles
		assertEquals ("6S", sf.pile2.peek().toString());
		assertEquals ("QC", sf.frpile1.peek().toString());

		// undo move
		assertTrue (mcrm.undo(sf));
		assertEquals ("QC", sf.pile2.peek().toString());
		assertEquals ("KC", sf.frpile1.peek().toString());
		

		

		//Try to do invalid move with rank
		movingCard = new Card(11,1);
		assertEquals ("KC", sf.frpile1.peek().toString());
		
		mcrm = new MoveCardRightMove(sf.pile2, movingCard, sf.frpile1);
		assertFalse (mcrm.doMove(sf));
		assertFalse (mcrm.valid(sf));
		assertEquals ("KC", sf.frpile1.peek().toString());
		
		//Try to do invalid move with suit
		movingCard = new Card(12,2);	
		assertEquals ("KC", sf.frpile1.peek().toString());
		
		mcrm = new MoveCardRightMove(sf.pile2, movingCard, sf.frpile1);
		assertFalse (mcrm.doMove(sf));
		assertFalse (mcrm.valid(sf));
		assertEquals ("KC", sf.frpile1.peek().toString());

		//Test undo when pile is empty
		movingCard = new Card(12,1);
		mcrm = new MoveCardRightMove(sf.pile2, movingCard, sf.frpile1);
		assertTrue (mcrm.valid(sf));
		sf.frpile1.removeAll();
		assertFalse(mcrm.undo(sf));		
		

		
	}
	
	//Tests dealing cards to reserve
	public void testDealMove(){
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
		
		//Test move
		DealMove dm = new DealMove(sf.deck, sf.reserveDeck);
		assertTrue(dm.valid(sf));
		
		assertTrue(dm.doMove(sf));
		assertTrue(sf.reserveDeck.count() == 20);
		assertTrue(dm.undo(sf));
		
		//Test undo when all cards aren't back on the reserve
		assertTrue(dm.doMove(sf));
		sf.reserveDeck.get();
		assertFalse(dm.undo(sf));
		
		//Test Domove when reserve is not empty
		sf.reserveDeck.add(new Card(1, 1));
		assertFalse(dm.doMove(sf));
	}

	//Tests Moving a reserveDeck card to a reserve column 
	public void testMoveReserveCardMove(){
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
		
		
		ModelFactory.init(sf.reserveDeck, "5S 7C");
		ModelFactory.init(sf.pile1, "4D");
		ModelFactory.init(sf.flpile1, "QD");
		assertEquals("7C", sf.reserveDeck.peek().toString());
		assertEquals("4D", sf.pile1.peek().toString());
		assertEquals("QD", sf.flpile1.peek().toString());
		
		Card movingCard = new Card(12,1);
		
		//Test move
		MoveReserveCardMove mrcm = new MoveReserveCardMove(sf.reserveDeck, movingCard, sf.pile1);
		assertTrue(mrcm.valid(sf));
		
		assertTrue(mrcm.doMove(sf));
		assertEquals(2, sf.reserveDeck.count());
		assertEquals("QC", sf.pile1.peek().toString());
		
		assertTrue(mrcm.undo(sf));
		
		assertEquals("QC", sf.reserveDeck.peek().toString());
		assertEquals("4D", sf.pile1.peek().toString());
		

		//Tests moving card from a pile that is not reserve.
		mrcm = new MoveReserveCardMove(sf.flpile1, movingCard, sf.pile1);
		assertFalse(mrcm.doMove(sf));
		assertFalse(mrcm.valid(sf));

		//Tests moving card from a pile that is not reserve.
		mrcm = new MoveReserveCardMove(sf.reserveDeck, movingCard, sf.pile1);
		assertTrue(mrcm.doMove(sf));
		sf.pile1.removeAll();
		assertFalse(mrcm.undo(sf));
	}
	
	//Test the Restock of an empty reserve column
	public void testRestockEmptyReserveMove(){
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
		
		ModelFactory.init(sf.deck, "5S 7C");
		
		RestockEmptyReserveMove rerm = new RestockEmptyReserveMove (sf.deck, sf.pile1, sf.getPhase());
		assertTrue(rerm.valid(sf));
		assertTrue(rerm.doMove(sf));
		assertEquals("7C", sf.pile1.peek().toString());
		assertTrue(rerm.undo(sf));
		
		//Test undo when pile is empty
		rerm = new RestockEmptyReserveMove (sf.deck, sf.pile1, sf.getPhase());
		assertTrue(rerm.doMove(sf));
		sf.pile1.removeAll();
		assertFalse(rerm.undo(sf));		
		
		// Makes sure doesn't work in phase 2
		sf.setPhase(2);
		rerm = new RestockEmptyReserveMove (sf.deck, sf.pile1, sf.getPhase());
		assertFalse(rerm.doMove(sf));
	
	}
	
	//Tests the controller for the deckView Object
	public void testDeckController(){
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
		
		ModelFactory.init(sf.deck, "AC 5S 6D 2D 3D 4D 5D 6D 7D KC");
		
		assertEquals("KC", sf.deck.peek().toString());
		
		MouseEvent pres = createPressed (sf, sf.deckView, 0, 0);
		sf.deckView.getMouseManager().handleMouseEvent(pres);
		
		assertEquals("AC", sf.reserveDeck.peek().toString());
	}
	
	//Tests moving cards from the reserve column to a foundation using the controller 
	public void testColumnFoundationInteraction(){
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
		
		
		//Tests going to the left
		ModelFactory.init(sf.pile1, "2C");	
		ModelFactory.init(sf.flpile1, "AC");
		
		MouseEvent pres = createPressed(sf, sf.cv1, 0, 0);
		sf.cv1.getMouseManager().handleMouseEvent(pres);
		
		MouseEvent real = createReleased(sf, sf.flpv1, 0, 0);
		sf.flpv1.getMouseManager().handleMouseEvent(real);
		
		assertEquals("2C", sf.flpile1.peek().toString());
		assertTrue(sf.pile1.empty());
	
			
		//Tests going to the Right	
		ModelFactory.init(sf.pile2, "QC");
		ModelFactory.init(sf.frpile1, "KC");
		
		pres = createPressed(sf, sf.cv2, 0, 0);
		sf.cv2.getMouseManager().handleMouseEvent(pres);
		
		real = createReleased(sf, sf.frpv1, 0, 0);
		sf.frpv1.getMouseManager().handleMouseEvent(real);
		
		assertEquals("QC", sf.frpile1.peek().toString());
		assertTrue(sf.pile2.empty());
		
		
		//Tests an invalid move
		ModelFactory.init(sf.pile3, "4S");
		
		pres = createPressed(sf, sf.cv3, 0, 0);
		sf.cv3.getMouseManager().handleMouseEvent(pres);
		
		real = createReleased(sf, sf.frpv1, 0, 0);
		sf.frpv1.getMouseManager().handleMouseEvent(real);
		
		assertEquals("QC", sf.frpile1.peek().toString());
		assertEquals("4S", sf.pile3.peek().toString());
		
	}
	
	//Tests Moving a card from the reserveDeck to a reserve column using the controller
	public void testReserveColumnInteraction(){
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
		
		ModelFactory.init(sf.reserveDeck, "KH QD");
		ModelFactory.init(sf.pile1, "4S");
		
		
		MouseEvent pres = createPressed(sf, sf.reserveView, 0, 0);
		sf.reserveView.getMouseManager().handleMouseEvent(pres);
		
		MouseEvent real = createReleased(sf, sf.cv1, 0, 0);
		sf.cv1.getMouseManager().handleMouseEvent(real);
		
		assertEquals("QD", sf.pile1.peek().toString());
		assertEquals("KH", sf.reserveDeck.peek().toString());
	}
		
	//Tests restocking the reserve in phase 1 
	public void testColumnController(){
		SlyFox sf = new SlyFox();
		GameWindow gw = Main.generateWindow(sf, 117);
			
		sf.setPhase(1);
		assertEquals(1, sf.getPhase());
		
		ModelFactory.init(sf.deck, "KH QD");
		ModelFactory.init(sf.pile1, "4S");
		
		sf.pile1.removeAll();
		assertTrue(sf.pile1.empty());
		
		
		MouseEvent clk = createClicked(sf, sf.cv1, 0, 0);
		sf.cv1.getMouseManager().handleMouseEvent(clk);
		
		assertEquals("QD", sf.pile1.peek().toString());
				
		
	}
}
