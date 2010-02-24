package amida.gui;

import java.awt.Frame;

import amida.AmidaException;
import amida.AmidaInterface;
import java.awt.Button;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTextField;

public class AmidaFrame extends Frame {

	/** Amida Board. */
	AmidaInterface  board;
	
	private Button resetButton = null;
	private AmidaCanvas amidaCanvas = null;
	
	/** Buttons for controlling Amida Board. */
	private Button removeButton = null;
	private Button addButton = null;

	private JTextField statusMessage = null;
	
	/**
	 * This is the default constructor
	 */
	public AmidaFrame() {
		super();
		initialize();
	}

	/**
	 * This is the default constructor
	 */
	public AmidaFrame(AmidaInterface board) {
		super();
		
		this.board = board;
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(null);
		this.setSize(620, 556);
		this.setTitle("Amida");
		this.add(getResetButton(), null);
		this.add(getAmidaCanvas(), null);
		this.add(getRemoveButton(), null);
		this.add(getAddButton(), null);
		this.add(getStatusMessage(), null);
		
		addComponentListener(new ComponentListener() {

			public void componentResized(ComponentEvent e) {
				adjust();
			}

			public void componentMoved(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
			
		});
		
		// Create an object that is responsible for reacting to window events. In particular,
		// when the user closes the window, exit the program.
		addWindowListener(new WindowListener() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}

			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			
		});
	}

	/**
	 * Adjust the inner window.
	 */
	public void adjust() {
		int width = this.getWidth();
		int height = this.getHeight();
		
		// save 150 for buttons and 50 on the left side.
		int smallWidth = width - 200;
		int smallHeight = height - 150;
		
		// update things
		amidaCanvas.setSize(smallWidth, smallHeight);
		
		removeButton.setLocation(smallWidth + 50, removeButton.getY());
		addButton.setLocation(smallWidth + 100, addButton.getY());
		resetButton.setLocation(smallWidth + 50, resetButton.getY());
		statusMessage.setLocation(statusMessage.getX(), height - 30);
	}
	
	/**
	 * This method initializes button	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getResetButton() {
		if (resetButton == null) {
			resetButton = new Button();
			resetButton.setName("resetButton");
			resetButton.setBounds(new java.awt.Rectangle(464,405,75,25));
			resetButton.setLabel("Reset");
			
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// remove all lines
					board.removeAll();
					
					// refresh
					amidaCanvas.repaint();
				}
			});
			
		}
		return resetButton;
	}

	/**
	 * This method initializes amidaCanvas	
	 * 	
	 * @return gui.AmidaCanvas	
	 */
	private AmidaCanvas getAmidaCanvas() {
		if (amidaCanvas == null) {
			amidaCanvas = new AmidaCanvas();
			amidaCanvas.setBoard(this.board);
			amidaCanvas.setBounds(new java.awt.Rectangle(30,56,422,376));
			amidaCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					try {
						amidaCanvas.addEdge(x, y);
						statusMessage.setText("");
						amidaCanvas.repaint();
					} catch (AmidaException ae) {
						statusMessage.setText(ae.getMessage());
					}
				}
			});
		}
		return amidaCanvas;
	}

	/**
	 * This method initializes button1	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getRemoveButton() {
		if (removeButton == null) {
			removeButton = new Button();
			removeButton.setBounds(new java.awt.Rectangle(466,61,29,23));
			removeButton.setLabel("-");
			removeButton.setName("removeLineButton");
			removeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// remove line
					board.removeLine();
					
					// refresh
					amidaCanvas.repaint();
				}
			});
		}
		return removeButton;
	}

	/**
	 * This method initializes button2	
	 * 	
	 * @return java.awt.Button	
	 */
	private Button getAddButton() {
		if (addButton == null) {
			addButton = new Button();
			addButton.setBounds(new java.awt.Rectangle(507,61,32,23));
			addButton.setLabel("+");
			addButton.setActionCommand("+");
			addButton.setName("addLineButton");
			addButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// add a line.
					board.addLine();
					
					// refresh
					amidaCanvas.repaint();
				}
			});
		}
		return addButton;
	}

	/**
	 * This method initializes statusMessage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStatusMessage() {
		if (statusMessage == null) {
			statusMessage = new JTextField();
			statusMessage.setBounds(new java.awt.Rectangle(36,471,518,20));
			statusMessage.setEditable(false);
			statusMessage.setText("");
		}
		return statusMessage;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
