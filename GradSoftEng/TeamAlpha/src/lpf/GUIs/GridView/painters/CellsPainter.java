package lpf.GUIs.GridView.painters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.GUIs.GridView.GridView;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.model.core.Cell;
import lpf.model.core.Grid;
import lpf.model.core.Location;
import lpf.model.core.Value;
import lpf.model.core.valueSets.CharacterSet;

/**
 * 
 * The painter for the Cells
 * 
 * @author Nik Deapen
 * 
 */
public class CellsPainter {

	CharacterSet cs = SudokuAlphaGameConfiguration.getInstance().getCharacterSet();
	PuzzleManager gridManager;
	GridView gridView;
	Font digitFont, markFont;
	Color markColor = Color.DARK_GRAY;
	Color digitColor = new Color(0.2588f,0.0f,.5233f);
	Color fixedColor = Color.black;

	/**
	 * Creates a new CellsPainter
	 * 
	 * @param gm			the PuzzleManager whose Cells are being affected
	 * @param gv			the GridView that contains the painted Cells
	 */
	public CellsPainter(PuzzleManager gm, GridView gv){
		this.gridManager = gm;
		this.gridView = gv;
	}
	
	/**
	 * Paints the cells (just the values)
	 * @param g
	 */
	public void paint(Graphics g){
		int numVals = cs.getNumValues();
		int cellWidth = gridView.getWidth() / numVals;
		int cellHeight = gridView.getHeight() / numVals;
		digitFont = getFont(cellHeight);
		markFont = getFont(cellHeight / sqrt(numVals));
				
		Grid grid = gridManager.getGrid();
				
		for (int x = 0; x < numVals; x++){
			for (int y = 0; y < numVals; y++){
				Cell c = grid.getCellAtLocation(new Location(y+1,(char)(x+'a')));
				boolean fixed = gridManager.isFixed(c);
				// bounds of the cell
				Rectangle bounds = new Rectangle(x*cellWidth,y*cellHeight,cellWidth,cellHeight);
				if (gridManager.getSelectedCell() != null && gridManager.getSelectedCell().loc.equals(c.loc) && gridManager.isMarkMode() && !gridManager.isFixed(c) && c.getDigit() == null){
					paintMarks(g,c.getMarks(),bounds);
				}
				else if (!gridManager.isMarkMode() && c.getDigit() != null){
					paintDigits(g,c.getDigit(),bounds, fixed);
				}
				else {
					if (c.getDigit() != null){
						paintDigits(g,c.getDigit(),bounds, fixed);
					}
					else {
						paintMarks(g,c.getMarks(),bounds);
					}
				}
			}
		}
	}

	/**
	 * Gets the font for the Cell
	 * 
	 * @param cellHeight			the cell's height
	 * @return	the font for the Cell
	 */
	private Font getFont(int cellHeight) {
		return new Font(Font.SERIF, Font.BOLD, cellHeight);
	}

	/**
	 * Paints the digit
	 * 
	 * @param g
	 * @param digit					the digit being painted
	 * @param bounds				the boundaries of the Cell
	 * @param fixed					whether the Cell is a fixed Cell or not
	 */
	private void paintDigits(Graphics g, Value digit, Rectangle bounds, boolean fixed) {
		if (digit == null)
			return;
		if (fixed)
			g.setColor(fixedColor);
		else
			g.setColor(digitColor);
		g.setFont(digitFont);
		g.drawString(digit.value + "", bounds.x+16, bounds.y+48);
	}

	/**
	 * Paints the marks in a cell
	 * 
	 * @param g
	 * @param marks					the marks being painted
	 * @param bounds				the boundaries of the Cell
	 */
	private void paintMarks(Graphics g, Collection<Value> marks, Rectangle bounds) {
		g.setColor(markColor);
		g.setFont(markFont);
		
		Value[] possibleValues = cs.getValues();
		int numRows = this.sqrt(possibleValues.length);
		
		Dimension d = bounds.getSize();
		Dimension newd = new Dimension(d.height/numRows,d.width/numRows);
		
		int curValue = 0;
		for (int i=0; i < numRows; i++){
			for (int j=0; j < numRows; j++){
				if (curValue == possibleValues.length)
					break;
				if (marks.contains(possibleValues[curValue])){
					Point newloc = new Point(bounds.x + i*newd.width, bounds.y + j*newd.width);
					String s = "" + possibleValues[curValue].value;
					g.drawString(s, newloc.x+4, newloc.y+16);
				}
				curValue++;
			}
		}
	}
	
	/**
	 * Gets the square root of x
	 * 
	 * @param x				Integer of which getting the square root of
	 * @return
	 */
	private int sqrt(int x){
		int a = 0;
		while (a*a < x)
			a++;
		return a;
	}
	
}
