// DRIVERS MIGHT CHANGE AS PER TH DEVICES
import java.sql.*;

public class GetCon {
	
	public Connection getConn() {
		
		Connection con = null;
		final String url = "jdbc:mysql://localhost:3306/";
		final String dbname = "tourismdb";
		final String driver = "com.mysql.jdbc.Driver";
		final String username = "root";
		final String password = "";
		
		
		
		try {
			
			con = DriverManager.getConnection(url+dbname, username, password);
			System.out.println("Connected to the DB !!!!!");
//			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			
			return con;
			
			
			
		}	// 												--------------------------    TRY    ------------------------- 
		catch(Exception e) {e.printStackTrace();}
		
		
		return null;
	}
	
}
