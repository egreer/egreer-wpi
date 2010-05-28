package simpledb.tx.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/** The Wait Graph Policy
 * 
 * @author Eric Greer
 * @since	CS4432-Project3
 */
public class WaitGraph implements LockPolicy {

	private static ArrayList<ArrayList<Integer>> lockGraph = new ArrayList<ArrayList<Integer>>(); 

	/*
	 * (non-Javadoc)
	 * @see simpledb.tx.concurrency.LockPolicy#slock(simpledb.tx.concurrency.Lock, int)
	 */
	@Override
	public boolean slock(Lock l, int txnum) throws LockAbortException {
		ArrayList<Integer> txs = l.getTxs();
		
		//If there is no one using the lock then sure
		if (l.getType() == 0) return true; 
		
		Iterator<Integer> iterator = txs.iterator();
		while (iterator.hasNext()){
			Integer row = iterator.next();
			this.set(row, txnum, this.get(row, txnum) + 1);
		}
		
		if (hasLoop()){
			//Rollback changes and throw exception
			Iterator<Integer> undoIterator = txs.iterator();
			while (undoIterator.hasNext()){
				Integer row = undoIterator.next();
				this.set(row, txnum, this.get(row, txnum) + 1);
			}
			throw new LockAbortException();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see simpledb.tx.concurrency.LockPolicy#xlock(simpledb.tx.concurrency.Lock, int)
	 */
	@Override
	public boolean xlock(Lock l, int txnum) throws LockAbortException {
		ArrayList<Integer> txs = l.getTxs();

		//If there is no one using the lock then sure
		if (l.getType() == 0) return true; 
		
		Iterator<Integer> iterator = txs.iterator();
		while (iterator.hasNext()){
			Integer row = iterator.next();
			this.set(row, txnum, this.get(row, txnum) + 1);
		}
		
		if (hasLoop()){
			//Rollback changes and throw exception
			Iterator<Integer> undoIterator = txs.iterator();
			while (undoIterator.hasNext()){
				Integer row = undoIterator.next();
				this.set(row, txnum, this.get(row, txnum) + 1);
			}
			throw new LockAbortException();
		} else return false;
	}

	/*
	 * (non-Javadoc)
	 * @see simpledb.tx.concurrency.LockPolicy#unlock(simpledb.tx.concurrency.Lock, int)
	 */
	@Override
	public  void  unlock(Lock l, int txnum){
		LinkedList<Integer> swaiting = l.getSwaiting();
		LinkedList<Integer> xwaiting = l.getXwaiting();
			
		//TODO remove the the relationship on the graph
		Iterator<Integer> sIterator = swaiting.iterator();
		Iterator<Integer> xIterator = xwaiting.iterator();
		
		//Remove any on slock
		while(sIterator.hasNext()){
			Integer col = sIterator.next();
			this.set(txnum, col, this.get(txnum, col) - 1);
		}
		
		//remove any  on xlock
		while(xIterator.hasNext()){
			Integer col = xIterator.next();
			this.set(txnum, col, this.get(txnum, col) - 1);
		}
	}

	private int get(int row, int col)
	{
		return lockGraph.get(row).get(col);
	}
	
	private void set(int row, int col, Integer val)
	{
		lockGraph.get(row).set(col, val);
	}

	private int getNumRows()
	{
		return lockGraph.size();
	}

	private int getNumCols(int row)
	{
		return lockGraph.get(row).size();
	}

	/** Looks at each row and look for conflicts  
	 * 
	 * @return		True then there is a loop detected 
	 */
	 private synchronized boolean hasLoop(){
		 
		  
		 for (int i = 0 ; i < this.getNumRows() ; i++){
			 for (int j = 0 ; i < this.getNumCols(i) ; j++){
				 if (this.get(i, j) > 0 && this.get(j, i) > 0) return true;
			 }
		 }
		//TODO Detects simple loops, need to implement full DFS to get the complex loops
		 
		return false;
	}
}
