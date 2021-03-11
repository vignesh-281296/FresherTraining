package com.ideas2it.employee.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

/**
 * This class is used to store and retrives datas from mysql
 *
 * @author vignesh r 
 */  
public class EmployeeDaoImpl implements EmployeeDao {
   
    /**
     * {inheritDoc}
     */
    public boolean insertEmployee(Employee employee) {

        try  {
	    Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
	    System.err.print("ClassNotFoundException: ");
	    System.err.println(e.getMessage());
          }

         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "insert into employee (id, name, desgination, email, phone_number, salary, dob)" 
                            + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, employee.getEmpID());
            prepareStatement.setString(2, employee.getName());
            prepareStatement.setString(3, employee.getDesgination());
            prepareStatement.setString(4, employee.getEmail());
            prepareStatement.setLong(5, employee.getPhoneNumber());
            prepareStatement.setLong(6, employee.getSalary());
            prepareStatement.setDate(7, employee.getDob());
            int result = prepareStatement.executeUpdate();
            return 0 != result;     
         } catch (SQLException e) {
             e.printStackTrace();
           }
         return false;             
    }

    /**
     * {inheritDoc}
     */ 
    public boolean insertAddress(Address address) {

        try  {
	    Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
	    System.err.print("ClassNotFoundException: ");
	    System.err.println(e.getMessage());
          }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "insert into address (employee_id, door_no, street_name, city, district, state," 
                            + " country, address_mode) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, address.getEmpID());
            prepareStatement.setString(2, address.getDoorNo());
            prepareStatement.setString(3, address.getStreetName());
            prepareStatement.setString(4, address.getCity());
            prepareStatement.setString(5, address.getDistrict());
            prepareStatement.setString(6, address.getState());
            prepareStatement.setString(7, address.getCountry());
            prepareStatement.setString(8, address.getAddressMode());
            int result = prepareStatement.executeUpdate();
            return 0 != result;      
         } catch (SQLException e) {
             e.printStackTrace();
             return false;
           }
    }
    
    /**
     * {inheritDoc}
     */
    public List<Employee> getAllEmployee() {
        List<Employee> employeeDetails = new ArrayList<Employee>();
        try  {
	    Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
	    System.err.print("ClassNotFoundException: ");
	    System.err.println(e.getMessage());
          }

         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "select * from employee";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt(1),
                                                 resultSet.getString(2),
                                                 resultSet.getString(3),
                                                 resultSet.getString(4),
                                                 resultSet.getLong(5),
                                                 resultSet.getLong(6),
                                                 resultSet.getDate(7));
                                                 employeeDetails.add(employee);
            } 	     
         } catch (SQLException e) {
             e.printStackTrace();
           }
          return employeeDetails; 
    }

    /**
     * {inheritDoc}
     */   
    public List<Address> getAllEmployeeAddress() {
        List<Address> employeeAddressDetails = new ArrayList<Address>();
        try  {
	    Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
	    System.err.print("ClassNotFoundException: ");
	    System.err.println(e.getMessage());
          }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "select * from address";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address(resultSet.getInt(2),
                                              resultSet.getString(3),
                                              resultSet.getString(4),
                                              resultSet.getString(5),
                                              resultSet.getString(6),
                                              resultSet.getString(7),
                                              resultSet.getString(8),
                                              resultSet.getString(9));
                                              employeeAddressDetails.add(address);
            } 	     
         } catch (SQLException e) {
             e.printStackTrace();
           }
         return employeeAddressDetails; 
    }

   /**
     * {inheritDoc}
     */
    public boolean isEmpIdExist(int id) {

         try  {
	     Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (java.lang.ClassNotFoundException e) {
	     System.err.print("ClassNotFoundException: ");
	     System.err.println(e.getMessage());
           }

         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "select id from employee Where id = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            return resultSet.next();     
         } catch (SQLException e) {
             e.printStackTrace();
           }
         return false;        
    }

     /**
     * {inheritDoc}
     */
    public boolean deleteEmployee(int id) {

    try  {
	     Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (java.lang.ClassNotFoundException e) {
	     System.err.print("ClassNotFoundException: ");
	     System.err.println(e.getMessage());
           }

         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String delete_emp_query = "Delete from employee Where id = ?";
            PreparedStatement prepareStatementEmp = connection.prepareStatement(delete_emp_query);
            prepareStatementEmp.setInt(1, id);
            if (1 == prepareStatementEmp.executeUpdate()) {
                String delete_emp_address_query = "Delete from address Where employee_id = ?";
                PreparedStatement prepareStatementAddr = connection.prepareStatement(delete_emp_address_query);
                prepareStatementAddr.setInt(1, id);
                return 1 == prepareStatementAddr.executeUpdate();
            }    
         } catch (SQLException e) {
             e.printStackTrace();
           }
         return false;
    }

    /**
     * {inheritDoc}
     */
    public boolean updateEmployee(int id, Employee employee) {

        try  {
	    Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
	    System.err.print("ClassNotFoundException: ");
	    System.err.println(e.getMessage());
          }

         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "update employee set name = ?, desgination = ?, email = ?, phone_number = ?"
                            + "salary = ?, dob = ? where id = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getDesgination());
            prepareStatement.setString(3, employee.getEmail());
            prepareStatement.setLong(4, employee.getPhoneNumber());
            prepareStatement.setLong(5, employee.getSalary());
            prepareStatement.setDate(6, employee.getDob());
            prepareStatement.setInt(7, id);
            int result = prepareStatement.executeUpdate();
            return 0 != result;     
         } catch (SQLException e) {
             e.printStackTrace();
           }
         return false;       
    }

    /**
     * {inheritDoc}
     */
    public Employee getEmployee(int id) {
    
        try  {
	     Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (java.lang.ClassNotFoundException e) {
	     System.err.print("ClassNotFoundException: ");
	     System.err.println(e.getMessage());
           }

         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "select * from employee where id = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            Employee employee = new Employee(resultSet.getInt(1),
                                            resultSet.getString(2),
                                            resultSet.getString(3),
                                            resultSet.getString(4),
                                            resultSet.getLong(5),
                                            resultSet.getLong(6),
                                            resultSet.getDate(7));
                return employee;                                    	     
         } catch (SQLException e) {
             e.printStackTrace();
             return null;
           }             
    }

    /**
     * {inheritDoc}
     */
    public Address getAddress(int id, String addressType) {
    
        try  {
	     Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (java.lang.ClassNotFoundException e) {
	     System.err.print("ClassNotFoundException: ");
	     System.err.println(e.getMessage());
           }
         
         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","1234");
            String query = "select * from address where employee_id = ? and address_mode = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, addressType);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            Address address = new Address(resultSet.getInt(1),
                                           resultSet.getString(2),
                                           resultSet.getString(3),
                                           resultSet.getString(4),
                                           resultSet.getString(5),
                                           resultSet.getString(6),
                                           resultSet.getString(7),
                                           resultSet.getString(8));
             return address;                                         	     
         } catch (SQLException e) {
             e.printStackTrace();
             return null;
           }                 
    }
}