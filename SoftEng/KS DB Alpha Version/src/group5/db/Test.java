package group5.db;

import java.util.Date;

public class Test {
	public static void main(String[] args) throws Exception {
		String[] players = {"a", "b"};
		IGame gmae = new IGame("winner", "gaIDasfd", players, "luginName", new Date(), 0);
		IGM_DB out = new IGM_DB();
		boolean type = false;
		
		type = out.add(gmae);

		System.out.println(type);
	
	}
}
