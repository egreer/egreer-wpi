package lpf.GUIs.GridView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import lpf.GUIs.SudokuAlphaMainGUI;

/**
 * 
 * The view for the Grid
 * 
 * @author Nik Deapen
 *
 */
public class GridView extends JPanel {

	/**
	 * Serial Version User ID
	 */
	private static final long serialVersionUID = 408133485050562208L;
	
	private GridEventHandler eventHandler;
	private GridPainter gridPainter;
	
	/**
	 * Creates a Grid View with a given grid manager
	 * @param gridManager
	 */
	public GridView(PuzzleManager gridManager){
		// set my background color
		this.setBackground(SudokuAlphaMainGUI.paleGreen);
		
		// add the event handler
		eventHandler = new GridEventHandler(gridManager, this);
		
		// add the painter
		gridPainter = new GridPainter(gridManager, this);
		
		// set up the event handlers
		this.addMouseListener(eventHandler);
		this.addFocusListener(eventHandler);
		this.addKeyListener(eventHandler);
		
		// give myself to the grid manager
		gridManager.setView(this);
	}
	
	/**
	 * Handles the painting of the grid view
	 */ 
	@Override
	public void paint(Graphics graphics){
		if (this.getWidth() <= 0 || this.getHeight() <= 0)
			return;
		
		// create the image
		BufferedImage myImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics g = myImage.getGraphics();
		
		// paint the background
		super.paint(g);
		
		// paint the grid
		this.gridPainter.paint(g);
		
		// paint to the canvas
		graphics.drawImage(myImage,0,0,this);
	}
		
}
