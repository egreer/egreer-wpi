package ks.framework.interfaces;

/**
 * Client-side interface to enable component access to
 * local resource accessible via URL.
 * 
 * @author George Heineman
 */
public interface IIconImage {
	/** Return URL for resource for Icon image. */
	java.net.URL getIconURL (String iconName);
}
