package lpf.model.core.valueSets;

import junit.framework.TestCase;
import lpf.model.core.Value;

public class CharacterSetTest extends TestCase {
	
	public void testCharacterSet(){
		CharacterSet cs = new OneToNineCharacterSet();
		assertEquals(cs.getNumValues(), 9);
		
		
		Value[] v = cs.getValues();
		
		for (int i=0; i < 9; i++){
			assertEquals((char)(i+'1'),v[i].value);
		}
		
	}

}
