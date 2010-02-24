package dec08;

import java.util.ArrayList;
import java.util.Iterator;

public class DailyQuestion {


	/**
	 * Show ability to user Iterator to access elements from a collection.
	 */
	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		al.add ("William");
		al.add ("Christopher");
		al.add ("Zachary");
		al.add ("James");
		al.add ("Luke");
		al.add ("Jack");
		al.add ("Joe");
		al.add ("Timothy");
		al.add ("Nicholas");
		
		Iterator it2 = al.iterator();
		// note how the boolean guard actually ADVANCES by mistake. use hasNext()
		while (it2.next() != null) {
		    String s = (String) it2.next();
		    System.out.println (s);
		}
	}

}
