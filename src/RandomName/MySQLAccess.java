/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RandomName;


import java.sql.*;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private ResultSet resultSet = null;

  public void readDataBase(String UserName, String Password) throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Setup the connection with the DB
      connect = DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement?user="+userName+"&password="+password);

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();

      // Result set get the result of the SQL query
      resultSet = statement.executeQuery("SELECT * FROM LoginInfo");

      // Iterate through the result set and print the data
      while (resultSet.next()) {
        String dbUserName = resultSet.getString("UserName");
        String dbPassword = resultSet.getString("Password");

        if (dbUserName.equals(UserName) && dbPassword.equals(Password)) {
          System.out.println("Login successful");
          return;
        }
      }
      System.out.println("Invalid username or password");

    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

  // Close the resultSet, statement and connect
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
}

