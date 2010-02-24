package nov16.p2;

import nov16.p1.Sample;

// note we are in a different package.

public class DerivedSample extends Sample {

   private void doSomething() {
      System.out.println(a);
      System.out.println(b);    // protected, therefore accessible to derived classes
      System.out.println(c);	// private, therefore not visible to anyone
      System.out.println(d);    // package private, therefore not visible to us
      
      System.out.println(e);
      System.out.println(f);   // protected, therefore accessible to derived classes
      System.out.println(g);   // private, therefore not visible to anyone
      System.out.println(h);   // package private, therefore not visible to us
   }

}
