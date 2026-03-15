import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {
  public static final String user = "root";
  public static final String password = "Nick007#";
  public static final String dpath = "com.mysql.cj.jdbc.Driver";
  public static final String url = "jdbc:mysql://localhost:3306/employeesdtabase";
  
  public static Connection getConnection() {
	  try {
		  Class.forName(dpath);
		 return  DriverManager.getConnection(url, user, password);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
	}
  }
  
}
