package ks.client.gamefactory;

import ks.client.gamefactory.skin.*;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireSolver;
import ks.common.view.BackgroundCopier;
import ks.common.view.Container;
import java.awt.Rectangle;
import java.awt.Button;

/**
 * Primary Window for managing the Container within which the solitaire
 * action takes place.<p>
 *
 * GameWindow can speak back to the primary connection through 
 * its helperClient (which it shares with KombatClient).<p>
 *
 * GameWindow receives TimerExpired events from its <code>TimerPanel</code> entity.
 *
 * Creation date: (9/25/01 9:19:21 PM)
 * @author George T. Heineman (heineman@cs.wpi.edu)
 */
public class GameWindow extends java.awt.Frame {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3544957666427353398L;
	private java.awt.Panel ivjContentsPane = null;
	private java.awt.Panel ivjTopPane = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private Container ivjContainer = null;
	
	/** Name of the current user. Needed to include this value within the Container. */
	protected String currentUser = "test";
	
	/** Initial width for the window. Must never shrink less than this. */
	protected int initialWidth = 769;
	
	/** Initial height for the window. Must never shrink less than this. */
	protected int initialHeight = 635;
	
	/** Last height for the window. Start with initial height. */
	protected int lastHeight = 635;
	
	/** Last width for the window. Start with initial width. */
	protected int lastWidth = 769;
	
	/** Since V2.2: Must hold active game until timer expires. */
	Solitaire activeGame = null;  //  @jve:decl-index=0:
	
	/** Since V1.6.2: The solver. */
	protected SolitaireSolver ss = null;
	private Button ivjnewGame = null;
	
	class IvjEventHandler implements java.awt.event.ActionListener, java.awt.event.ComponentListener, java.awt.event.MouseListener, java.awt.event.MouseMotionListener, java.awt.event.WindowListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {

		};
		public void componentHidden(java.awt.event.ComponentEvent e) {};
		public void componentMoved(java.awt.event.ComponentEvent e) {};
		public void componentResized(java.awt.event.ComponentEvent e) {
			gameWindow_ComponentResized(e);
		};
		public void componentShown(java.awt.event.ComponentEvent e) {};
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getSource() == GameWindow.this.getContainer()) 
				connEtoC2(e);
		};
		public void mouseDragged(java.awt.event.MouseEvent e) {
			if (e.getSource() == GameWindow.this.getContainer()) 
				connEtoC3(e);
		};
		public void mouseEntered(java.awt.event.MouseEvent e) {};
		public void mouseExited(java.awt.event.MouseEvent e) {};
		public void mouseMoved(java.awt.event.MouseEvent e) {
			if (e.getSource() == GameWindow.this.getContainer()) 
				connEtoC4(e);
		};
		public void mousePressed(java.awt.event.MouseEvent e) {
			if (e.getSource() == GameWindow.this.getContainer()) 
				connEtoC5(e);
		};
		public void mouseReleased(java.awt.event.MouseEvent e) {
			if (e.getSource() == GameWindow.this.getContainer()) 
				connEtoC6(e);
		};
		public void windowActivated(java.awt.event.WindowEvent e) {};
		public void windowClosed(java.awt.event.WindowEvent e) {
		};
		public void windowClosing(java.awt.event.WindowEvent e) {
			
		};
		public void windowDeactivated(java.awt.event.WindowEvent e) {};
		public void windowDeiconified(java.awt.event.WindowEvent e) {};
		public void windowIconified(java.awt.event.WindowEvent e) {};
		public void windowOpened(java.awt.event.WindowEvent e) {};
	};
/**
 * GameWindow constructor comment.
 */
public GameWindow() {
	super();
	initialize();
}
/**
 * GameWindow constructor comment.
 * @param title java.lang.String
 */
public GameWindow(String title) {
	super(title);
}




/**
 * connEtoC2:  (Container.mouse.mouseClicked(java.awt.event.MouseEvent) --> GameWindow.container_MouseClicked(Ljava.awt.event.MouseEvent;)V)
 * @param arg1 java.awt.event.MouseEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(java.awt.event.MouseEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.container_MouseClicked(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC3:  (Container.mouseMotion.mouseDragged(java.awt.event.MouseEvent) --> GameWindow.container_MouseDragged(Ljava.awt.event.MouseEvent;)V)
 * @param arg1 java.awt.event.MouseEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC3(java.awt.event.MouseEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.container_MouseDragged(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC4:  (Container.mouseMotion.mouseMoved(java.awt.event.MouseEvent) --> GameWindow.container_MouseMoved(Ljava.awt.event.MouseEvent;)V)
 * @param arg1 java.awt.event.MouseEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC4(java.awt.event.MouseEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.container_MouseMoved(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC5:  (Container.mouse.mousePressed(java.awt.event.MouseEvent) --> GameWindow.container_MousePressed(Ljava.awt.event.MouseEvent;)V)
 * @param arg1 java.awt.event.MouseEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC5(java.awt.event.MouseEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.container_MousePressed(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC6:  (Container.mouse.mouseReleased(java.awt.event.MouseEvent) --> GameWindow.container_MouseReleased(Ljava.awt.event.MouseEvent;)V)
 * @param arg1 java.awt.event.MouseEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC6(java.awt.event.MouseEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.container_MouseReleased(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}

/**
 * Route the event into the container handler routines
 */
public void container_MouseClicked(java.awt.event.MouseEvent mouseEvent) {
	// these don't seem to be invoked at all?	
	getContainer().processMouseEvent(mouseEvent);
}
/**
 * Route the event into the container handler routines
 */
public void container_MouseDragged(java.awt.event.MouseEvent mouseEvent) {
	// Invoked but not terribly useful!
	getContainer().processMouseEvent(mouseEvent);
}
/**
 * Route the event into the container handler routines
 */
public void container_MouseMoved(java.awt.event.MouseEvent mouseEvent) {
	// Invoked but not terribly useful!	
	getContainer().processMouseEvent(mouseEvent);
}
/**
 * Route the event into the container handler routines
 */
public void container_MousePressed(java.awt.event.MouseEvent mouseEvent) {
	// these don't seem to be invoked at all?	
	getContainer().processMouseEvent(mouseEvent);
}
/**
 * Route the event into the container handler routines
 */
public void container_MouseReleased(java.awt.event.MouseEvent mouseEvent) {
	// these don't seem to be invoked at all?
	getContainer().processMouseEvent(mouseEvent);
}
/**
 * Construct an image given the specified URL
 * Creation date: (10/9/01 8:26:51 PM)
 * @return java.awt.Image
 * @param u java.net.URL
 */
protected java.awt.Image createBackgroundImage(java.net.URL u) {
	java.awt.Image img = java.awt.Toolkit.getDefaultToolkit().getImage(u);
	java.awt.MediaTracker tracker = new java.awt.MediaTracker (this);
	tracker.addImage(img, 0);


	try {
		tracker.waitForAll();
	} catch (InterruptedException ie) {
		// we have been interrupted, but this is not overly problematic.
		// we will be slowed down on demand.
		System.err.println ("GameWindow::createBackgroundImage() interrupted:" + ie.toString());
	}

	return img;
}
/**
 * Handle the window resize events here.  Resize Container and move control panel
 * down a few pixels to make space.
 * @since V1.6.6
 */
public void gameWindow_ComponentResized(java.awt.event.ComponentEvent componentEvent) {
	// calculate variance and return if no change.
	int deltaY = offsetHeight();
	int deltaX = offsetWidth();
	if ((deltaX == 0) && (deltaY == 0)) return;

	// Push all gui elements at the bottom to be flush against the bottom edge
	// and extend the container dimension out to the right. Simply done because we
	// move the whole contentsPane
	java.awt.Rectangle r; 
	
	// extend Container as much as we dare
	r = getContainer().getBounds();
	getContainer().setBounds (r.x, r.y, r.width + deltaX, r.height + deltaY);

	// If resized, we must manually recalculate background so the refresh
	// routines will properly be able to address newly acquired real estate.
	getContainer().recalculateBackground();
}
/**
 * Comment
 */
public void gameWindow_WindowEvents() {
	int x = 5;
	x = x+ 20;
	System.err.println ("x:" + x);
}
/**
 * Return the Panel2 property value.
 * @return ks.common.view.Container
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private Container getContainer() {
	if (ivjContainer == null) {
		try {
			ivjContainer = new Container();
			ivjContainer.setName("Container");
			ivjContainer.setBackground(new java.awt.Color(0,138,0));
			ivjContainer.setBounds(0, 0, 764, 512);  // was 14,52
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjContainer;
}
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Panel getControlPane() {
	if (ivjTopPane == null) {
		try {
			ivjTopPane = new java.awt.Panel();
			ivjTopPane.setName("TopPane");
			ivjTopPane.setSize(769, 40);
			ivjTopPane.setLayout(null);
			ivjTopPane.setBackground(new java.awt.Color(0,138,0));
			ivjTopPane.add(getnewGame(), null);
		} catch (Exception e) {
			
		}
	}
	return ivjTopPane;
}
/**
 * Return the ContentsPane property value.
 * @return java.awt.Panel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Panel getContentsPane() {
	if (ivjContentsPane == null) {
		try {
			ivjContentsPane = new java.awt.Panel();
			ivjContentsPane.setName("ContentsPane");
			ivjContentsPane.setLayout(null);
			ivjContentsPane.setBackground(new java.awt.Color(0,138,0));
			ivjContentsPane.add(getContainer(), getContainer().getName());
		} catch (Exception e) {
			
		}
	}
	return ivjContentsPane;
}
/**
 * Returns the Container in which solitaire games are being executed.
 * Creation date: (10/3/01 1:12:29 AM)
 * @return ks.common.view.Container
 */
public Container getGameContainer() {
	return getContainer();
}


/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	exception.printStackTrace(System.out);
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	this.addWindowListener(ivjEventHandler);
	getContainer().addMouseListener(ivjEventHandler);
	getContainer().addMouseMotionListener(ivjEventHandler);
	getnewGame().addActionListener(ivjEventHandler);
	this.addComponentListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	// GameWindow setup intends to prevent BAD security things:
	
	
	try {
		// user code begin {1}
		// user code end
		setName("GameWindow");
		setLayout(new java.awt.BorderLayout());
		setSize(769, 635);
		setTitle("Sample Game");
		add(getControlPane(), "North");
		add(getContentsPane(), "Center");
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
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
	
	// Get Game Container and initialize game within it. Make sure
	// that container is as large as preferred size of solitaire...
	Container cont = getGameContainer();
	cont.setBounds(0, 0, Math.max(764, d.width), Math.max(512, d.height));
	
	cont.initialize (game);

	// Default Visitor (if one not already set)
	if (cont.getVisitor() == null) {
		cont.setVisitor (new BackgroundCopier(cont.getGraphics(), cont.getBackground()));
	}

	// wire up mouse listeners (both motion and regular). All mouse events
	// are first sent to the container, and then to the game itself.
	cont.addMouseListener (ivjEventHandler);
	cont.addMouseMotionListener (ivjEventHandler);
	cont.setCurrentUser(currentUser);
}



/**
 * Return the height to offset our GUI elements (if any)
 */
public int offsetHeight () {
	int newHeight = getBounds().height;
	if (newHeight < initialHeight) return 0;  // no change.

	// return the amount to offset each GUI widget.
	int diff = newHeight - lastHeight;
	lastHeight = newHeight;
	return diff;
}
/**
 * Return the width to offset our GUI elements (if any)
 */
public int offsetWidth () {
	int newWidth = getBounds().width;
	if (newWidth < initialWidth) return 0;  // no change.

	// return the amount to offset each GUI widget.
	int diff = newWidth - lastWidth;
	lastWidth = newWidth;
	return diff;
}



/**
 * Set the skin for this GameWindow...
 */
public void setSkin (String skinChoice) {
	Container cont = getGameContainer();

	// Default Visitor (if one not already set)
	if (skinChoice.equals (SkinCatalog.STANDARD_SKIN)) {
		cont.setVisitor (new BackgroundCopier(cont.getGraphics(), cont.getBackground()));
	} else if (skinChoice.equals (SkinCatalog.SCROLLING_BALLOONS)) {
		java.net.URL u = this.getClass().getResource ("/backgrounds/FloatingImage.gif");
		java.awt.Image image = createBackgroundImage (u);
		if (image != null) {
			cont.setVisitor (new ImageScroller(image, cont));
		} else {
			System.err.println ("Unable to create ImageScroller.");
		}
	} else if (skinChoice.equals (SkinCatalog.FIXED_IMAGE)) {
		java.net.URL u = this.getClass().getResource ("/backgrounds/FloatingImage.gif");
		java.awt.Image image = createBackgroundImage (u);
		if (image != null) {
			cont.setVisitor (new ImageCopier(image, cont, cont.getGraphics()));
		} else {
			System.err.println ("Unable to create ImageCopier.");
		}
	} else if (skinChoice.equals (SkinCatalog.BOUNCING_BALLS)) {
		cont.setVisitor (new BouncingBalls (cont));
	} else if (skinChoice.equals (SkinCatalog.PSYCHADELIC)) {
		cont.setVisitor (new Psychedelic (cont, cont.getBackground()));
	} else if (skinChoice.equals (SkinCatalog.MULTIPLE_BOUNCING_BALLS)) {
		cont.setVisitor (new BouncingBalls (cont, true));
	}
}


/**
 * This method initializes ivjnewGame	
 * 	
 * @return java.awt.Button	
 */
private Button getnewGame() {
	if (ivjnewGame == null) {
		ivjnewGame = new Button();
		ivjnewGame.setBounds(new Rectangle(12, 12, 72, 23));
		ivjnewGame.setActionCommand("");
		ivjnewGame.setLabel("New Hand");
		ivjnewGame.setName("newGame");
		ivjnewGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Container cont = getGameContainer();
				cont.forceNextHand();
			}
		});
	}
	return ivjnewGame;
}

}
