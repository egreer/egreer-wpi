package lpf.GUIs.GridView.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.GUIs.GridView.GridView;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.model.core.valueSets.CharacterSet;

/**
 * Paints the highlight on the cell
 * 
 * @author Nik Deapen
 *
 */
public class HighlightedCellPainter {
	
	CharacterSet cs = SudokuAlphaGameConfiguration.getInstance().getCharacterSet();
	PuzzleManager gridManager;
	GridView gridPanel;
	Color markColor = Color.yellow;
	Color digitColor = new Color(.388235f, .6902f, 1.0f);
	
	/**
	 * Creates a new HighlightedCellPainter
	 * 
	 * @param gm			the PuzzleManager whose GridView is affected
	 * @param gp			the GridView whose Cell will be highlighted
	 */
	public HighlightedCellPainter(PuzzleManager gm, GridView gp){
		this.gridManager = gm;
		this.gridPanel = gp;
	}

	/**
	 * Paints the highlighted Cell
	 * 
	 * @param g
	 */
	public void paint(Graphics g){
		Rectangle r = gridManager.getSelectedRectangle();
		if (r == null)
			return;
		
		if (gridManager.isMarkMode())
			g.setColor(markColor);
		else 
			g.setColor(digitColor);
		
		// fill the cell with the color
		g.fillRect(r.x, r.y, r.width, r.height);
	}
}
