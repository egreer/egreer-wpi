package simpledb.tx.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/** A lock class for the Lock Table
 * 
 * @author Eric Greer
 * @since	CS4432-Project3
 */
public class Lock {

	private int type; //-1 xLock, 0 , 1 sLock
	private ArrayList<Integer> txs = new ArrayList<Integer>();
	
	private LinkedList<Integer> swaiting = new LinkedList<Integer>();
	private LinkedList<Integer> xwaiting = new LinkedList<Integer>();
	
	private static int nextLockNum = 0;
	private int lockNum;
	
	Lock(){
		this.type = 0;
		this.lockNum = nextLockNumber();
	}
	
	/**
	 * @param type The type to set for the lock (-1 xLock, 0 no lock, 1 sLock)
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the type	of the lock (-1 xLock, 0 no lock, 1 sLock)
	 */
	public int getType() {
		return type;
	}
	
	/** 
	 * @return	The number of txs holding locks 
	 */
	public int numberTxs(){
		return txs.size();
	}
	
	/** Unlocks the particular tx
	 * 
	 * @param tx	The number to unlock
	 * @return		True if it was removed
	 */
	public boolean unlock(int tx){
		Iterator<Integer> iterator = txs.iterator();
		while (iterator.hasNext()){
			Integer i = iterator.next();
			if (i.equals(tx)){
				txs.remove(i);
				if(this.numberTxs() == 0){ //If there are no more txs  with locks go to pending
					giveLock();
				}
				System.out.println(this.toString() + " | Unlocked " + tx + " Lock is now: " + this.type + " with " + this.numberTxs() + " locks and " + (this.swaiting.size() + this.xwaiting.size()) + " waiting.");
				return true;
			}
		}
		System.out.println(this.toString() +" | Could not unlock tx " + tx);
		return false;
	}
	
	/** Establishes an xLock for the txnum
	 * 
	 * @param txnum	The number requesting the lock
	 * @return		True if the lock was givens
	 */
	public boolean xlock(int txnum){
		if (this.type == 0 || (this.type == 1 && this.numberTxs() <= 1)){
			txs.add(txnum);
			Collections.sort(txs);
			this.type = -1;
			return true;
		}
		return false;
	}
	
	/** Establishes an sLock for the txnum
	 * 
	 * @param txnum	The number requesting the lock
	 * @return		True if the lock was givens
	 */
	public boolean slock(int txnum){
		if (this.type >= 0){
			this.type = 1;
			txs.add(txnum);
			Collections.sort(txs);
			return true;
		}
		return false;
	}
	
	/** adds the tx to the waiting list 
	 * 
	 * @param txnum	The number to add
	 * @param type	The type of lock it is requesting (-1 = xLock, 1 = sLock)
	 * @return		True if it is added to the list
	 * @since	CS4432-Project3
	 */
	public boolean pendTx(int txnum, int type){
		if (type == -1){ 
			xwaiting.add(txnum);
			Collections.sort(xwaiting);
		}else if (type > 0){
			swaiting.add(txnum);
			Collections.sort(swaiting);
		}
		return true;
	}
	
	/** Removes the tx from the list of waiting txs
	 * 
	 * @param txnum	the tx to remmove
	 * @return		True if it was removed
	 */
	public boolean removePendTx(int txnum){
		Iterator<Integer> iterator = swaiting.iterator();
		while (iterator.hasNext()){
			Integer i = iterator.next();
			if (i.equals(txnum)){
				swaiting.remove(i);
				return true;
			}
		}
		
		iterator = xwaiting.iterator();
		while (iterator.hasNext()){
			Integer i = iterator.next();
			if (i.equals(txnum)){
				xwaiting.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**checks to see if the txnum is has a lock
	 * 
	 * @param txnum The number to check
	 * @return		True if it does false otherwise
	 * @since	CS4432-Project3
	 */
	public boolean hasLock(int txnum){
		Iterator<Integer> iterator = txs.iterator();
		while (iterator.hasNext()){
			Integer i = iterator.next();
			if (i.equals(txnum)) return true;
		}
		return false;
	}
	
	/** checks to see if the txnum is waiting for this lock
	 * 
	 * @param txnum	The number to check
	 * @return		True if the number is waiting
	 * @since	CS4432-Project3
	 */
	public boolean isWaiting(int txnum){
		//Check to see if it is waiting for a shared lock
		Iterator<Integer> iterator = swaiting.iterator();
		while (iterator.hasNext()){
			Integer i = iterator.next();
			if (i.equals(txnum)) return true;
		}
		
		//Check to see if it is waiting for an exclusive lock
		iterator = xwaiting.iterator();
		while (iterator.hasNext()){
			Integer i = iterator.next();
			if (i.equals(txnum)) return true;
		}
		
		return false;
	}
	
	/** Checks to see if the given TX number is older then all txs with locks, and all waiting
	 * 
	 * @param txnum		The txnumber
	 * @return			True if the number is older  
	 * @since	CS4432-Project3
	 */
	public boolean isOlder(int txnum){
		LinkedList<Integer> temp =  new LinkedList<Integer>();
		Integer one = txs.get(0);
		Integer two = swaiting.peekFirst();
		Integer three = xwaiting.peekFirst();
		if(one != null)temp.add(one);
		if(two != null)temp.add(two);
		if(three != null)temp.add(three);
		if (!temp.isEmpty()) Collections.sort(temp);
		else return true;
		
		return txnum < temp.peekFirst();
	}
	
	/**
	 * Gives the lock to the next waiting on an unlock
	 * @since	CS4432-Project3
	 */
	private void giveLock() {
		//No one waiting good
		if (swaiting.isEmpty() && xwaiting.isEmpty()){
			this.type = 0;
			return;
		}
		
		//Else grab the first waiting one
		Integer s = swaiting.peek();
		Integer x = xwaiting.peek();
		
		//Unlocks the block completely
		if (s == null && x == null){
			type = 0;
			return;
		}
		
		//Grabs the first waiting tx
		if (s == null || x == null){
			if (x == null){
				//Grants all txs requesting shared lock the lock
				this.type = 1;
				txs.addAll(swaiting);
				Collections.sort(txs);
				swaiting.clear();	
			}else{
				this.type = -1;
				txs.add(xwaiting.pollFirst());
				Collections.sort(txs);
			}
		}
		
		//Grabs the first waiting tx
		else{
			if (s < x){
				//Grants all txs requesting shared lock the lock
				this.type = 1;
				txs.addAll(swaiting);
				Collections.sort(txs);
				swaiting.clear();
			}else{
				this.type = -1;
				txs.add(xwaiting.pollFirst());
				Collections.sort(txs);
			}
		}
	}
	
	/**
	 * @return returns the next lock number in the sequence
	 * @since	CS4432-Project3
	 */
	private static synchronized int nextLockNumber() {
		nextLockNum++;
		return nextLockNum;
	}
	
	/**
	 * @return The lock number	
	 * @since	CS4432-Project3
	 */
	public int getLockNum() {
		return lockNum;
	}

	/**
	 * @return the transactions currently on the lock
	 * @since	CS4432-Project3
	 */
	public ArrayList<Integer> getTxs() {
		return txs;
	}
	
	/**
	 * @return the transactions currently waiting for slock
	 * @since	CS4432-Project3
	 */
	public LinkedList<Integer> getSwaiting() {
		return swaiting;
	}
	
	/**
	 * @return the transactions currently waiting for xlock
	 * @since	CS4432-Project3
	 */
	public LinkedList<Integer> getXwaiting() {
		return xwaiting;
	}

	
	/** Returns the Lock Number which uniquely represents the lock  
	 * @since	CS4432-Project3
	 */
	@Override
	public String toString(){
		return ""+ lockNum;
	}

}
