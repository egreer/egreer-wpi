package studentTest;

import simpledb.file.Block;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;
import simpledb.tx.concurrency.LockAbortException;

public class TxTest {
	private static String result = ""; 
	public static void test() {
		SimpleDB.initFileLogAndBufferMgr("testdb");
		System.out.println("BEGIN TX PACKAGE TEST");
		Transaction tx = new Transaction(); /*The main transaction, if bad things happen, roll back*/
		TestA t1 = new TestA();
		Thread th1 = new Thread(t1);
		th1.start();
		TestB t2 = new TestB();
		Thread th2 = new Thread(t2);
		th2.start();
		TestC t3 = new TestC();
		Thread th3 = new Thread(t3);
		th3.start();
		TestD t4 = new TestD();
		Thread th4 = new Thread(t4);
		th4.start();

		TestE t5 = new TestE();
		Thread th5 = new Thread(t5);
		th5.start();

		TestF t6 = new TestF();
		Thread th6 = new Thread(t6);
		th6.start();

		try {
			th1.join();//main thread wait for transaction A,B,C to finish before taking next steps.
			th2.join();
			th3.join();
			th4.join();
			th5.join();
			th6.join();
		}
		catch (InterruptedException e) {}
		tx.rollback();
		String correctResult =
			"Tx 5: write 4 start\n" + //Tx5 starts , acquires xLock 
			"Tx 5: write 4 end\n" +   //Tx5 still holds xLock
			"Tx 5: read 4 start\n"	+  //Tx5 downgrades to sLock
			"Tx 5: read 4 end\n" + //Tx5 still has to sLock
			"Tx 5: read 5 start\n" + //Tx5 gets sLock	
			"Tx 5: read 5 end\n" + //Tx5 still has sLock
			"Tx 5: write 5 start\n" + //Tx5 upgrades sLock to xLock 
			"Tx 5: write 5 end\n" +  //Tx5 holds xLock	
			"Tx 2: read 1 start\n" +//Tx2 starts, acquire sLock
			"Tx 2: read 1 end\n" +//Tx2 still holding sLock
			"Tx 3: write 2 start\n" +//Tx3 starts, acquire xLock
			"Tx 3: write 2 end\n" + //Tx3 still holding xLock
			"Tx 4: write 1 start\n" +//Tx4 starts, needing xLock, but Tx2 has sLock, wait
			"Tx 4: could not obtain lock\n"+ //Tx4 is too young and dies
			"Tx 2: read 2 start\n" +//Tx2 needs sLock, but Tx3 has xLock, wait 
			"Tx 3: read 1 start\n" +//Tx3 acquire sLock, sharing with Tx2,
			"Tx 3: read 1 end\n"+ 
			"Tx 3: write 3 start\n" + //Tx3 gets xLock
			"Tx 3: write 3 end\n" +	//Tx3 finished, release all locks
			"Tx 2: read 2 end\n" +
			"Tx 2: write 3 start\n" + //Tx2 needs x lock but has to wait
			"Tx 2: write 3 end\n" +	//Tx2 finished, release all locks
		"Tx 6: read 6 start\n" +	//Tx6 gets slock
		"Tx 6: read 6 end\n" +
		"Tx 7: read 6 start\n" +	//Tx7 gets slock
		"Tx 7: read 6 end\n" +
		"Tx 7: write 6 start\n" +	//tx7 tries to upgrade slock to xlock
		"Tx 7: could not obtain lock\n" + //tx7 Fails to upgrade 
		"Tx 6: write 7 start\n" +	//tx6 writes on 7
		"Tx 6: write 7 end\n" +
		"Tx 7: read 7 start\n" +	//tx7 tries to get read lock on 7
		"Tx 7: could not obtain lock\n";	//Fails to obtain locks

		if (!result.equals(correctResult)){
			System.out.println("*****TxTest: bad tx history");
			System.out.println(result);
		}else{
			System.out.println("*****TxTest: Good tx history");
		}
	}

	//class method to write to result, all transaction write to the same result string.
	public synchronized static void appendToResult(String s) {
		result += s + "\n";
		System.out.println(s);
	}
}

class TestA implements Runnable {
	public void run() {
		try {
			Transaction tx = new Transaction();
			Block blk1 = new Block("temptransaction", 0);
			Block blk2 = new Block("temptransaction", 1);
			Block blk3 = new Block("temptransaction", 2);
			tx.pin(blk1);
			tx.pin(blk2);
			tx.pin(blk3);
			try{
				Thread.sleep(500);
				TxTest.appendToResult("Tx 2: read 1 start");
				tx.getInt(blk1, 0);//Reading need sLock
				TxTest.appendToResult("Tx 2: read 1 end");
				Thread.sleep(2000);
				TxTest.appendToResult("Tx 2: read 2 start");
				tx.getInt(blk2, 0); //Reading need sLock
				TxTest.appendToResult("Tx 2: read 2 end");

				Thread.sleep(250);
				TxTest.appendToResult("Tx 2: write 3 start");
				tx.setInt(blk3, 0, 0); //Writing need xLock
				TxTest.appendToResult("Tx 2: write 3 end");

				tx.rollback();
			}catch (LockAbortException e){
				TxTest.appendToResult("Tx 3: could not obtain lock");
				tx.rollback();
			}

		}
		catch(InterruptedException e) {};
	}
}

class TestB implements Runnable {
	public void run() {
		try {
			Thread.sleep(1500);
			Transaction tx = new Transaction();
			Block blk1 = new Block("temptransaction", 0);
			Block blk2 = new Block("temptransaction", 1);
			Block blk3 = new Block("temptransaction", 2);
			tx.pin(blk1);
			tx.pin(blk2);
			tx.pin(blk3);
			try{
				TxTest.appendToResult("Tx 3: write 2 start");
				tx.setInt(blk2, 0, 0); //Writing need xLock
				TxTest.appendToResult("Tx 3: write 2 end");
				Thread.sleep(1500);
				TxTest.appendToResult("Tx 3: read 1 start");
				tx.getInt(blk1, 0); //Reading need sLock
				TxTest.appendToResult("Tx 3: read 1 end");

				TxTest.appendToResult("Tx 3: write 3 start");
				tx.setInt(blk3, 0, 0); //Writing need xLock
				TxTest.appendToResult("Tx 3: write 3 end");
				Thread.sleep(250);

				tx.rollback();
			}catch (LockAbortException e){
				TxTest.appendToResult("Tx 3: could not obtain lock");
				tx.rollback();
			}

		}
		catch(InterruptedException e) {};
	}
}

class TestC implements Runnable {
	public void run() {
		try {
			Thread.sleep(2000);
			Transaction tx = new Transaction();
			Block blk1 = new Block("temptransaction", 0);
			Block blk2 = new Block("temptransaction", 1);
			tx.pin(blk1);
			tx.pin(blk2);
			try{
				TxTest.appendToResult("Tx 4: write 1 start");
				tx.setInt(blk1, 0, 0); //Writing need xLock
				TxTest.appendToResult("Tx 4: write 1 end");
				TxTest.appendToResult("Tx 4: read 2 start");
				tx.getInt(blk2, 0); //Reading need sLock
				TxTest.appendToResult("Tx 4: read 2 end");
				tx.rollback();
			}catch (LockAbortException e){
				TxTest.appendToResult("Tx 4: could not obtain lock");
				tx.rollback();
			}

		}
		catch(InterruptedException e) {};
	}
}

class TestD implements Runnable {
	public void run() {

		Transaction tx = new Transaction();
		Block blk4 = new Block("temptransaction", 3);
		Block blk5 = new Block("temptransaction", 4);

		tx.pin(blk4);
		tx.pin(blk5);

		try{
			TxTest.appendToResult("Tx 5: write 4 start");
			tx.setInt(blk4, 0, 0); //Writing need xLock
			TxTest.appendToResult("Tx 5: write 4 end");

			TxTest.appendToResult("Tx 5: read 4 start");
			tx.getInt(blk4, 0); //Reading need sLock
			TxTest.appendToResult("Tx 5: read 4 end");

			TxTest.appendToResult("Tx 5: read 5 start");
			tx.getInt(blk5, 0); //Reading need sLock
			TxTest.appendToResult("Tx 5: read 5 end");

			TxTest.appendToResult("Tx 5: write 5 start");
			tx.setInt(blk5, 0, 0); //Writing need xLock
			TxTest.appendToResult("Tx 5: write 5 end");


			tx.rollback();
		}catch (LockAbortException e){
			TxTest.appendToResult("Tx 5: could not obtain lock");
			tx.rollback();
		}

	}
}	
class TestE implements Runnable {
	public void run() {

		Transaction tx = new Transaction();
		Block blk6 = new Block("temptransaction", 5);
		Block blk7 = new Block("temptransaction", 6);

		tx.pin(blk6);
		tx.pin(blk7);
		try {
			Thread.sleep(7000);

			try{
				TxTest.appendToResult("Tx 6: read 6 start");
				tx.getInt(blk6, 0); //Reading need sLock
				TxTest.appendToResult("Tx 6: read 6 end");
				Thread.sleep(100);

				tx.rollback();
			}catch (LockAbortException e){
				TxTest.appendToResult("Tx 6: could not obtain lock");
				tx.rollback();
			}
			tx.pin(blk6);
			tx.pin(blk7);
			Thread.sleep(500);
			try{
				TxTest.appendToResult("Tx 6: write 7 start");
				tx.setInt(blk7, 0, 0); //Writing need xLock
				TxTest.appendToResult("Tx 6: write 7 end");

				Thread.sleep(100);

				tx.rollback();
			}catch (LockAbortException e){
				TxTest.appendToResult("Tx 6: could not obtain lock");
				tx.rollback();
			}
		} catch (InterruptedException e1) {}
	}
}		
class TestF implements Runnable {
	public void run() {

		Transaction tx = new Transaction();
		Block blk6 = new Block("temptransaction", 5);
		Block blk7 = new Block("temptransaction", 6);

		tx.pin(blk6);
		tx.pin(blk7);
		try {
			Thread.sleep(7000);

			try{
				TxTest.appendToResult("Tx 7: read 6 start");
				tx.getInt(blk6, 0); //Reading need sLock
				TxTest.appendToResult("Tx 7: read 6 end");

				TxTest.appendToResult("Tx 7: write 6 start");
				tx.setInt(blk6, 0, 0); //Writing need xLock
				TxTest.appendToResult("Tx 7: write 6 end");

				tx.rollback();
			}catch (LockAbortException e){
				TxTest.appendToResult("Tx 7: could not obtain lock");
				tx.rollback();
			}
			tx.pin(blk6);
			tx.pin(blk7);
			Thread.sleep(600);
			try{
				TxTest.appendToResult("Tx 7: read 7 start");
				tx.getInt(blk7, 0); //Reading need sLock
				TxTest.appendToResult("Tx 7: read 7 end");

				tx.rollback();
			}catch (LockAbortException e){
				TxTest.appendToResult("Tx 7: could not obtain lock");
				tx.rollback();
			}
		} catch (InterruptedException e1) {}
	}
}


