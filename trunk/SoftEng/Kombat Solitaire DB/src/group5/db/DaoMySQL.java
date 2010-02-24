package group5.db;

import java.util.Date;


public class DaoMySQL {
	//private Connection connect = null;
	//private Statement statement = null;
	//private ResultSet resultSet = null;
	/*
	public DaoMySQL() throws Exception {
		try {

		     Connection connection = null;
             Class.forName("com.mysql.jdbc.Driver").newInstance();
             connection = DriverManager.getConnection("jdbc:mysql://mysql.wpi.edu/thekombatsolitaire","svw","zYkPLH");
			
		 
            connect = connection;
			PreparedStatement statement = connect
					.prepareStatement("SELECT * FROM plugins");
			//SELECT * FROM games

			//statement.executeUpdate();
			
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String user = resultSet.getString("pluginname");
				int website = resultSet.getInt("suspended");
				System.out.println("Date: " + user);
				System.out.println("Comment: " + website);
			
			}
			
		} catch (Exception e) {
			System.out.println("OMFG YOU SCREWED UP");
			throw e;
		} finally {
			close();
		}

	}


	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
	*/
	public static void main(String[] args) throws Exception {
		String[] players = {"a", "b"};
		IGame gmae = new IGame("winner", "gaIDasfd", players, "luginName", new Date(), 0);
		IGM_DB out = new IGM_DB();
		boolean type = false;
		
		type = out.add(gmae);

		System.out.println(type);
		
		//DaoMySQL dao = new DaoMySQL();
		//IGame gmae = new IGame("winner", "gameID", "luginName", new Date(), 0);
		//IGM_DB out = new IGM_DB();
		//boolean type = out.add(gmae);
		//System.out.println(type);
	}

}
