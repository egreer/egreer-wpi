package tictactoe.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tictactoe.TicTacToe;
import tictactoe.Space;


/**
 * Responsible for Drawing the TicTacToe Canvas.
 * 
 * This means that it is also responsible for interpreting XY events over this canvas
 * and determining the Space which was selected by the user.
 * 
 * @author heineman
 */
public class Drawer {
	/** Width/Height of squares. */
	public static final int CELLSIZE = 60;
	
	/** Offset (x,y) location for Tic Tac Toe Board. */
	public static final int OFFSET_X = 10;
	public static final int OFFSET_Y = 10;
	
	/** Inset into each Cell on screen. */
	public static final int INSET = 10;

	/**
	 * Draw marker for player in specific location.
	 * 
	 * @param g    Graphics object that controls the drawing
	 * @param c    column of cell to draw
	 * @param r    row of cell to draw
	 * @param m    char to draw (or ' ' for empty space)
	 */
	private void drawSpot(Graphics g, int r, int c, char m) {
		// erase if SPACE
		if (m == ' ') {  // should be made a constant in interface.
			g.setColor(Color.lightGray);
			g.fillRect(30 + CELLSIZE*c, 30+CELLSIZE*r, 40, 40);
			return;
		}
		
		// draw extra large character. 
		// NOTE THAT DRAW STRING IS THE ONLY METHOD THAT DEMANDS
		// THE ANCHOR FOR THE COORDINATES BE LOWER LEFT CORNER
		g.setColor (Color.black);
		g.drawString ("" + m, 40+CELLSIZE*c, 60+CELLSIZE*r);
	}
    
	/**
	 * Interpret XY given the way the board is drawn, and 
	 * return a Cell representing (col, row) for that mouse
	 * point.
	 * 
	 * @param x    x-coordinate of mouse event.
	 * @param y    y-coordinate of mouse event.
	 * @return     Space object representing (r,c) value
	 */
	public Space interpretXY (int x, int y)  {
		int col = (x - OFFSET_X - INSET)/CELLSIZE;
		int row = (y - OFFSET_Y - INSET)/CELLSIZE;
		
		return new Space (row, col);
	}
	
	
	/**
	 * Responsible for drawing the board.
	 * 
	 * Note: A great deal of trust is placed on the board, since otherwise this
	 * class could manipulate the board as it is being drawn.
	 * 
	 * @param g        Graphics entity into which we will draw our board.
	 * @param board    the Actual TicTacToe game in progress
	 */
	public void drawBoard (Graphics g, TicTacToe board) {
		if (board == null) return;  // nothing to draw?
		
	    // make sure font is at 36 point for large size. Note that
		// this should only create a new Font object once.
		Font f = g.getFont();
		if (f.getSize() != 36) {
			f = new Font (f.getFamily(), Font.PLAIN, 36);
			g.setFont (f);
		}
		
		int width = CELLSIZE*3 + INSET * (2);
		int height = CELLSIZE*3 + INSET * (2);
		g.setColor(Color.lightGray);
		g.fillRect (OFFSET_X, OFFSET_Y, width, height);							
				
		// Draw Axes
		g.setColor (Color.black);
		for (int i = 1; i < 3; i++) {
			g.drawLine(OFFSET_X + INSET + CELLSIZE*i, OFFSET_Y + INSET, OFFSET_X + INSET + CELLSIZE*i, height);  // vertical lines
			g.drawLine(OFFSET_X + INSET, OFFSET_Y + 10+CELLSIZE*i, width, OFFSET_Y + INSET+CELLSIZE*i);      // horizontal lines
		}
		
		if (board != null) {
			for (int c = 0; c < 3; c++) {
				for (int r = 0; r < 3; r++) {
					drawSpot (g, r, c, board.getMark(r,c));
				}
			}
		}
	}
	
}
