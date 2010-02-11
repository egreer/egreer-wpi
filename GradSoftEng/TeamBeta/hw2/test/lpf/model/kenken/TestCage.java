package lpf.model.kenken;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;
import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Value;

public class TestCage extends TestCase {

	public Grid solution = new Grid(3, 3);

	public void testCage() {

		ArrayList<Location> collection = new ArrayList<Location>();

		collection.add(new Location(1, 'A'));
		collection.add(new Location(1, 'B'));
		Cage c1 = new Cage('+', 5, collection);
		assertEquals(c1.operation, '+');
		assertEquals(c1.finalValue, 5);

		ArrayList<Location> arr = (ArrayList<Location>) c1.locations;

		for (int i = 0; i < arr.size(); i++) {
			assertEquals(arr.get(i).row, collection.get(i).row);
			assertEquals(arr.get(i).column, collection.get(i).column);
		}

	}

	public void testContains() {
		ArrayList<Location> loc = new ArrayList<Location>();

		loc.add(new Location(2, 'A'));
		loc.add(new Location(3, 'A'));

		Cage c1 = new Cage('+', 5, loc);

		for (Iterator<Location> it = loc.iterator(); it.hasNext();) {

			assertTrue(c1.contains(it.next()));
		}

	}

	public void testIsValid() {

		solution.cells[0][0] = new Cell(new Location(1, 'A'));
		solution.cells[0][1] = new Cell(new Location(1, 'B'));
		solution.cells[0][2] = new Cell(new Location(1, 'C'));
		solution.cells[1][0] = new Cell(new Location(2, 'A'));
		solution.cells[1][1] = new Cell(new Location(2, 'B'));
		solution.cells[1][2] = new Cell(new Location(2, 'C'));
		solution.cells[2][0] = new Cell(new Location(3, 'A'));
		solution.cells[2][1] = new Cell(new Location(3, 'B'));
		solution.cells[2][2] = new Cell(new Location(3, 'C'));

		solution.cells[0][0].setDigit(new Value('1'));
		solution.cells[0][1].setDigit(new Value('2'));
		solution.cells[0][2].setDigit(new Value('3'));
		solution.cells[1][0].setDigit(new Value('2'));
		solution.cells[1][1].setDigit(new Value('3'));
		solution.cells[1][2].setDigit(new Value('1'));
		solution.cells[2][0].setDigit(new Value('3'));
		solution.cells[2][1].setDigit(new Value('1'));
		solution.cells[2][2].setDigit(new Value('2'));

		ArrayList<Location> loc1 = new ArrayList<Location>();
		loc1.add(new Location(1, 'A'));
		loc1.add(new Location(1, 'B'));

		ArrayList<Location> loc2 = new ArrayList<Location>();
		loc2.add(new Location(2, 'A'));
		loc2.add(new Location(2, 'B'));

		ArrayList<Location> loc3 = new ArrayList<Location>();
		loc3.add(new Location(3, 'A'));
		loc3.add(new Location(3, 'B'));

		ArrayList<Location> loc4 = new ArrayList<Location>();
		loc4.add(new Location(1, 'C'));
		loc4.add(new Location(2, 'C'));

		ArrayList<Location> loc5 = new ArrayList<Location>();
		loc5.add(new Location(3, 'C'));
		
		ArrayList<Location> loc6 = new ArrayList<Location>();
		loc6.add(new Location(1, 'A'));
		loc6.add(new Location(2, 'C'));

		Cage c1 = new Cage('+', 3, loc1);
		assertTrue(c1.isValid(solution));

		Cage c2 = new Cage('*', 6, loc2);
		assertTrue(c2.isValid(solution));

		Cage c3 = new Cage('-', 2, loc3);
		assertTrue(c3.isValid(solution));

		Cage c4 = new Cage('/', 3, loc4);
		assertTrue(c4.isValid(solution));

		Cage c5 = new Cage('$', 2, loc5);
		assertFalse(c5.isValid(solution));
		
		assertTrue(c1.isAdjacent());

		Cage c6 = new Cage('+', 2, loc6);
		assertFalse(c6.isAdjacent());
	}

}
