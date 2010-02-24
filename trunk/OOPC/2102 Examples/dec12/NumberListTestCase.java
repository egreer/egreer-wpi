package dec12;

import junit.framework.TestCase;

public class NumberListTestCase extends TestCase {
	public void testHorribleDeleteMethod() {
		NumberList list = new NumberList();
		assertEquals ("[]", list.toString());
		
		list.append(3);
		assertEquals ("[3]", list.toString());
		
		list.append(2);
		assertEquals ("[3,2]", list.toString());
		
		list.append(1);
		assertEquals ("[3,2,1]", list.toString());
		
		NumberNode n = list.find(2);
		assertEquals (2, n.value);
		
		// note that we pass the test! But oh, what damage we may wreak!
		n.deleteFrom(list);
		assertEquals("[3,1]", list.toString());
		
		n = list.find(1);
		assertEquals (1, n.value);
		n.deleteFrom(list);
		assertEquals("[3]", list.toString());
		
		// here the problem manifests itself. This FAILS! And it has nothing to do with the append
		// method. Rather it has to do with the fact that we didn't properly maintain head/tail values.
		list.append(8);
		assertEquals("[3,8]", list.toString());
		
		
		
	}
	
	public void testAppendAndPrepend () {
		NumberList list = new NumberList();
		assertEquals ("[]", list.toString());
		
		list.append(3);
		assertEquals ("[3]", list.toString());
		
		list.append(2);
		assertEquals ("[3,2]", list.toString());
		
		list.append(1);
		assertEquals ("[3,2,1]", list.toString());
		
		list.prepend(7);
		assertEquals ("[7,3,2,1]", list.toString());
		
		list.prepend(6);
		assertEquals ("[6,7,3,2,1]", list.toString());
		
		
	}
}
