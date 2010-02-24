package dec01;

import junit.framework.TestCase;

public class SkeletonArrayListTest extends TestCase {
	public void testArrays() {
		SkeletonArrayList sal = new SkeletonArrayList();
		
		sal.add("test");
		assertEquals ("[test]", sal.toString());
		
		sal.add("trouble");
		assertEquals ("[test,trouble]", sal.toString());
		
		// prepend
		sal.add(0,"first");
		assertEquals ("[first,test,trouble]", sal.toString());
		
		// try end
		assertEquals (3, sal.size());
		sal.add(sal.size(),"last");
		assertEquals ("[first,test,trouble,last]", sal.toString());
		
		// test contains
		assertTrue (sal.contains("first"));
		assertFalse (sal.contains("slkdj"));
		
		// try to grow
		sal.add("doc");
		sal.add("grumpy");
		sal.add("happy");
		sal.add("sneezy");
		sal.add("bashful");
		sal.add("sleepy");
		sal.add("dopey");
		assertEquals ("[first,test,trouble,last,doc,grumpy,happy,sneezy,bashful,sleepy,dopey]", sal.toString());
		assertEquals (11, sal.size());
				
		
		
	}
}
