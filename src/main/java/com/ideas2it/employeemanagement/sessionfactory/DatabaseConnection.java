package com.ideas2it.employeemanagement.sessionfactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * class provide database connectivity
 * 
 * @author vignesh r
 * @version 1.0 14-03-2021
 */
public class DatabaseConnection {
      private static SessionFactory sessionFactory = null;

      private DatabaseConnection() {    
      }
    
      /**
       * It is used to  establish connection to SQL database
       */
      public static SessionFactory getSessionFactory() {
          try {
              if (null == sessionFactory) {
                  Configuration configuration = new Configuration();
	          configuration.configure("resources/hibernate/properties/hibernate.cfg.xml");
	          sessionFactory = configuration.buildSessionFactory();
              }
          } catch(Exception e) {
               e.printStackTrace();
          }
          return sessionFactory;
      }
      
      public static void sessionClose(Session session) {
    	  if (null != session) {
				session.close();
			}
      }
}