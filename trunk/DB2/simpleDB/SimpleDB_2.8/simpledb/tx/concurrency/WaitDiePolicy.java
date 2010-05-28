package simpledb.tx.concurrency;

/** This class handles the Wait-Die policy for determining locks
 * 
 * @author Eric Greer
 * @since	CS4432-Project3
 */
public class WaitDiePolicy implements LockPolicy {

	/*
	 * (non-Javadoc)
	 * @see simpledb.tx.concurrency.LockPolicy#slock(simpledb.tx.concurrency.Lock, int)
	 */
	@Override
	public boolean slock(Lock l, int txnum) throws LockAbortException {
		if (l.getType() >= 0){
			boolean returner = l.slock(txnum);
			System.out.println((returner) ? l.toString() + " | Tx: " + txnum + " has " +l.getType() + " lock" : l.toString() +"Tx: " + txnum + " doesn't have " +l.getType() + " lock"); 
			return returner;
		}else{
			if (l.hasLock(txnum)){
				l.setType(1);
				System.out.println(l.toString() + " | Tx: " + txnum + " changed to " +l.getType() + " lock");
				return true;
			}else if (l.isOlder(txnum)){
				l.pendTx(txnum, 1);
				return false;
			}else{
				System.out.println(l.toString() + " | Tx: " + txnum + " could not obtain slock.");
				throw new LockAbortException();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see simpledb.tx.concurrency.LockPolicy#xlock(simpledb.tx.concurrency.Lock, int)
	 */
	@Override
	public boolean xlock(Lock l, int txnum) throws LockAbortException{
		if (l.getType() == 0){
			boolean returner = l.xlock(txnum);
			System.out.println((returner) ? l.toString() +" | Tx: " + txnum + " has " +l.getType() + " lock" : l.toString() +"Tx: " + txnum + " doesn't have " +l.getType() + " lock");
			return returner;
		}else if(l.getType() == 1 && l.hasLock(txnum)){
			if (l.numberTxs() == 1) {
				l.setType(-1);
				System.out.println(l.toString() + " | Tx: " + txnum + " has " +l.getType() + " lock from share.");
				return true;
			} else if (l.isOlder(txnum)){
				l.pendTx(txnum, -1);
				return false;// Wait until this has a locks
				

			}else{
				System.out.println(l.toString() + " | Tx: " + txnum + " could not obtain " +l.getType() + " lock.");
				throw new LockAbortException();
			}
		}else if (l.getType() != 0 && l.isOlder(txnum)){
			l.pendTx(txnum, -1);
			return false;
		}else{
			System.out.println(l.toString() + " | Tx: " + txnum + " could not obtain xlock.");
			
			throw new LockAbortException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see simpledb.tx.concurrency.LockPolicy#unlock(simpledb.tx.concurrency.Lock, int)
	 */
	@Override
	public void unlock(Lock l, int txnum) {
		//Unlock doesn't mean anything for the wait die policy 
		
	}
}
