package dec11;

public class TimingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		for (int size = 256; size <= 16384; size*= 2) {
			// create numberList of given size
			NumberList nl = new NumberList();
			
			for (int i = 0; i < size; i++) {
				int val = (int) (Math.random() * 1000);  // numbers from 0 ... 999
				
				nl.prepend(val);
			}
			
			System.gc();  // garbage collect to ensure clean run below
			
			// run failing test
			System.out.println ("Size:" + size);
			System.out.println ("iterative:" + size);
			nl.find(-9);
			System.out.println ("recursive:" + size);
			nl.findIterative(-9);
			System.out.println();
		}
		
 

	}

}
