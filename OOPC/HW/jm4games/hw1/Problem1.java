package jm4games.hw1;

import java.awt.Dimension;

	/**
	 * @author Josh Montgomery,Eric Greer
	 * sn: jm4games,egreer
	 *
	 */
public class Problem1 {
		/**
		 * method main
		 * @param args
		 */
		public static void main(String[] args) {
			
			//INPUT -> PROCESSING
			double a = cylinder(6,3);
			double b = sphere(5);
			double c = pyramid(4,new Dimension(4,3));
			
			//OUTPUT
			if(a > b && a > c)
				System.out.println("The cylinder has the largest volume of "+a);
			else if(b > a && b > c)
				System.out.println("The sphere has the largest volume of "+b);
			else
				System.out.println("The pyramid has the largest volume of "+c);

		}		
		
		//method cylinder returns the volume of a cylinder
		//@param radius height
		private static double cylinder(double r, double h)
		{
			return (Math.PI*Math.pow(r, 2)*h);
		}
		//method sphere returns the volume of a sphere
		//@param radius
		private static double sphere(double r)
		{
			return ((4/3)*Math.PI*Math.pow(r, 3));
		}
		//method pyramid returns the volume of a pyramid
		//@param height (demension of base)
		private static double pyramid(double h, Dimension d)
		{
			double baseArea = d.getHeight()*d.getWidth();
			return ((1/3)*baseArea*h);
		}

}


