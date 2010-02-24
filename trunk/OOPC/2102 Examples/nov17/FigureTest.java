package nov17;

import nov17.daily.Circle;
import nov17.daily.Figure;
import nov17.daily.Triangle;

public class FigureTest {

	/**
	 * Small program to show nuances for Figures.
	 */
	public static void main(String[] args) {
		Figure circle = new Circle(2);
		Figure triangle = new Triangle (3, 4, 5);
		
		if (triangle.sameArea(circle)) {
			System.out.println ("Figures have same area");
		}

	}

}
