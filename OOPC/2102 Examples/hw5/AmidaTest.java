package hw5;

import amida.*;
import junit.framework.TestCase;

public class AmidaTest extends TestCase {

	
	/**
	 * Validate adding lines.
	 * 
	 * Note: If student's constructor starts with more than zero lines, that is not
	 * a defect. No points should be taken off.
	 */
	public void testAddLine() {
		AmidaInterface board = new Amida();
		assertEquals (0, board.numLines());
		
		// have one more
		board.addLine();
		assertEquals (1, board.numLines());
		
	}

	/**
	 * Validate for small additions/removals that the number of lines in the board
	 * is properly maintained and controlled.
	 */
	public void testNumLines() {
		AmidaInterface board = new Amida();
		assertEquals (0, board.numLines());
		
		// have one more
		board.addLine();
		board.addLine();
		assertEquals (2, board.numLines());
		assertEquals (true, board.removeLine());
		assertEquals (1, board.numLines());
		assertEquals (true, board.removeLine());
		assertEquals (0, board.numLines());
		
		// Stress test.
		for (int i = 0; i < 100; i++) {
			board.addLine();
		}
		assertEquals (100, board.numLines());
		for (int i = 0; i < 100; i++) {
			assertEquals (true, board.removeLine());
		}
		assertEquals (0, board.numLines());
		
	}

	/**
	 * Verify removeLine behavior on empty board.
	 */
	public void testRemoveLine() {
		AmidaInterface board = new Amida();
		assertEquals (0, board.numLines());

		// can't remove from an empty board.
		assertEquals (false, board.removeLine());
	}

	/** 
	 * Verify that we can add an edge to the board.
	 */
	public void testAddEdge() {
		AmidaInterface board = new Amida();
		assertEquals (0, board.numLines());
		
		board.addLine();   // create a line
		
		// try to create edge.
		try {
			board.addEdge(1, 10);
			fail ("Cannot add an edge to a board with only one vertical line.");
		} catch (AmidaException ae) {
			// Note: it isn't critical that you validate the MESSAGE, but I am doing 
			// that for completeness.
			assertEquals ("invalid vertical line", ae.getMessage());
		}
		
		// Make board contain four line so we can create edges at different heights, to 
		// test the tooClose ability
		board.addLine();  // 2
		board.addLine();  // 3
		board.addLine();  // 4

		
		try {
			board.addEdge(2, 10);
			assertEquals (1, board.numEdges(2));
		} catch (AmidaException ae) {
			fail ("Cannot Add an edge or number of edges not properly maintained:" + ae.getMessage());
		}
		
		// too close to edge on existing line
		try {
			board.addEdge(2, 12);
			fail ("shouldn't add since too close");
		} catch (AmidaException ae) {
			assertEquals ("line is too close to existing edge", ae.getMessage());
		}
		
		// too close on previous
		try {
			board.addEdge(1, 12);
			fail ("shouldn't add since too close");
		} catch (AmidaException ae) {
			assertEquals ("line is too close to existing edge", ae.getMessage());
		}
		
		// too close on next
		try {
			board.addEdge(3, 12);
			fail ("shouldn't add since too close");
		} catch (AmidaException ae) {
			assertEquals ("line is too close to existing edge", ae.getMessage());
		}

		// invalid negative position
		try {
			board.addEdge(1, -45);
			fail ("Should catch invalid edge.");
		} catch (AmidaException ae) {
			assertEquals ("invalid vertical position", ae.getMessage());
		}
	}

	/** 
	 * Simple test for whether too close on a specific line.
	 */
	public void testTooClose() {
		AmidaInterface board = new Amida();
		assertEquals (0, board.numLines());
		
		board.addLine();   // create lines for edges
		board.addLine();   // create lines for edges
		
		// try to create edge.
		try {
			board.addEdge(1, 10);
		} catch (AmidaException ae) {
			fail ("Adding edge should be ok.");
		}

		// try to create edge.
		try {
			board.addEdge(1, 5);
			fail ("within five pixels.");
		} catch (AmidaException ae) {
			// Note: it isn't critical that you validate the MESSAGE, but I am doing 
			// that for completeness.
			assertEquals ("line is too close to existing edge", ae.getMessage());
		}

		// try to create edge.
		try {
			board.addEdge(1, 4);
			board.addEdge(1, 16);
		} catch (AmidaException ae) {
			fail ("Should have no exception here.");
		}
	}

	/*
	 * Test method for 'solution.Amida.numEdges(int)'
	 */
	public void testNumEdges() {

	}

	/*
	 * Test method for 'solution.Amida.getEdge(int, int)'
	 */
	public void testGetEdge() {

	}

	/*
	 * Test method for 'solution.Amida.removeEdge(Edge)'
	 */
	public void testRemoveEdge() {

	}

	/*
	 * Test method for 'solution.Amida.removeAll()'
	 */
	public void testRemoveAll() {

	}

	/*
	 * Test method for 'solution.Amida.exitLine(int)'
	 */
	public void testExitLine() {

	}

}
