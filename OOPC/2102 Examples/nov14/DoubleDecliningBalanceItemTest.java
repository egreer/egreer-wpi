package nov14;

import junit.framework.TestCase;

import java.text.NumberFormat;
import java.util.*;

public class DoubleDecliningBalanceItemTest extends TestCase {
	
	
	public void testValues() {
		Calendar c = Calendar.getInstance();
		int baseYear = c.get(Calendar.YEAR);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		
		DoubleDecliningBalanceItem dbi = new DoubleDecliningBalanceItem ("some", 4.5, 100.0, 5, baseYear);
		assertEquals ("$100.00", format.format(dbi.getValue()));
		
		// 40.00  	24.00  	14.40  	8.64  	5.18  	0.00
		dbi = new DoubleDecliningBalanceItem ("some", 4.5, 100.0, 5, baseYear-1);
		assertEquals ("$40.00", format.format(dbi.getValue()));
		
		dbi = new DoubleDecliningBalanceItem ("some", 4.5, 100.0, 5, baseYear-2);
		assertEquals ("$24.00", format.format(dbi.getValue()));
		
		dbi = new DoubleDecliningBalanceItem ("some", 4.5, 100.0, 5, baseYear-3);
		assertEquals ("$14.40", format.format(dbi.getValue()));
		
		dbi = new DoubleDecliningBalanceItem ("some", 4.5, 100.0, 5, baseYear-4);
		assertEquals ("$8.64", format.format(dbi.getValue()));
		
		dbi = new DoubleDecliningBalanceItem ("some", 4.5, 100.0, 5, baseYear-5);
		assertEquals ("$5.18", format.format(dbi.getValue()));
		
		dbi = new DoubleDecliningBalanceItem ("some", 4.5, 100.0, 5, baseYear-6);
		assertEquals ("$0.00", format.format(dbi.getValue()));
	}
}
