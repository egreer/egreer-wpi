package brlandry;

import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Stack;


/**A GrandfatherDeck is a Deck with the following cards on top of the deck
 * <TABLE>
 * <THEAD>
 *   <TR>
 *	   <TH>Position</TH>
 *	   <TH>Card</TH>
 *	 </TR>
 * </THEAD> 
 * <TBODY>
 *   <TR>
 *     <TH>1</TH>
 *     <TH>10 of Hearts</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Jack of Spades</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Queen of Diamonds</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>King of Clubs</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Two of Hearts</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Three of Spades</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Four of Diamonds</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Five of Clubs</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Six of Hearts</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Seven of Spades</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Eight of Diamonds</TH>
 *   </TR>
 *	 <TR>
 *     <TH>1</TH>
 *     <TH>Nine of Clubs</TH>
 *   </TR>
 * </TBODY>
 * </TABLE>
 */
public class GrandfatherDeck extends Deck{
	public GrandfatherDeck(String name){
		super(name);
	}
	
	/**Populate the deck
	 * @param seed int - a number used to determine the ordering of the deck
	 */
	public void create(int seed){
		Stack temp = new Stack();
		Card[] special = new Card[12];
		Card c;
		
		//First create a standard deck
		super.create(seed);
		//Take a card from the deck.  If it should be on a foundation,
		//place it in special.
		c = this.get();
		while(c != null){
			switch(c.getSuit()){
			case Card.CLUBS:
					switch(c.getRank()){
					case Card.NINE:special[0]=c; break;
					case Card.KING:special[4]=c; break;
					case Card.FIVE:special[8]=c; break;
					default: temp.add(c);
					}
					break;
			case Card.HEARTS:
					switch(c.getRank()){
					case Card.TEN:special[1]=c; break;
					case Card.TWO:special[5]=c; break;
					case Card.SIX:special[9]=c; break;
					default: temp.add(c);
					}
					break;
			case Card.SPADES:
					switch(c.getRank()){
					case Card.JACK:  special[2]=c; break;
					case Card.THREE: special[6]=c; break;
					case Card.SEVEN: special[10]=c; break;
					default: temp.add(c);
					}
					break;
			case Card.DIAMONDS:
					switch(c.getRank()){
					case Card.QUEEN: special[3]=c; break;
					case Card.FOUR:  special[7]=c; break;
					case Card.EIGHT: special[11]=c; break;
					default: temp.add(c);
					}
					break;
			}
			c = this.get();
		}
		c = temp.get();
		while(c != null){
			this.add(c);
			c = temp.get();
		}
		//push the cards for the foundation back onto the deck
		for(int j=11; j>=0; j--)
			this.add(special[j]);
	}
}
			