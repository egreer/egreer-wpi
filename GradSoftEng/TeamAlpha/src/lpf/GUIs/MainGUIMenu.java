package lpf.GUIs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lpf.configuration.SudokuAlphaGameConfiguration;
import lpf.controllers.NewGameController;
import lpf.controllers.PrintController;
import lpf.controllers.ProcessFailedException;

/**
 * 
 * The menus available in the GUI
 * 
 * @author Nik Deapen
 *
 */
public class MainGUIMenu extends JMenuBar {

	private static final long serialVersionUID = -6128150405145310878L;
	
	/**
	 * Creates a new MainGUIMenu
	 */
	public MainGUIMenu(){
		this.add(getFileMenu());
		this.add(getHelpMenu());
	}

	/**
	 * Gets the GUI's Help menu
	 * 
	 * @return	the Help menu
	 */
	@Override
	public JMenu getHelpMenu() {
		JMenu m = new JMenu("Help");
		m.add(getHelpMenuItem());
		m.add(getHowToPlayMenuItem());
		m.add(getAboutMenuItem());
		return m;
	}

	/**
	 * Gets the GUI's About menu item
	 * 
	 * @return	the "About" JMenuItem
	 */
	private JMenuItem getAboutMenuItem() {
		JMenuItem m = new JMenuItem("About");
		m.addMouseListener(new MouseListener(){

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
			 * Views the about page when "About" menu item is pressed
			 * 
			 * @param arg0
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					new FileViewer().viewWebPage(new File("files/about.html"));
				} catch (IOException e) {
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return m;
	}

	/**
	 * Gets the GUI's How to Play menu item
	 * 
	 * @return	the "How to Play" JMenuItem
	 */
	private JMenuItem getHowToPlayMenuItem() {
		JMenuItem m = new JMenuItem("HowToPlay");
		m.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			/**
			 * Views the how to play page when "How to Play" menu item is pressed
			 * 
			 * @param arg0
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new FileViewer().viewWebPage(new File("files/help.html"));
				} catch (IOException ex) {
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return m;
	}

	/**
	 * Gets the GUI's Help menu item
	 * 
	 * @return	the "Help" JMenuItem
	 */
	private JMenuItem getHelpMenuItem() {
		JMenuItem m = new JMenuItem("Help");
		m.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			/**
			 * Views the help page when "Help" menu item is pressed
			 * 
			 * @param arg0
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					new FileViewer().viewWebPage(new File("files/help.html"));
				} catch (IOException ex) {
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return m;
	}

	/**
	 * Gets the GUI's File menu
	 * 
	 * @return	the File menu
	 */
	private JMenu getFileMenu() {
		JMenu m = new JMenu("File");
		m.add(getNewGameMenuItem());
		m.add(getSettingsMenuItem());
		m.add(getPrintMenuItem());
		m.add(getExitMenuItem());
		return m;
	}

	/**
	 * Gets the GUI's Exit menu item
	 * 
	 * @return	the "Exit" JMenuItem
	 */
	private JMenuItem getExitMenuItem() {
		JMenuItem m = new JMenuItem("Exit");
		m.addMouseListener(new MouseListener() {

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
			 * Exits the game when "Exit" menu item is pressed
			 * 
			 * @param arg0
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				config.getGUI().dispose();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return m;
	}

	/**
	 * Gets the GUI's Print menu item
	 * 
	 * @return	the "Print" JMenuItem
	 */
	private JMenuItem getPrintMenuItem() {
		JMenuItem m = new JMenuItem("Print");
		m.addMouseListener(new MouseListener(){

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
			 * Prints the current GridView when the "Print" menu item is pressed
			 * 
			 * @param arg0
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					new PrintController(config.getGUI().getGridView()).process();
				} catch (ProcessFailedException e) {}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return m;
	}

	/**
	 * Gets the GUI's Settings menu item
	 * 
	 * @return	the "Settings" JMenuItem
	 */
	private JMenuItem getSettingsMenuItem() {
		JMenuItem m = new JMenuItem("Settings");
		m.addMouseListener(new MouseListener (){

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
			 * Opens the settings GUI when "Settings" menu item is pressed
			 * 
			 * @param arg0
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				new SettingsGUI();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return m;
	}

	/**
	 * Gets the GUI's New Game menu item
	 * 
	 * @return	the "New Game" JMenuItem
	 */
	private JMenuItem getNewGameMenuItem() {
		JMenuItem m = new JMenuItem("New Game");
		m.addMouseListener(new MouseListener(){
		
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			/**
			 * Starts a new game when the "New Game" menu item is pressed
			 * 
			 * @param arg0
			 */
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					new NewGameController().process();
				} catch (ProcessFailedException e) {
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		
		});
	
		return m;
	}

}
