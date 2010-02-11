package lpf.GUIs.GridView.painters;

import java.awt.Color;
import java.awt.Graphics;

import lpf.GUIs.GridView.PuzzleManager;
import lpf.GUIs.GridView.GridView;
import lpf.configuration.SudokuAlphaGameConfiguration;

/**
 * 
 * A painter class for the grid's sections
 * 
 * @author Nik Deapen
 *
 */
public class GridSectionsPainter {
	
	SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
	
	GridView gridPanel;
	PuzzleManager gridManager;
	
	/**
	 * Creates a new GridSectionsPainter
	 * 
	 * @param gp				the GridView whose sections are getting painted
	 * @param gm				the PuzzleManager whose GridView is affected
	 */
	public GridSectionsPainter(GridView gp, PuzzleManager gm){
		this.gridManager = gm;
		this.gridPanel = gp;	
	}
	
	/**
	 * Paints the grid sections  (just thick lines)
	 * @param g
	 */
	public void paint(Graphics g){
		int numSections = sqrt(config.getCharacterSet().getNumValues());
		int sectionWidth = gridPanel.getWidth() / numSections;
		int panelWidth = gridPanel.getWidth();
		
		g.setColor(Color.black);
		
		int cur = 0;
		for (int i=0; i <= numSections; i++){
			// draw lines to the right and left of the normal line
			g.drawLine(cur+1,0,cur+1,panelWidth);
			g.drawLine(cur-1,0,cur-1,panelWidth);
			g.drawLine(0,cur+1,panelWidth,cur+1);
			g.drawLine(0,cur-1,panelWidth,cur-1);
			cur += sectionWidth;
		}
	}
	
	
	int sqrt(int x){
		int a = 0;
		while (a*a < x)
			a++;
		return a;
	}
}
