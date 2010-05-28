package simpledb.tx.concurrency;

import simpledb.file.Block;

import java.util.*;

/**
 * The lock table, which provides methods to lock and unlock blocks.
 * If a transaction requests a lock that causes a conflict with an
 * existing lock, then that transaction is placed on a wait list.
 * There is only one wait list for all blocks.
 * When the last lock on a block is unlocked, then all transactions
 * are removed from the wait list and rescheduled.
 * If one of those transactions discovers that the lock it is waiting for
 * is still locked, it will place itself back on the wait list.
 * @author Edward Sciore
 * @author Eric Greer
 * @since	CS4432-Project3
 */
class LockTable {

	private Map<Block,Lock> locks = new HashMap<Block,Lock>();
	private LockPolicy policy = new WaitDiePolicy(); 

	/**
	 * Grants an SLock on the specified block.
	 * If an XLock exists when the method is called,
	 * then the calling thread will be placed on a wait list
	 * until the lock is released.
	 * If the thread remains on the wait list for a certain 
	 * amount of time (currently 10 seconds),
	 * then an exception is thrown.
	 * @param blk a reference to the disk block
	 * @since	CS4432-Project3
	 */
	public synchronized void sLock(Block blk, int txnum) {
		Lock l = getLockVal(blk);
		try {
			if(policy.slock(l, txnum)){
				//val.slock(txnum);
				locks.put(blk, l);  
			}
			else {
				while (!(l.hasLock(txnum) && l.getType() == 1)) wait(5); // Wait until this has a locks
				System.out.println(l.toString() + " | Tx: " + txnum + " has " +l.getType() + " lock after waiting.");
				locks.put(blk, l);
			}
		} catch (InterruptedException e) {
			throw new LockAbortException();
		}
	}

	/**
	 * Grants an XLock on the specified block.
	 * If a lock of any type exists when the method is called,
	 * then the calling thread will be placed on a wait list
	 * until the locks are released.
	 * If the thread remains on the wait list for a certain 
	 * amount of time (currently 10 seconds),
	 * then an exception is thrown.
	 * @param blk a reference to the disk block
	 * @since CS4432-Project2
	 */
	synchronized void xLock(Block blk , int txnum) {
			Lock l = getLockVal(blk);
			try {
				if(policy.xlock(l, txnum)){
					locks.put(blk, l);
				} else {
					while (!(l.hasLock(txnum) && l.getType() == -1)) wait(5); // Wait until this has a locks
					System.out.println(l.toString() + " | Tx: " + txnum + " has " +l.getType() + " lock after waiting.");
					locks.put(blk, l);
				}
			} catch (InterruptedException e) {
				throw new LockAbortException();
			}
	}

	/**
	 * Releases a lock on the specified block.
	 * If this lock is the last lock on that block,
	 * then the waiting transactions are notified.
	 * @param blk a reference to the disk block
	 * @since CS4432-Project2
	 */
	synchronized void unlock(Block blk , int txnum) {
		Lock val = getLockVal(blk);
		if (val.numberTxs() > 0){
			val.unlock(txnum);
			locks.put(blk, val); 
			notifyAll();
		}
		else {
			locks.remove(blk);
			notifyAll();
		}
	}

	/** Checks to see if the sLock exists on the block
	 * 
	 * @param blk	The block to check
	 * @param txnum	The txnumber to check
	 * @return		True if the tx has a lock on the block
	 * @since	CS4432-Project3
	 */
	public synchronized boolean hasSLock(Block blk, int txnum) {
		Lock ival = locks.get(blk);
		if (ival == null){
			return false;
		}else{
			if (ival.getType() == 1) return ival.hasLock(txnum);
			else return false;
		}
	}
	
	/** Checks to see if the xLock exists on the block
	 * 
	 * @param blk	The block to check
	 * @param txnum	The txnumber to check
	 * @return		True if the tx has a lock on the block
	 * @since	CS4432-Project3
	 */
	public synchronized boolean hasXLock(Block blk, int txnum){
		Lock ival = locks.get(blk);
		if (ival == null){
			return false;
		}else{
			if (ival.getType() == -1) return ival.hasLock(txnum);
			else return false;
		}
	}
	
	/** Returns the lock type for the block (-1 xLock, 0 no lock, 1 sLock) 
	 * 
	 * @param blk	The block to check
	 * @return		The lock type on the block (-1 xLock, 0 no lock, 1 sLock)
	 * @since	CS4432-Project3
	 */
	public int getLockType(Block blk) {
		Lock ival = locks.get(blk);
		return (ival == null) ? (new Lock()).getType() : ival.getType();
	}
	
	
	/** Obtains the lock from the lock table
	 * 
	 * @param blk	The block to get the lock type for 
	 * @return		The Locks
	 * @since	CS4432-Project3
	 */
	private Lock getLockVal(Block blk) {
		Lock ival = locks.get(blk);
		return (ival == null) ? new Lock() : ival;
	}
}
