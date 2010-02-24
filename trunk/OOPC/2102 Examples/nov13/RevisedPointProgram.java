package nov13;

import java.util.Scanner;

public class RevisedPointProgram {
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
			double newDist = pn.value.distance(p);
			
			// NOW: insert to ensure list is in order of distance from the target point.
			// thus the CLOSEST object will be first.
			
			// If empty -> head is now the only element in the list
			// If not empty, then check to see if new value is going to be
			//    the closest; it is only in this case, that head is updated
			// Otherwise, determine 'node' which will 
			//    (a) either have its next value be null OR its next value
			//        refers to a Point object whose distance > newDist.
			//    (b) refer to a Point object whose distance < newDist
			//    
			if (head == null) {
				// set the list
				head = pn;
			} else if (newDist < p.distance(head.value)) {
				// prepend
				pn.next = head;
				head = pn;
			} else {
				// Note that justBefore will always refer to a node whose value is
				// smaller than newDist (because above if statement).
				PointNode node = head;
				PointNode justBefore = head;
				
				while (node != null) {
					
					// keep track of last point before which we want to insert.
					if (p.distance(node.value) < newDist) {
						justBefore = node;
					}
					
					node = node.next;   // ADVANCE
				}
				
				// when we get here, justBefore is pointing to a node that meets (a) and (b) above
				// LINK in
				pn.next = justBefore.next;
				justBefore.next = pn;
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
