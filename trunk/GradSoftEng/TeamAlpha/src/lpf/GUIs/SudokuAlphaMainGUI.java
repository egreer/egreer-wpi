package lpf.GUIs;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lpf.GUIs.GridView.GridView;
import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.controllers.ClearAllGridMarksController;
import lpf.controllers.GiveUpController;
import lpf.controllers.HintController;
import lpf.controllers.ProcessFailedException;
import lpf.controllers.RedoController;
import lpf.controllers.RemoveIncorrectController;
import lpf.controllers.ResetGridController;
import lpf.controllers.UndoController;

/**
 * 
 * The main GUI for the Sudoku Alpha game
 * 
 * @author Nik Deapen
 *
 */
public class SudokuAlphaMainGUI extends JFrame {
	
	/**
	 * Serializeable ID
	 */
	private static final long serialVersionUID = 327514466251112015L;
	public static final Color paleGreen = new Color(0.596078f,0.984314f,0.596078f);
	public static final Color forestGreen = new Color(0.125490196f,0.51372549f,0.125490196f);
	MainPanel myMainPanel;
	
	/**
	 * Creates the SudokuAlphaMainGUI
	 */
	public SudokuAlphaMainGUI(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Sudoku Alpha");
		this.setSize(750,600);
		myMainPanel = new MainPanel();
		this.add(myMainPanel);
		this.setJMenuBar(new MainGUIMenu());
		this.setVisible(true);
	}
	
	/**
	 * Gets the GUI's GridView
	 * 
	 * @return	the GUI's current GridView
	 */
	public GridView getGridView(){
		return myMainPanel.getGridView();
	}
	
	/**
	 * Sets the GUI's GridView
	 * 
	 * @param gv			the new GridView
	 */
	public void setGridView(GridView gv){
		myMainPanel.setGridView(gv);
	}
	
	/**
	 * Repaints the GUI's Grid
	 */
	public void repaintGrid() {
		this.myMainPanel.repaintGrid();
	}
	
	/**
	 * 
	 * The main panel for the GUI 
	 * 
	 * @author Nik Deapen
	 *
	 */
	class MainPanel extends JPanel {
		
		/**
		 * Serializable ID
		 */
		private static final long serialVersionUID = -7282581456233774623L;
		private SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
		private GridView myGridView;
		
		/**
		 * Creates the main panel to put on the frame
		 */
		public MainPanel(){
			initailizeMainComponents();
			this.setBackground(SudokuAlphaMainGUI.forestGreen);
		}

		/**
		 * Repaints the Grid
		 */
		public void repaintGrid() {
			this.myGridView.repaint();
		}

		/**
		 * Initializes the MainPanel's components
		 */
		private void initailizeMainComponents() {
			this.setLayout(new MainGUILayoutManager()); 
			addControlButtons();
		}

		/**
		 * Sets the MainPanel's myGridView
		 * 
		 * @param gv			the new GridView
		 */
		public void setGridView(GridView gv) {
			if (gv == null)
				throw new IllegalArgumentException();
			
			if (myGridView != null)
				this.remove(myGridView);
			this.add(gv, MainGUILayoutManager.MAIN_PANEL);
			this.myGridView = gv;
		}
		
		/**
		 * Gets the MainPanel's myGridView
		 * 
		 * @return	the MainPanel's current myGridView
		 */
		public GridView getGridView(){
			return myGridView;
		}

		/**
		 * Adds the Control Buttons for the MainPanel
		 */
		private void addControlButtons() {
			this.add(new HintButton(), MainGUILayoutManager.CONTROL_BUTTON);
			this.add(new UndoButton(), MainGUILayoutManager.CONTROL_BUTTON);
			this.add(new RedoButton(), MainGUILayoutManager.CONTROL_BUTTON);
			this.add(new ResetGridButton(), MainGUILayoutManager.CONTROL_BUTTON);
			this.add(new GiveUpButton(), MainGUILayoutManager.CONTROL_BUTTON);
			this.add(new ClearAllMarksOnGridButton(), MainGUILayoutManager.CONTROL_BUTTON);
			this.add(new BigHintButton(),MainGUILayoutManager.CONTROL_BUTTON);
		}
		
		
		/**
		 * The control buttons for the SudokuAlphaMainGUI
		 * 
		 * @author Nik Deapen
		 *
		 */
		class ControlButton extends JButton implements MouseListener {

			private static final long serialVersionUID = 1132143241L;
			
			/**
			 * Creates a new ControlButton
			 */
			public ControlButton(){
				this.addMouseListener(this);
				this.setBackground(SudokuAlphaMainGUI.paleGreen);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			
			
		}
		
		/**
		 * 
		 * The Hint button for the GUI that adds a correct digit to the
		 * Player Grid when clicked
		 * 
		 * @author Nik Deapen
		 *
		 */
		class HintButton extends ControlButton {
			
			private static final long serialVersionUID = 1L;
			
			/**
			 * Creates a new HintButton
			 */
			public HintButton(){
				this.setText("Hint");
			}
		
			/**
			 * Adds a correct digit to the current Player Grid via the HintController
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					new HintController(config.getCurrentPuzzle()).process();
				}
				catch (ProcessFailedException c){
					
				}
				super.mouseClicked(e);
			}
		}
		
		/**
		 * 
		 * The Undo button for the GUI that undoes a move on the Player Grid
		 * when clicked
		 * 
		 * @author Nik Deapen
		 *
		 */
		class UndoButton extends ControlButton {
			
			private static final long serialVersionUID = -8488050293084737930L;

			/**
			 * Creates a new UndoButton
			 */
			public UndoButton(){
				this.setText("Undo");			
			}
		
			/**
			 * Undoes the last move available if applicable via the UndoController
			 * 
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					new UndoController().process();
				} catch (ProcessFailedException e1) {
					Toolkit.getDefaultToolkit().beep();
				}
				super.mouseClicked(e);
			}
		}

		/**
		 * 
		 * The Redo button for the GUI that redoes a move on the Player Grid
		 * when clicked
		 * 
		 * @author Nik Deapen
		 *
		 */
		class RedoButton extends ControlButton {
		
			private static final long serialVersionUID = 2845944455205522960L;

			/**
			 * Creates a new RedoButton
			 */
			public RedoButton(){
				this.setText("Redo");
			}
		
			/**
			 * Redoes the last move available if applicable via the RedoController
			 * 
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					new RedoController().process();
				} catch (ProcessFailedException e1) {
					
				}

				super.mouseClicked(e);
			}
		}

		/**
		 * 
		 * The Reset Grid button for the GUI that resets the Player Grid back
		 * to the Initial Grid when clicked 
		 * 
		 * @author Nik Deapen
		 *
		 */
		class ResetGridButton extends ControlButton {

			private static final long serialVersionUID = 905048187215015562L;

			/**
			 * Creates a new ResetGridButton
			 */
			public ResetGridButton(){
				this.setText("Reset Grid");
			}
		
			/**
			 * Resets the Player Grid back to the Initial Grid when clicked
			 * via the ResetGridController
			 * 
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					new ResetGridController(config.getCurrentPuzzle()).process();
				} catch (ProcessFailedException e1) {
				}
				super.mouseClicked(e);
			}
		}
		
		/**
		 * 
		 * The Give Up button for the GUI that displays the puzzle's solution and
		 * tells the player that they have lost when clicked
		 * 
		 * @author Nik Deapen
		 *
		 */
		class GiveUpButton extends ControlButton {
			
			private static final long serialVersionUID = 1L;

			/**
			 * Creates a new GiveUpButton
			 */
			public GiveUpButton(){
				this.setText("Give Up");
			}
		
			/**
			 * Displays the puzzle solution and a "You Lose" window via the
			 * GiveUpController
			 * 
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					new GiveUpController().process();
				} catch (ProcessFailedException e1) {
				}
				super.mouseClicked(e);
			}		
		}
		
		/**
		 * 
		 * The Clear All Marks button that removes all of the marks on the
		 * Grid when clicked
		 * 
		 * @author Nik Deapen
		 *
		 */
		class ClearAllMarksOnGridButton extends ControlButton {
			
			private static final long serialVersionUID = 1L;
			
			/**
			 * Creates a new ClearAllMarksOnGridButton
			 */
			public ClearAllMarksOnGridButton(){
				this.setText("Clear All Marks");
			}
		
			/**
			 * Clears all of the marks on the grid via the ClearAllGridMarksController
			 * 
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					new ClearAllGridMarksController(config.getCurrentPuzzle().getPlayerGrid()).process();
				}
				catch (ProcessFailedException c){
				}
				super.mouseClicked(e);
			}
		}
		
		/**
		 * 
		 * The Big Hint button for the GUI that removes all of the incorrect digits
		 * on the Player Grid when clicked
		 * 
		 * @author Nik Deapen
		 *
		 */
		class BigHintButton extends ControlButton {
			
			private static final long serialVersionUID = 1L;
			
			/**
			 * Creates a new BigHintButton
			 */
			public BigHintButton(){
				this.setText("Big Hint");
			}
		
			/**
			 * Removes all incorrect digits on the Player Grid via the
			 * RemoveIncorrectController
			 * 
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					new RemoveIncorrectController(config.getCurrentPuzzle()).process();
				}
				catch (ProcessFailedException c){
				}
				super.mouseClicked(e);
			}
		}	
	}
}