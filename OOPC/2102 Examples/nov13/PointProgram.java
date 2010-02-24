package nov13;

import java.util.Scanner;

public class PointProgram {
    /** Maintain our list of points. */
	static PointNode head;
	
	/**
	 * Read in set of points.
	 */
	public static void main(String[] args) {
		// INPUT
		Scanner sc = new Scanner (System.in);
		System.out.println ("What is the target point?");
		Point p = Point.scanPoint(sc);
		
		System.out.println ("Add a set of points, and I will output them in appropriate distance from " + p);
		
		// build up list.
		while (sc.hasNext()) {
			Point p2 = Point.scanPoint(sc);
			
			PointNode pn = new PointNode(p2);
			if (head == null) {
				head = pn;
			} else {
				pn.next = head;
				head = pn;
			}
			
		}
		
		// OUTPUT
		PointNode node = head;
		while (node != null) {
			double dist = node.value.distance(p);
			System.out.println ("Distance from " + node.value + " to " + p + " is " + dist);
			
			node = node.next;   // ADVANCE
		}
	}

}
