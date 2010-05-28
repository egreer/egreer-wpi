package studentTest;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;

import simpledb.remote.SimpleDriver;

/** Test the program as a black box.
 * 
 * @author Eric Greer
 * @since	CS4432-Project3
 */
public class BlackboxTest {
	private static String result = ""; 
	public static void test(){

		Connection conn = null;
		Statement st = null;
		Driver d = new SimpleDriver();
		String host = "localhost"; 
		String url = "jdbc:simpledb://" + host;

		/* Establish a connection to the database */
		/* Run the SQL queries */
		try {
			/* Open connection 1 and prepare a statement for execution */
			conn = d.connect(url, null);
			st = conn.createStatement();		
			/* Create Table */
			st.executeUpdate("CREATE TABLE Student " +
					"( id int," +
					"lastname varchar(50)," +
					"firstname varchar(32)," +
					"email varchar(50)" +
			")");
			
			/* Insert Values */

			String s = "Insert INTO Student(id,lastname,firstname,email) VALUES (1, 'Greer', 'Eric', 'egreer@wpi.edu')";
			st.executeUpdate(s);
			
			s = "INSERT INTO Student(id,lastname,firstname,email) VALUES (2, 'Test2', 'Test2', 'test2@wpi.edu')";
			st.executeUpdate(s);
			
			s = "INSERT INTO Student(id,lastname,firstname,email) VALUES (3, 'TestLast', 'TestFirst', 'test3@example.com')";
			st.executeUpdate(s);
			
			/* Start Running Test */
			Test1 t1 = new Test1();
			Thread th1 = new Thread(t1);
			th1.start();
			Test2 t2 = new Test2();
			Thread th2 = new Thread(t2);
			th2.start();
			Test3 t3 = new Test3();
			Thread th3 = new Thread(t3);
			th3.start();
			
			try {
				th1.join();//main thread wait for transaction A,B,C to finish before taking next steps.
				th2.join();
				th3.join();
			}
			catch (InterruptedException e) {}

		}catch (SQLException e){
			/* Error occurs */
			System.err.println("SQLException: " + e.getMessage());

			e.printStackTrace();
		}
	}

	//class method to write to result, all transaction write to the same result string.
	public synchronized static void appendToResult(String s) {
		result += s + "\n";
		System.out.println(s);
	}
}


class Test1 implements Runnable {
	public void run() {
		Connection conn1 = null;
		Statement st1 = null;
		Driver d = new SimpleDriver();
		String host = "localhost"; 
		String url = "jdbc:simpledb://" + host;

		/* Open connection 1 and prepare a statement for execution */
		try {
			conn1 = d.connect(url, null);
			st1 = conn1.createStatement();
		
			/* Select Values All three get slocks*/
			BlackboxTest.appendToResult("TX 1 Start Read 1");
			String s = "Select id, firstname, lastname, email From Student Where id = '1'";
			st1.executeQuery(s);
			BlackboxTest.appendToResult("TX 1 End Read 1");
			
			/* get an slock while another tries to get an xlock*/
			BlackboxTest.appendToResult("TX 1 Start Read 2");
			s = "Select id, firstname, lastname, email From Student Where id = 2";
			st1.executeQuery(s);
			BlackboxTest.appendToResult("TX 1 End Read 2");
			
			/* obtain a xlock on the data and have others get an slock*/
			BlackboxTest.appendToResult("TX 1 Start Update 1");
			s = "UPDATE Student SET email = 'update@wpi.edu' WHERE id = 1";
			st1.executeUpdate(s);
			BlackboxTest.appendToResult("TX 1 End Update 1");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

class Test2 implements Runnable {
	public void run() {
		Connection conn2 = null;
		Statement st2 = null;
		Driver d = new SimpleDriver();
		String host = "localhost"; 
		String url = "jdbc:simpledb://" + host;
		
		/* Open connection 2 and prepare a statement for execution */
		try {
			conn2 = d.connect(url, null);
			st2 = conn2.createStatement();
			
			/* All three get s locks */
			BlackboxTest.appendToResult("TX 2 Start Read 1");
			String s = "Select id, firstname, lastname, email From Student Where id = 1";
			st2.executeQuery(s);
			BlackboxTest.appendToResult("TX 2 End Read 1");
			
			/* Tries to get xlock but others have slocks*/
			BlackboxTest.appendToResult("TX 2 Start Update 1");
			s = "UPDATE Student SET email = 'update@wpi.edu' WHERE id = 2";
			st2.executeUpdate(s);
			BlackboxTest.appendToResult("TX 2 End Update 1");
			
			
			/* Fail to get slock since Tx1 has xlock*/
			BlackboxTest.appendToResult("TX 2 Start Read 2");
			s = "Select id, firstname, lastname, email from  Student WHERE id = 1";
			st2.executeQuery(s);
			BlackboxTest.appendToResult("TX 2 End Read 2");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

class Test3 implements Runnable {
	public void run() {
		Connection conn3 = null;
		Statement st3 = null;
		Driver d = new SimpleDriver();
		String host = "localhost"; 
		String url = "jdbc:simpledb://" + host;
		
		/* Open connection 3 and prepare a statement for execution */
		try {
			conn3 = d.connect(url, null);
			st3 = conn3.createStatement();
			
			/* All three get slocks on the values */
			BlackboxTest.appendToResult("TX 3 Start Read 1");
			String s = "Select id, firstname, lastname, email From Student Where id = 1";
			st3.executeQuery(s);
			BlackboxTest.appendToResult("TX 3 End Read 1");
			
			
			/* Gets slock while other tries to get xlock*/
			BlackboxTest.appendToResult("TX 3 Start Read 2");
			s = "Select id, firstname, lastname, email From Student Where id = 2";
			st3.executeQuery(s);
			BlackboxTest.appendToResult("TX 3 End Read 2");
			
			/* Fail to get slock since TX1 has xlock*/ 
			BlackboxTest.appendToResult("TX 3 Start Read 3");
			s = "Select id, firstname, lastname, email from  Student WHERE id = 1";
			st3.executeQuery(s);
			BlackboxTest.appendToResult("TX 3 End Read 3");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
