package December;

public class neemt {
	/**
	 * Neemt een geheel en output een Ster voor elke vijf of nul
	 */
	public static void nreemt (int n) {
	  if (n > 0) {
	     nreemt (n/10);

	     if (n %5 == 0) {
	        System.out.print ('*');
	     } else {
	        System.out.print (n % 10);
	     }
	  }
	}
	
	public static void main(String[] args){
		nreemt(11560);
		return;
	}
}
