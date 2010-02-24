package November;

public class NumberList {
	  NumberNode head;  /* first one. */


public void doSomething() {
		   NumberNode node = new NumberNode(3);
		   NumberNode other = new NumberNode(5);
		   head = node;
		   other.next = node;
		   head.next = new NumberNode(7);
		   node.next = other;
		   other.next = null;
		  
		}


}


