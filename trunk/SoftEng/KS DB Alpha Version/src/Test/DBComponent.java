package Test;

import org.compunit.Provide;
import org.compunit.interfaces.IComponent;
import org.compunit.interfaces.IResourceRetriever;
import java.sql.*;

//@Provide({IGame.class, IGM_DB.class, IPlugin.class, IPM_DB.class, IStat.class, IUM_DB.class , IUser.class})
public class DBComponent implements IComponent {

	public boolean activate(IResourceRetriever handler) throws Exception {
		System.out.println("This is not implemeted yet.");
		return false;
	}

	public boolean connect(IComponent unit, String interfaceName)
			throws Exception {
		System.out.println("This is not implemeted yet.");
		return false;
	}

	public void deactivate() throws Exception {
		System.out.println("This is not implemeted yet.");

	}
	
	
	protected static Connection DBConnect(){
		Connection connection = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://mysql.wpi.edu/thekombatsolitaire","svw","zYkPLH");
       
		} catch (Exception e) {
			System.out.println("OMFG YOU SCREWED UP");
			return null;
		}	
		
		return connection;
	}
	
	

}
