package lpf.GUIs.GridView.painters;

import java.awt.Color;
import java.awt.Graphics;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.GUIs.GridView.GridView;
import lpf.configuration.SudokuAlphaGameConfiguration;

/**
 * 
 * A painter class for the Grid's Lines
 * 
 * @author Nik Deapen
 *
 */
public class GridLinesPainter {
	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	
	GridView gridPanel;
	PuzzleManager gridManager;
	
	/**
	 * Creates a new GridLinesPainter
	 * 
	 * @param gp				the GridView whose lines are being painted
	 * @param gm				the PuzzleManager whose GridView is being affected
	 */
	public GridLinesPainter(GridView gp, PuzzleManager gm){
		this.gridManager = gm;
		this.gridPanel = gp;	
	}
	
	/**
	 * Paints the lines of the grid
	 * @param g
	 */
	public void paint(Graphics g){
		int numSections = config.getCharacterSet().getNumValues();
		int sectionWidth = gridPanel.getWidth() / numSections;
		int panelWidth = gridPanel.getWidth();
		
		g.setColor(Color.black);
		
		int cur = 0;
		for (int i=0; i <= numSections; i++){
			g.drawLine(cur,0,cur,panelWidth);
			g.drawLine(0,cur,panelWidth,cur);
			cur += sectionWidth;
		}
	}
}
