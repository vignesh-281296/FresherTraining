package com.ideas2it.sessionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is singleton class 
 * 
 * @author vignesh r
 */
public class DatabaseConnection {
      private static DatabaseConnection databaseConnection = null;
    
      /**
       * Method to establich connection to SQL database
       */
      public Connection getConnection() throws SQLException, ClassNotFoundException {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection connection = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");  
              return connection;
      }
      
      /**
       * Method to create Instance 
       */
      public static DatabaseConnection getInstance() {
          if (null == databaseConnection) {
            return databaseConnection = new DatabaseConnection(); 
          }
          return databaseConnection;
      }
}