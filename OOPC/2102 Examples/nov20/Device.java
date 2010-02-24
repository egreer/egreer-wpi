package nov20;

/**
 * Implements a rudimentary encryption device.
 * 
 * An encryption starts with a plain text String in capital letters 
 * (i.e., "THE PARTY WILL BE AT THE GRAVEYARD AT MIDNIGHT TELL NO ONE" 
 * and processes a sequence of Encryptions over the text. You must support
 * the following encryptions:
 * 
 * [REV] Reverse the text
 * [SWAP] swap (A,Z), (B,Y), (C,X) ... (L, N) leaving M characters unchanged
 * [ROTVOWELS] Rotate vowels (A -> E, E -> I, I -> O, O -> U, U -> A)
 * [ROT13] Rotate13 the text (A -> N, B -> O, C -> P, ..., M -> Z, N -> A, ...)
 * [LROT13] Rotate13 the text in the reverse order 
 * 
 * Your encryption device must allow one to add any number of encryptions.
 * Then, when you call method String encrypt(String plainText) an
 * encrypted String value is returned. 
 * 
 * For extra credit, write a String descrypt (String encryptedText) in the
 * same class, when given an encrypted String.
 *
 * @author heineman@cs.wpi.edu
 */
public class Device {

	/** First of the encryptions. */
	EncryptionNode head;
	
	public Device() {
		head = null;
	}
	
	/**
	 * Add an encryption at the end of the list.
	 * 
	 * @param e    Encryption to be added
	 */
	public void add (Encryption e) {
		if (head == null) {
			head = new EncryptionNode (e);
		} else {
			EncryptionNode node = head;
			
			// find the last node in the list.
			while (node.next != null) {
				node = node.next;
			}
			
			node.next = new EncryptionNode (e);
		}
	}
	
	/**
	 * Process a set of encryptions over the text and return the encrypted value.
	 * 
	 * @param plain    text to start
	 * @return         encrypted value.
	 */
	public String confuseText (String plain) {
		EncryptionNode node = head;
		while (node != null) {
			// calculate next text to be used as input
			Encryption e = node.process;
			plain = e.encrypt(plain); 
			
			node = node.next;   // ADVANCE
		}
		
		return plain;
	}
}
