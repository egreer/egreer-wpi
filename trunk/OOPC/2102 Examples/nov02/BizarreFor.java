package nov02;

public class BizarreFor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (boolean b = false;!b; b ^= !b) {
			System.out.println (b);
		}

	}

}
