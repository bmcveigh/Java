import java.sql.*;
public class AdminDB{
	 private Connection getConnection() {
        Connection connection = null;
        try {
     	   DriverManager.getConnection("jdbc:mysql://localhost/reception?" +
                    "user='root'");
 		   return connection;
         }
         catch(Exception e) {
             System.err.println(e);
             e.printStackTrace();
 			 return connection;
         }
    }
	 public boolean checkadmin(String user, String pw) {
        //String sql = "SELECT A_NAME FROM ADMIN WHERE A_NAME = '"+user+"' AND A_PASS = '"+pw+"'";
		String sql = "SELECT * FROM ADMIN";
        boolean found = false;
		  try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
				while(rs.next()) { 
					found = true;
					break;
				}
				connection.close();
				return found;
        	}
        catch(SQLException e) {
            System.err.println(e);
            return found;
        }
    }

}