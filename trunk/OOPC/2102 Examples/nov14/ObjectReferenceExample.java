package nov14;

public class ObjectReferenceExample {
	public static void main (String[] args) {
		Item i = new Item ("Book", 2.0);
		Item j = i;
		
		// note that now, variable i and variable j point to the exact same
		// object. There is only one object that has been instantiated.
		i.weight = 10.0;
		
		System.out.println ("Note that j now also has a weight of 10:" + j.weight);
		
		j = new Item ("Shelf", 200);
		
		// i still refers to the original object. 
		System.out.println(i.weight + " is still 10.0");
		
		
	}
}
