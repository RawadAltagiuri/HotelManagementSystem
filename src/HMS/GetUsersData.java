package HMS;

import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GetUsersData{
    public static User FillFields(String username) {
        User user = null;
        
        // Connect to the database and execute the SQL query
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            // Extract data from the result set and create a User object
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("lastname");
                String occupation = rs.getString("occupation");
                user = new User(id, username, password, name, surname, occupation);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return user;
    }
    
    public static void deleteUser(String username) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE username = ?")) {
            stmt.setString(1, username);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmed");
                alert.setHeaderText(null);
                alert.setContentText("User deleted succefully");
                alert.showAndWait();
            } else {
                Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Confirmed");
                alert.setHeaderText(null);
                alert.setContentText("User not deleted");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
    public static String getOccupation(String username) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT occupation FROM user WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String occupation = rs.getString("occupation");
                return occupation;
            } else {
                System.out.println("User " + username + " not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }
    
    public static void UpdateUser(String username, String password, String name, String lastname, String occupation) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
             PreparedStatement stmt = conn.prepareStatement("UPDATE user SET password = ?, name = ?, lastname = ?, occupation = ? WHERE username = ?")) {
            stmt.setString(1, password);
            stmt.setString(2, name);
            stmt.setString(3, lastname);
            stmt.setString(4, occupation);
            stmt.setString(5, username);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Updated succefully");
                alert.showAndWait();
            } else {
                Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Error, did not update");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
    
    public static void AddUser(String username, String password, String name, String lastname, String occupation) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxId FROM user");
            int id = rs.next() ? rs.getInt("maxId") + 1 : 1;

            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO user (id, username, password, name, lastname, occupation) VALUES (?, ?, ?, ?, ?, ?)");
            insertStmt.setInt(1, id);
            insertStmt.setString(2, username);
            insertStmt.setString(3, password);
            insertStmt.setString(4, name);
            insertStmt.setString(5, lastname);
            insertStmt.setString(6, occupation);

            int rowsUpdated = insertStmt.executeUpdate();

            if (rowsUpdated > 0) {
                Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Added succefully");
                alert.showAndWait();
            } else {
                Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("User not added");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }


    
    
    public static class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String occupation;

    public User(int id, String username, String password, String name, String surname,String occupation) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.occupation = occupation;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public String getOccupation() {
        return occupation;
    }
}
}



