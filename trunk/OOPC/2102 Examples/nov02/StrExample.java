package nov02;

public class StrExample {

	/**
	 * String manipulation example.
	 */
	public static void main(String[] args) {
		for (String s = "George"; s.length() > 0; s=s.substring(1,s.length())) {
			System.out.println (s);
		}
		

		int n = 1024;
		int log = 0;
		for (int i = 1; i < n; i = i * 2) {
		   log++;
		}
		System.out.println (n + " " + log);


	}

}
