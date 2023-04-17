package RandomName;
import java.sql.*;


public class SQL {
    public static boolean checkLogin(String userName, String password) {
        try {
            // Establish a connection to the MySQL database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "");

            // Prepare a statement to check if the username and password exist in the LoginInfo table
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LoginInfo WHERE username=? AND password=?");
            stmt.setString(1, userName);
            stmt.setString(2, password);

            // Execute the query and check if any rows were returned
            ResultSet rs = stmt.executeQuery();
            boolean loginSuccessful = rs.next();

            // Close the database resources
            rs.close();
            stmt.close();
            conn.close();

            return loginSuccessful;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
         }
    }
    
    
    
}
