package Test;

import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IGame gmae = new IGame("winner", "gasdsdmeIDasfd", "luginName", new Date(), 0);
		IGM_DB out = new IGM_DB();
		boolean type = false;
		
		type = out.add(gmae);

		System.out.println(type);
	}

}
