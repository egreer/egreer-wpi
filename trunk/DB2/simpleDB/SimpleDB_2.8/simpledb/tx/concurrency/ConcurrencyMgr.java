package simpledb.tx.concurrency;

import simpledb.file.Block;
import java.util.*;

/**
 * The concurrency manager for the transaction.
 * Each transaction has its own concurrency manager. 
 * The concurrency manager keeps track of which locks the 
 * transaction currently has, and interacts with the
 * global lock table as needed. 
 * @author Edward Sciore
 * @author Eric Greer
 * @since	CS4432-Project3
 */
public class ConcurrencyMgr {
   
   /**
    * The global lock table.  This variable is static because all transactions
    * share the same table.
    */
   private static LockTable locktbl = new LockTable();
   private LinkedList<Block> blocks  = new LinkedList<Block>();
   
   /**
    * Obtains an SLock on the block, if necessary.
    * The method will ask the lock table for an SLock
    * if the transaction currently has no locks on that block.
    * @param blk a reference to the disk block
    * @since	CS4432-Project3
    */
   public void sLock(Block blk, int txnum) {
      if (!locktbl.hasSLock(blk, txnum)){
    	  locktbl.sLock(blk, txnum);
    	  blocks.add(blk);
      }
   }
   
   /**
    * Obtains an XLock on the block, if necessary.
    * If the transaction does not have an XLock on that block,
    * then the method first gets an SLock on that block
    * (if necessary), and then upgrades it to an XLock.
    * @param blk a refrence to the disk block
    * @since	CS4432-Project3
    */
   public void xLock(Block blk, int txnum) {
      if (!locktbl.hasXLock(blk, txnum)) {
        // sLock(blk, txnum);
         locktbl.xLock(blk, txnum);
         blocks.add(blk);
      }
   }
   
   /**
    * Releases all locks by asking the lock table to
    * unlock each one.
    * @since	CS4432-Project3
    */
   public void release(int txnum) {
      for (Block blk : blocks)
         locktbl.unlock(blk, txnum);
      blocks.clear();
   }
}
