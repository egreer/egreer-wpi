package amida.gui;

import java.awt.*;

import amida.AmidaException;
import amida.AmidaInterface;
import amida.Edge;

/**
 * Specialized GUI canvas in which to draw the Amida board.
 * 
 * @author heineman
 */
public class AmidaCanvas extends Canvas {
	
	/** inset from bottom position. */
	public final static int inset = 20;
	
	/**
	 * Not formally required, but removes warning messages in Eclipse.
	 */
	private static final long serialVersionUID = -3320258175783927160L;

	/** The board to use. */
	AmidaInterface board;
	
	/** Rely on base class for constructor. */
	public AmidaCanvas () {
		super ();
	}
	
	/**
     * Provide an alternate paint method.
     */
	public void paint (Graphics g) {
		if (board == null) {
			g.drawString ("Empty Board", 100, 100);
			return;
		}
		
		int width = this.getWidth();
		int n = board.numLines();
		if (n == 0) {
			g.drawString ("No Lines", 100, 100);
			return;
		}
		
		int size = width/n;
		
		// draw vertical lines and their headers
		for (int line = 0; line < n; line++) {
			g.drawLine(line*size, inset, line*size, getHeight() - inset);
			g.drawLine(line*size+1, inset, line*size+1, getHeight() - inset);
			g.drawLine(line*size+2, inset, line*size+2, getHeight() - inset);
			g.drawString ("" + (line+1), line*size, inset - 5);
		}
		
		// draw each horizontal edge, but only to the right
		try {
			for (int line = 1; line < n; line++) {
				int num = board.numEdges(line);
				
				// draw each edge, which are always to the right
				for (int j = 1 ; j <= num; j++) {
					Edge e = board.getEdge(line, j);
	
					g.drawLine((line-1)*size, e.getVerticalPosition(), line*size, e.getVerticalPosition());
				}
			}
			
			// draw the ending locations
			for (int line = 1; line <= n; line++) {
				int end = board.exitLine(line);
				g.drawString ("" + line, (end-1)*size, getHeight());
			}
		} catch (AmidaException ae) {
			System.err.println (ae.toString());
		}
	}

	/** 
	 * Pass in the amida board to be used.
	 * 
	 * @param board
	 */
	public void setBoard(AmidaInterface board) {
		this.board = board;
		
		repaint();
	}

	/**
	 * Add an edge.
	 * 
	 * Throw exception if problem 
	 * 
	 * @param x     x-coordinate on canvas where mouse pressed
	 * @param y     y-coordinate on canvas where mouse pressed
	 */
	public void addEdge(int x, int y) throws AmidaException {
		int width = this.getWidth();
		int n = board.numLines();
		if (n == 0) {
			Toolkit.getDefaultToolkit().beep();  // BEEP!
			return;
		}
		
		if (y < inset || y > getHeight() - inset) {
			Toolkit.getDefaultToolkit().beep(); // BEEP
			return;
		}
		
		int size = width/n;
		
		// Locate edges
		int line = 1 + (x/size);
		
		board.addEdge(line, y);
	}

}
