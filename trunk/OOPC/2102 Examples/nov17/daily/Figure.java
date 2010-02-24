package nov17.daily;

/**
 * Figure is a special class because its constructor has been marked
 * as being protected.
 * 
 * Thus you can't arbitrarily go and construct Figure instances.
 * 
 * @author heineman
 *
 */
public class Figure {

	/** Make available to subclasses ONLY this constructor. */
	protected Figure () {
		
	}
	
	/** Area of a figure. */
	protected double area;
	
	/** 
	 * Return the area of the figure. 
	 *
	 * @return    area of the figure.
	 */
	public double getArea () {
		return area;
	}
	
	/** 
	 * Set the area for the figure.
	 * 
	 * Note that only Friends can access this method.
	 * 
	 * @param newArea   newly calculated area.
	 */
	protected void setArea(double newArea) {
		area = newArea;
	}
	
	/**
	 * Compare two figures by area
	 * 
	 * @param f    Figure to be compared against
	 * @return     true if the figures have the same area.
	 */
	public boolean sameArea (Figure f) {
		return getArea() == f.getArea();
	}

}
