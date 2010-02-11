package lpf.model.core;

import java.util.Iterator;

import junit.framework.TestCase;
import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.Location;

/**
 * 
 * @author Nik Deapen
 */
public class GridTest extends TestCase {

	public void testGrid_Int_Int(){
		int height = 5, width =5;
		Grid g = new Grid(height,width);
		assertEquals(g.height,height);
		assertEquals(g.width,width);
		
		height = -1;
		width = 1;
		try {
			g = new Grid(height,width);
		}
		catch(Exception e){
			g = null;
		}
		assertEquals(g,null);
		
		
		height = 5;
		width = 9;
		g = new Grid(height,width);
		assertEquals(g.height,height);
		assertEquals(g.width,width);
		
		height = 15;
		width = 12;
		g = new Grid(height,width);
		assertEquals(g.height,height);
		assertEquals(g.width,width);
		
		height = 9;
		width = 9;
		g = new Grid(height,width);
		assertEquals(g.height,height);
		assertEquals(g.width,width);
		
	}
	
	
	public void testGetCellAtLocation(){
		Grid g = new Grid(9,9);
		
		Location loc1 = g.getCellAtLocation(new Location(2,'b')).loc;
		Location loc2 = new Location(2,'b');
		
		assertEquals(loc1, loc2);
		
		loc1 = g.getCellAtLocation(new Location(9,'i')).loc;
		loc2 = new Location(9,'i');
		
		assertEquals(loc1,loc2);
		
		int i=0;
		try {
			g.getCellAtLocation(new Location(10,'i'));
			fail();
		}
		catch (IllegalArgumentException e){
			i++;
		}
		
		assert (i == 1);
		
		loc1 = g.getCellAtLocation(new Location(9,'A')).loc;
		loc2 = new Location(9,'A');
		
		assertEquals(loc1,loc2);
	}
	
	
	public void testIterator(){
		Grid g = new Grid(9,9);
		Iterator<Cell> i = g.iterator();
		
		int count = 0;
		while (i.hasNext()){
			Cell c = i.next();
			count++;
			assertTrue(c != null);
		}
		
		assertEquals(count, 81);
	}
}
