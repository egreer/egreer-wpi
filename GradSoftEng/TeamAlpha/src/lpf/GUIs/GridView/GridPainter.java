package lpf.GUIs.GridView;

import java.awt.Graphics;

import lpf.GUIs.GridView.painters.CellsPainter;
import lpf.GUIs.GridView.painters.GridLinesPainter;
import lpf.GUIs.GridView.painters.GridSectionsPainter;
import lpf.GUIs.GridView.painters.HighlightedCellPainter;

/**
 * An Object that paints the grid
 * @author Nik Deapen
 *
 */
public class GridPainter {
	
	/**
	 * Sub painters
	 */
	GridLinesPainter glp;
	GridSectionsPainter gsp;
	HighlightedCellPainter hcp;
	CellsPainter csp;
	
	/*
	 * Creates an object that knows how to paint the grid
	 */
	public GridPainter(PuzzleManager gm, GridView gp){
		glp = new GridLinesPainter(gp,gm);
		gsp = new GridSectionsPainter(gp,gm);
		hcp = new HighlightedCellPainter(gm,gp);
		csp = new CellsPainter(gm,gp);
	}
	
	/**
	 * Defines the order to paint the grid
	 * I think this way is better than decorator because its obvious whats happening
	 * @param g graphics object
	 */
	public void paint(Graphics g){
		hcp.paint(g);
		glp.paint(g);
		gsp.paint(g);
		csp.paint(g);
	}
}
