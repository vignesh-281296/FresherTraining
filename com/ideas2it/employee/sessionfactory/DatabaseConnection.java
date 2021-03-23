package com.ideas2it.employee.sessionfactory;

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

      private DatabaseConnection() {
          
      }
    
      /**
       * It is used to  establish connection to SQL database
       */
      public Connection getConnection() {
          //Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = null;
          try {
              connection = DriverManager
                      .getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");   
          } catch(SQLException e) {
              System.out.println("not connect");
          }  
          return connection;
      }
      
      /**
       * It will used to create Instance 
       */
      public static DatabaseConnection getInstance() {
         return (null == databaseConnection) ? (databaseConnection = new DatabaseConnection()) : databaseConnection;
      }
}