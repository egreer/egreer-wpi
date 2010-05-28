package simpledb.tx.concurrency;

/** The interface all policies use to determine if a tx should get the specified lock
 * 
 * @author Eric Greer
 * @since	CS4432-Project3
 */
public interface LockPolicy {

	/** The policy to see if the program should allow the transaction to get an slock 
	 * 
	 * @param l			The lock the requester is trying to obtain
	 * @param txnum		The txnum of the requester
	 * @return			True if it can  take the lock immediately
	 * 					False if it needs  to wait for the lock 
	 * @throws LockAbortException	If it canont wait for the lock, and cannont get the lock
	 * @since	CS4432-Project3
	 */
	public boolean slock(Lock l, int txnum) throws LockAbortException;

	/** The policy to see if the program should allow the transaction to get an xlock 
	 * 
	 * @param l			The lock the requester is trying to obtain
	 * @param txnum		The txnum of the requester
	 * @return			True if it can  take the lock immediately
	 * 					False if it needs  to wait for the lock 
	 * @throws LockAbortException	If it canont wait for the lock, and cannont get the lock
	 * @since	CS4432-Project3
	 */	public boolean xlock(Lock l, int txnum) throws LockAbortException;

	 /** Updates the policy with the unlocked information 
	  * 
	  * @param l		The lock being unlocked
	  * @param txnum	The txnum being removed from the lock
	  */
	public void unlock(Lock l, int txnum);

}
