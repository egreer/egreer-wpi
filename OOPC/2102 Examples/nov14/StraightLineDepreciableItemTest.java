package nov14;

import java.text.NumberFormat;
import java.util.Calendar;

import junit.framework.TestCase;

public class StraightLineDepreciableItemTest extends TestCase {
	public void testValues() {
		Calendar c = Calendar.getInstance();
		int baseYear = c.get(Calendar.YEAR);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		
		// Honestly haven't confirmed this properly. Something still looks a bit wrong
		// must look into the accounting...
		
		StraightLineDepreciableItem sldi = new StraightLineDepreciableItem ("some", 4.5, 100.0, 5, baseYear);
		assertEquals ("$100.00", format.format(sldi.getValue()));
		
		// 20.00   20.00   20.00   20.00   20.00
		sldi = new StraightLineDepreciableItem ("some", 4.5, 100.0, 5, baseYear-1);
		assertEquals ("$20.00", format.format(sldi.getValue()));
		
		sldi = new StraightLineDepreciableItem ("some", 4.5, 100.0, 5, baseYear-2);
		assertEquals ("$20.00", format.format(sldi.getValue()));
		
		sldi = new StraightLineDepreciableItem ("some", 4.5, 100.0, 5, baseYear-3);
		assertEquals ("$20.00", format.format(sldi.getValue()));
		
		sldi = new StraightLineDepreciableItem ("some", 4.5, 100.0, 5, baseYear-4);
		assertEquals ("$20.00", format.format(sldi.getValue()));
		
		sldi = new StraightLineDepreciableItem ("some", 4.5, 100.0, 5, baseYear-5);
		assertEquals ("$0.00", format.format(sldi.getValue()));
		
		sldi = new StraightLineDepreciableItem ("some", 4.5, 100.0, 5, baseYear-6);
		assertEquals ("$0.00", format.format(sldi.getValue()));
	}
}
