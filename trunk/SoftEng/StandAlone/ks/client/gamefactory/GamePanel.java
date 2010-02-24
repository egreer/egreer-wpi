package ks.client.gamefactory;

import ks.common.games.Solitaire;
import ks.common.view.BackgroundCopier;
import ks.common.view.Container;

import java.awt.Button;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends Panel implements IUpdateStatus {
	/** Since V2.2: Must hold active game until timer expires. */
	Solitaire activeGame = null;

	Panel topPanel;
	Button newGameButton;
	Container gameContainer;
	Panel contentsPanel;
	
	public GamePanel() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new java.awt.BorderLayout());
		setSize(769, 635);
		add(getControlPane(), "North");
		add(getContentsPane(), "Center");
		
	}
	
	public java.awt.Panel getContentsPane() {
		if (contentsPanel == null) {
			contentsPanel = new java.awt.Panel();
			contentsPanel.setName("ContentsPane");
			contentsPanel.setLayout(null);
			contentsPanel.setBackground(new java.awt.Color(0,138,0));
			contentsPanel.add(getContainer(), getContainer().getName());
		}
		return contentsPanel;
	}
	
	public java.awt.Panel getControlPane() {
		if (topPanel == null) {
			topPanel = new java.awt.Panel();
			topPanel.setName("TopPane");
			topPanel.setSize(769, 40);
			topPanel.setLayout(null);
			topPanel.setBackground(new java.awt.Color(0,138,0));
			topPanel.add(getnewGame(), null);
		}
		return topPanel;
	}
	
	public Container getContainer() {
		if (gameContainer == null) {
			gameContainer = new Container();
			gameContainer.setUpdateStatus(this);
			gameContainer.setName("Container");
			gameContainer.setBackground(new java.awt.Color(0,138,0));
			gameContainer.setBounds(0, 0, 764, 512);
		}
		
		return gameContainer;
	}
	
	/**
	 * This method initializes newGameButton	
	 * 	
	 * @return java.awt.Button	
	 */
	public Button getnewGame() {
		if (newGameButton == null) {
			newGameButton = new Button();
			newGameButton.setBounds(new Rectangle(12, 12, 72, 23));
			newGameButton.setActionCommand("");
			newGameButton.setLabel("New Hand");
			newGameButton.setName("newGame");
			newGameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Container cont = getContainer();
					cont.forceNextHand();
				}
			});
		}
		return newGameButton;
	}
	
	/**
	 * Initialize game canvas using specific solitaire game. 
	 * 
	 * Note that nothing will actually become visible until activate(boolean) is called.
	 * 
	 * Called by the entity that creates the GameWindow. Must be called before setting GameWindow 
	 * to Visible.
	 * <p>
	 * since v1.6.2 this enables a button "Solve" if the solitaire game is an
	 * instance of SolvableSolitaire.
	 * <p>
	 * Creation date: (10/1/01 9:10:22 PM)
	 * @param game Solitaire
	 */
	public void initialize(Solitaire game) {

		activeGame = game;
		
		// resize GameWindow to fit the size of the Solitaire preferred size.
		java.awt.Dimension d = game.getPreferredSize();
		setSize (d);
		
		gameContainer.initialize (game);

		// Default Visitor (if one not already set)
		if (gameContainer.getVisitor() == null) {
			gameContainer.setVisitor (new BackgroundCopier(gameContainer.getGraphics(), gameContainer.getBackground()));
		}

		// wire up mouse listeners (both motion and regular). All mouse events
		// are first sent to the container, and then to the game itself.
		gameContainer.addMouseListener (new MouseListener() {
			public void mouseClicked(MouseEvent e) { gameContainer.processMouseEvent(e); }
			public void mouseEntered(MouseEvent e) { gameContainer.processMouseEvent(e); }
			public void mouseExited(MouseEvent e) { gameContainer.processMouseEvent(e); }
			public void mousePressed(MouseEvent e) { gameContainer.processMouseEvent(e); }
			public void mouseReleased(MouseEvent e) { gameContainer.processMouseEvent(e); }
			
		});
		
		gameContainer.addMouseMotionListener (new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) { gameContainer.processMouseEvent(e); }
			public void mouseMoved(MouseEvent e) { gameContainer.processMouseEvent(e); }
		});
	}

	public void incrementNumNewHands() {
		System.out.println ("GamePanel: numNewHands++");
	}

	public void incrementNumWins() {
		System.out.println ("GamePanel: numWins++");
	}

	public void updateScore(int score) {
		System.out.println ("updateScore:" + score);
	}
}
