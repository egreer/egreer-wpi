package yostinso;

import java.awt.Image;
import java.awt.MediaTracker;

import ks.common.model.Card;


/**
 * Responsible for returning images for SuitPileViews.
 * <p>
 * Preloads images using Media tracker.
 * Creation date: (12/3/2001 11:59:34 AM)
 * @author: 
 */
public class SuitImages implements Runnable {
	protected static SuitImages _suitImages = null;
	protected static final String suitImageDirectory = "yostinso/";

	protected static java.util.Hashtable<String,Image> imageCache = new java.util.Hashtable<String,Image>();

	public java.lang.Thread thread = null;
	public java.awt.Component viewPeer = null;
	public static boolean ready = false;

	/** Semaphore in-case multiple accesses. */
	Object semaphore = new Object();
	
	/**
	 * SuitImages constructor comment.
	 */
	public SuitImages(java.awt.Component viewPeer) {
		super();

		this.viewPeer = viewPeer;
	}
	
	/**
	 * The key is the resource name (such as "10H" or "Back").
	 * The initial thread will have already placed images into the cache.
	 * Creation date: (10/1/01 10:18:44 PM)
	 * @return java.awt.Image
	 * @param key java.lang.String
	 */
	protected synchronized Image cacheLookup(String key) {
		try {
			return (Image) imageCache.get(key);
		} catch (IllegalArgumentException e) {
			System.err.println("SuitImage's cacheLookup failed to find the image for " + key);
			return null;
		}
	}
	public static SuitImages getInstance(java.awt.Component viewPeer) {
		if (_suitImages == null) {
			_suitImages = new SuitImages(viewPeer);
		}

		/** If we have a new peer, use it */
		if (viewPeer != null)
			_suitImages.viewPeer = viewPeer;

		return _suitImages;
	}
	
	public java.awt.Image getSuitImage(int suit) {
		String rep = Card.getSuitName (suit);
		try {
			return cacheLookup(rep);
		} catch (IllegalArgumentException e) {
			System.err.println("getSuitImages failed to find the image.");
			return null;
		}
	}
	
	
	public java.awt.Image getSuitImage(SuitPile suitpile) {
		int suit = suitpile.getAssignedSuit();

		String rep = Card.getSuitName(suit);

		try {
			return cacheLookup(rep);
		} catch (IllegalArgumentException e) {
			System.err.println("getSuitImages failed to find the image.");
			return null;
		}
	}


	public static boolean isReady() {
		return ready;
	}
	
	/**
	 * Retrieve all tracker objects..
	 * Creation date: (10/2/01 5:17:01 PM)
	 */
	public void run() {

		MediaTracker tracker = new MediaTracker(viewPeer);
		int idx = 1;

		for (int counterval = Card.CLUBS; counterval <= Card.SPADES; counterval++) {
			// extract from resource file (prepend images directory)
			String key = Card.getSuitName(counterval);
			try {
				java.net.URL u = this.getClass().getResource("/" + suitImageDirectory + key + ".gif");
				Image img = null;
				if (u != null) {
					img = java.awt.Toolkit.getDefaultToolkit().getImage(u);
				}
				if (img != null) {
					tracker.addImage(img, idx++);
					cacheInsert (key, img);
				} else {
					System.err.println("SuitImages failed to find a resource file for suit: " + Card.getSuitName(counterval));
				}

			} catch (IllegalArgumentException e) {
				System.err.println("SuitImages failed to find a resource file for suit: " + Card.getSuitName(counterval));
			}
		}

		try {
			tracker.waitForAll();
		} catch (InterruptedException ie) {
			// we have been interrupted, but this is not overly problematic.
			// we will be slowed down on demand.
			System.err.println("SuitImage interrupted:" + ie.toString());
		}

		// Ready
		setReady(true);
	}
	
	/**
	 * Store image using key.
	 * 
	 * @param key
	 * @param img
	 */
	private synchronized void cacheInsert(String key, Image img) {
		imageCache.put(key, img);
	}

	public static void setReady(boolean newReady) {
		ready = newReady;
	}
	
	/**
	 * Launch the thread to execute run.
	 * Creation date: (10/2/01 5:17:12 PM)
	 */
	public void start() {
		thread = new Thread(this);
		thread.start();
	}

}
