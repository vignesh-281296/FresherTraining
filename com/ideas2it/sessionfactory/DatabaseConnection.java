package com.ideas2it.sessionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class provide database connectivity
 * 
 * @author vignesh r
 * @version 1.0 14-03-2021
 */
public class DatabaseConnection {
      private static DatabaseConnection databaseConnection = null;
    
      /**
       * It is used to  establish connection to SQL database
       */
      public Connection getConnection() throws ClassNotFoundException {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager
                  .getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");  
          return connection;
      }
      
      /**
       * It will used to create Instance 
       */
      public static DatabaseConnection getInstance() {
          if (null == databaseConnection) {
            return databaseConnection = new DatabaseConnection(); 
          }
          return databaseConnection;
      }
}