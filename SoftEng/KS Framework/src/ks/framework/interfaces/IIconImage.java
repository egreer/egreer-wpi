package ks.framework.interfaces;

/**
 * Client-side interface to enable component access to
 * local resource accessible via URL.
 * 
 * Icons are intended to be 32x32 in size.
 * 
 * @author George Heineman
 */
public interface IIconImage {
	/** Return URL for resource for Icon image. */
	java.net.URL getIconURL (String iconName);
	
}
