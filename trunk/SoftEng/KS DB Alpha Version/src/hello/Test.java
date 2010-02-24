package hello;

import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IGame gmae = new IGame("winner", "gameID", "luginName", new Date(), 0);
		IGM_DB out = new IGM_DB();
		boolean type;
		
		try{
		type = out.add(gmae);
		}catch (Exception e){
			System.out.println("we win!");
		}
		System.out.println(type);
	}

}
