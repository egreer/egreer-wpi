package nov20;

public class EncryptionNode {

	/** Encryption. */
	Encryption    process;
	
	/** Next one. */
	EncryptionNode   next;
	
	public EncryptionNode (Encryption e) {
		this.process = e;
		this.next = null;
	}

}
