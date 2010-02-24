package lab5;

import java.util.*;

/**
 * Perform simple reverse polish notation computation.
 *     http://en.wikipedia.org/wiki/Reverse_Polish_notation
 * 
 * Once your LabStack is complete, you can complete the implementation of this class.
 * 
 * @author heineman
 */
public class Calculator {

	/**
	 * Run simple program to perform reverse polish notation computation.
	 */
	public static void main(String[] args) {
		LabStack s = new LabStack();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter in expressions in Reverse Polish Notation (i.e., 1 2 + 3 4 + +");
		System.out.println("Press Control-Z when done.");
		while (sc.hasNext()) {
			try {
				System.out.println("Value on top of stack is:" + s.top());
			} catch (EmptyStackException ese) {
				System.out.println("Stack is empty.");
			}
			if (sc.hasNextDouble()) {
				double d = sc.nextDouble();
				s.push(d);
			} else {
				String op = sc.next();

				if (op.equals("+")) {
					double d1 = (Double) s.pop();
					double d2 = (Double) s.pop();
					s.push(d1 + d2);
				} else if  (op.equals("*")) {
					double d1 = (Double) s.pop();
					double d2 = (Double) s.pop();
					s.push(d1 * d2);
				} else if (op.equals ("-")) {
					double d1 = (Double) s.pop();
					double d2 = (Double) s.pop();
					s.push(d1 - d2);
				}
				
				// See if you can modify to include '*', '-', and '/'
			}
		}

		System.out.println("Value on top of stack is:" + s.pop());

	}

}
