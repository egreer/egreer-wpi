package lpf.GUIs;

import java.awt.Component;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lpf.configuration.SudokuAlphaGameConfiguration;

/**
 * 
 * The GUI for adjusting the game's settings 
 * 
 * @author Nik Deapen
 *
 */
public class SettingsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton setLibraryButton = getSetLibraryButton();
	
	private JComboBox difficultyList = getDifficultyList();
	private JLabel difficultyLabel = new JLabel("Select Difficulty");
	
	/**
	 * Creates a new SettingsGUI
	 */
	public SettingsGUI(){
		this.setVisible(true);
		this.setSize(500,200);
		this.setLayout(new SettingsLayoutManager());
		addMainComponents();
	}
	
	/**
	 * Get the list of available difficulty levels to be selected (from 1 - 10)
	 * 
	 * @return	a JComboBox containing all of the difficulties
	 */
	private JComboBox getDifficultyList() {
		String[] difficulties = {"1","2","3","4","5","6","7","8","9","10"};
		JComboBox jcb = new JComboBox(difficulties);
		jcb.addActionListener(new ActionListener(){

			SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();
			
			/**
			 * Sets the difficulty to the selected value
			 * 
			 * @param ae
			 */
			@Override
			public void actionPerformed(ActionEvent ae) {
				JComboBox cb = (JComboBox)ae.getSource();
				String s = (String) cb.getSelectedItem();
				try {
					config.setDifficulty(Integer.parseInt(s));
				} catch(Exception e){
				}
			}
			
		});
		
		return jcb;
	}

	/**
	 * Gets the Choose Library button
	 * 
	 * @return	 the "Choose Library" button
	 */
	private JButton getSetLibraryButton() {
		JButton b =  new JButton("Choose Library");
		b.addMouseListener(new MouseListener(){
			SudokuAlphaGameConfiguration config = SudokuAlphaGameConfiguration.getInstance();

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			/**
			 * Changes the game's library (by making the user search for a
			 * library on a local drive) when the button is pressed
			 * 
			 * @param e
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				config.changeLibrary();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		return b;
	}

	/**
	 * Add the main components for the GUI
	 */
	private void addMainComponents(){
		add(this.difficultyLabel, SettingsLayoutManager.DIFFICULTY_LABEL);
		add(this.difficultyList, SettingsLayoutManager.DIFFICULTY_MENU);
		add(this.setLibraryButton, SettingsLayoutManager.LIBRARY_CHOOSER_BUTTON);
	}
		
	/**
	 * 
	 * Layout Manager for the Settings GUI
	 * 
	 * @author Nik Deapen
	 *
	 */
	class SettingsLayoutManager implements LayoutManager {
		
		public static final String DIFFICULTY_LABEL = "difficultyLabel";
		public static final String DIFFICULTY_MENU = "difficultyMenu";
		public static final String LIBRARY_CHOOSER_BUTTON = "libraryChooserButton";

		/**
		 * Adds layout components for the GUI
		 * 
		 * @param s				the name of the component
		 * @param comp			the component being added
		 */
		@Override
		public void addLayoutComponent(String s, Component comp) {
			comp.setSize(new Dimension(90,40));
			if (s.equals(DIFFICULTY_LABEL)){
				comp.setLocation(new Point(10,10));
			}
			else if (s.equals(DIFFICULTY_MENU)){
				comp.setLocation(new Point(200,10));
			}
			else if (s.equals(LIBRARY_CHOOSER_BUTTON)){
				comp.setLocation(200,60);
				comp.setSize(120, 40);
			}
		}

		@Override
		public void layoutContainer(Container arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Dimension minimumLayoutSize(Container arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Dimension preferredLayoutSize(Container arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void removeLayoutComponent(Component arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
