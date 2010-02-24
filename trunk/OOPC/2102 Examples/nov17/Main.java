package nov17;

public class Main {

	/**
	 * Sample program showing interesting issues with late binding.
	 */
	public static void main(String[] args) {
		
		// invoke toString() method on Item
		Item it = new Item ("Book", 1.0);
		System.out.println (it);
		
		// invoke toString() method on BusinessItem
		BusinessItem bi = new BusinessItem ("Book", 2.0, 100.00);
		System.out.println (bi);

		// Now come the tricky part. note that BusinessItem.toString() is used.
		// why? because the method to invoke is based on the object.
		Item j = bi;
		System.out.println ("Answer in the form of a BusinessItem");
		System.out.println (j);  
		
		// now try static.
		System.out.println ("Things are different with static methods.");
		it.outputType();   // static method. Taken from the Item class
		bi.outputType();   // static method. Taken from the BusinessItem class
		
	    // Wow. This invokes the static method on the type of the variable j, which
		// we can see above is of type Item. Thus when you invoke a static method 
		// and give an object as the target, Java will seek for the static method
		// that would have been invoked if the type of the variable had been used instead.
		j.outputType();    // static method. Taken from the Item class
		
		// Here is an example where I first 'downcast' j (which is defined as being
		// of type Item, but I know the object it refers to is actually of the derived
		// type BusinessItem. In this case, the static outputType method actually to
		// be invoked is the one from BusinessItem.
		((BusinessItem)j).outputType();
		
	}

}
