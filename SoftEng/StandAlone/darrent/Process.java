package darrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;

/** Pack the 32000 shufflings into compressed form. */
public class Process {

	static Hashtable<Integer,String> comp = new Hashtable<Integer,String>();
	
	public static void main (String []args) throws Exception {
		File f = new File ("darrent\\32000.txt");
		FileOutputStream fos = new FileOutputStream (new File ("shuffles.hash"));
		ObjectOutputStream oos = new ObjectOutputStream (fos);
		if (f.exists()) {
			Scanner sc = new Scanner (f);
			while (sc.hasNext()) {
				String s = sc.nextLine();
				
				
				StringTokenizer st = new StringTokenizer(s);
				String key = st.nextToken();
				key = key.substring(0,key.length()-1);
				String rest = st.nextToken();
				
				int g = Integer.valueOf(key).intValue();
				st = new StringTokenizer (rest, ",");
				StringBuilder sb = new StringBuilder();
				while (st.hasMoreTokens()) {
					String pos = st.nextToken();
					int p = Integer.valueOf(pos).intValue();
					sb.append ((char)('!' + p));
				}
				
				comp.put(g, sb.toString());
			}
			
			oos.writeObject(comp);
		}
	}
}
