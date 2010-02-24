A discussion on the nature of equals.

We want to make it possible to compare objects together

Thus, we came up with equals (T) methods:

  in Item:           equals (Item i)
  in BusinessItem:   equals (BusinessItem bi)
  
See the example from nov14 (EqualsExampleProgram)

First, as we move forward, we see that all classes have the opportunity
to override the equals(Object o) method, which we have not done; we have
merely overloaded it.  Thus we should be redefining the method as

  equals (Object o)

Now we can compare Items with Items, and BusinessItems with BusinessItems.
However, there are times when we want to return false when comparing 
a BusinessItem with an Item. 

More specifically, if an item and BusinessItem have the same name
and weight, then the following is true:

  it.equals (bi)
  
but this is not:

  bi.equals (it)
  
This doesn't seem right!

  