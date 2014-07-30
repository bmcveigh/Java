import java.util.*;
import java.sql.*;

public class GuestDB {
    private Connection getConnection() {
        Connection connection = null;
        try {
     	   DriverManager.getConnection("jdbc:mysql://localhost/reception?" +
                    "user='root'&");
 		   return connection;
         }
         catch(Exception e) {
             System.err.println(e);
             e.printStackTrace();
 			return connection;
         }
    }
	//get all guest
    public ArrayList<Guest> getguests() {
        String sql1 = "SELECT G_ID, G_FIRST,G_LAST,G_ADDRESS,G_CITY,G_STATE,G_ZIP FROM GUEST ORDER BY G_ID";        
        ArrayList<Guest> guests = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sql1);
             ResultSet rs1 = ps1.executeQuery()) {
        	while(rs1.next()) {
                int id = rs1.getInt("G_ID");
                String first = rs1.getString("G_FIRST");
					 String second = rs1.getString("G_LAST");
					 String address = rs1.getString("G_ADDRESS");
					 String city = rs1.getString("G_CITY");
					 String state = rs1.getString("G_STATE");
					 String zip = rs1.getString("G_ZIP");
      
               Guest guest = new Guest(id, first, second, address, city, state, zip);
               
                guests.add(guest);
            }
            connection.close();
            return guests;
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
	 //check if guest exist
	 public boolean checkguest(Guest guest) {
        String sql1 = "SELECT G_ID, G_FIRST,G_LAST,G_ADDRESS,G_CITY,G_STATE,G_ZIP FROM GUEST WHERE G_FIRST = '"+guest.getfirst()+"' AND G_LAST = '"+guest.getlast()+"'";         
        boolean found = false;
		  try (Connection connection = getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sql1);
             ResultSet rs1 = ps1.executeQuery()) {
				while(rs1.next()) { 
           	found = true;
				}
				connection.close();
				return found;
        	}
        catch(SQLException e) {
            System.err.println(e);
            return found;
        }
    }
	 	//get guest by ID
	  public Guest getguest(int guestid) {
        String sql1 = "SELECT G_ID, G_FIRST,G_LAST,G_ADDRESS,G_CITY,G_STATE,G_ZIP FROM GUEST WHERE G_ID = "+guestid;        
        Guest guest = new Guest();
        
        try (Connection connection = getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sql1);
             ResultSet rs1 = ps1.executeQuery()) {
            if (rs1.next()) { 
				 int id = rs1.getInt("G_ID");
             String first = rs1.getString("G_FIRST");
				 String second = rs1.getString("G_LAST");
				 String address = rs1.getString("G_ADDRESS");
				 String city = rs1.getString("G_CITY");
				 String state = rs1.getString("G_STATE");
				 String zip = rs1.getString("G_ZIP");
      
             guest = new Guest(id, first, second, address, city, state, zip);

            connection.close();
            return guest;
				}
				else{
					rs1.close();
                connection.close();
                return null;
					}
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
	 //Gets guest by name
	 public Guest getguestbyname(Guest currentguest) {
        String sql1 = "SELECT G_ID, G_FIRST,G_LAST,G_ADDRESS,G_CITY,G_STATE,G_ZIP FROM GUEST "
		  					+"WHERE G_FIRST = '"+currentguest.getfirst()+"'"
							+" AND G_LAST = '"+currentguest.getlast()+"'";        
        Guest guest = new Guest();
        
        try (Connection connection = getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sql1);
             ResultSet rs1 = ps1.executeQuery()) {
            if (rs1.next()) { 
				 int id = rs1.getInt("G_ID");
             String first = rs1.getString("G_FIRST");
				 String second = rs1.getString("G_LAST");
				 String address = rs1.getString("G_ADDRESS");
				 String city = rs1.getString("G_CITY");
				 String state = rs1.getString("G_STATE");
				 String zip = rs1.getString("G_ZIP");
      
             guest = new Guest(id, first, second, address, city, state, zip);

            connection.close();
            return guest;
				}
				else{
					rs1.close();
                connection.close();
                return null;
					}
        }
        catch(SQLException e) {
            System.err.println(e);
            return null;
        }
    }
	 //gets highest guest ID
	 public int getcurrentid() {
        String sql1 = "SELECT MAX(G_ID) FROM GUEST";        
        int current = 0;
        
        try (Connection connection = getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sql1);
             ResultSet rs1 = ps1.executeQuery()) {
            if (rs1.next()) { 
				 current = rs1.getInt("MAX(G_ID)");

            connection.close();
            return current+1;
				}
				else{
					rs1.close();
                connection.close();
                return 0;
					}
        }
        catch(SQLException e) {
            System.err.println(e);
            return 0;
        }
    }
	 

    //adds new guest
    public boolean addguest(Guest g) {
    	
        String sql ="INSERT INTO GUEST (G_ID, G_FIRST,G_LAST,G_ADDRESS,G_CITY,G_STATE,G_ZIP) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {	
    			ps.setInt(1,g.getid());        
				ps.setString(2, g.getfirst());
            ps.setString(3, g.getlast());
            ps.setString(4, g.getaddress());
				ps.setString(5, g.getcity());
            ps.setString(6, g.getstate());
            ps.setString(7, g.getzip());
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
   
    // delete guest
    public boolean deleteguest(Guest g) {
        String sql = "DELETE FROM Customers " +
                     "WHERE G_ID = " + g.getid();        
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            connection.close();
            return true;
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }

}