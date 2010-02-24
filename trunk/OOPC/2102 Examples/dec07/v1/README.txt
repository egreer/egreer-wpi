You won't be required to know bi-directional associations for the exam.
As you can see, they offer an interesting complexity!

Try to follow the logic contained herein. The basic point is that once 
you decided there is a one-to-one bidirectional relationship between a 
parent and a child, then any attempt to change that relationship must 
result in a set of objects that do not violate the parent/child integrity.

Thus, it is fine for a parent object or a child object to be detached 
and have no child, or parent. However, once a parent has a child, then 
that child must be exlusively have that parent as its parent.

In other words, once p and c are related, then:

  p.getChild().getParent() == p
  c.getParent().getChild() == c
  
Where strict equality is the demand.
