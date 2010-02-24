package ks.client.gamefactory;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Contains three panels for game play. 
 * <p>
 * The first reflects the connect tab, to be used when connecting to the 
 * game server.
 * <p>
 * The second reflects the lobby of the room to which the client has connected.
 * <p>
 * The third reflects any game that may be in progress; note that the client may
 * only be involved in a single game at a time.
 * 
 * Adapted from http://www.tufat.com/java/CardLayoutExampleFrame.html
 */
public class SampleRoomGUI extends Panel {
	/** The three tabs. */
	private Tab1 connect;
	private Tab2 lobby;
	private Tab3 game;

	/** The enclosing panel. */
	private Panel cardPanel;

	/** layout information. */
	private CardLayout card;

	/** Colors to inform the tabs. */
	private Color activeColor = new Color(200,200,200);
	private Color inactiveColor = new Color(150,150,150);

	// used when no game is available (or after game has been closed).
	GameCard standIn;
	
	public SampleRoomGUI() {
		connect = new Tab1();
		lobby = new Tab2();
		game = new Tab3();

		setBackground(activeColor);
		setBounds(0,0,600,650);

		Panel controls = new Panel(new GridLayout(1,3));
		controls.add(connect);
		controls.add(lobby);
		controls.add(game);

		card = new CardLayout();
		cardPanel = new Panel(card);

		cardPanel.add("Connect", new ConnectCard());
		cardPanel.add("Lobby", new LobbyCard());
		standIn = new GameCard();
		cardPanel.add("Game", standIn);

		Panel border = new Panel(new BorderLayout());

		border.add(controls, "North");
		border.add(cardPanel, "Center");
		add(border);

		setVisible(true);
	}

	/** Sets the game layout accordingly. */
	public void updateGame(Component game) {
		cardPanel.remove(standIn);
		cardPanel.add("Game", game);
		cardPanel.invalidate();
		cardPanel.validate();
	}
	
	// Left tab for card1.
	class Tab1 extends Panel {
		public Tab1() {			
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e)  {
					card.show(cardPanel, "Connect");
					setBackground(activeColor);
					lobby.setBackground(inactiveColor);
					game.setBackground(inactiveColor);
				}
			});
		}

		public void paint(Graphics g) { g.drawString("Connect",25,20); }

		// only need this for one of the tabs
		public Dimension getPreferredSize() {
			return new Dimension(100,35);
		}
	}

	// Middle tab
	class Tab2 extends Panel {
		public Tab2() {     
			setBackground(inactiveColor);

			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					card.show(cardPanel, "Lobby");
					setBackground(activeColor);
					connect.setBackground(inactiveColor);
					game.setBackground(inactiveColor);
				}
			});
		}

		public void paint(Graphics g) { g.drawString("Lobby",25,20); }
	}

	// Right tab
	class Tab3 extends Panel {
		public Tab3() {       
			setBackground(inactiveColor);

			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					card.show(cardPanel, "Game");
					setBackground(activeColor);
					connect.setBackground(inactiveColor);
					lobby.setBackground(inactiveColor);
				}
			});
		}

		public void paint(Graphics g) { g.drawString("Game",25,20); } 
	}


	// This is a skeleton class, to be replaced by reference to real content
	class ConnectCard extends Panel {
		public ConnectCard() {
			add(new Label("Connect Card"));
			setBackground(activeColor);   
		}
	}

	// This is a skeleton class, to be replaced by reference to real content
	class LobbyCard extends Panel {
		public LobbyCard() {
			add(new Label("Lobby Card"));
			setBackground(activeColor);   
		}
	}

	// This is a skeleton class, to be replaced by the game when available. 
	class GameCard extends Panel {
		public GameCard() {
			add(new Label("Game Card"));
			setBackground(activeColor);
		}
	}

}
