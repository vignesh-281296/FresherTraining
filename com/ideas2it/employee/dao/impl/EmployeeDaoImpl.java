package com.ideas2it.employee.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.sessionfactory.DatabaseConnection;

/**
 * This class is used to store and retrives datas from mysql
 *
 * @author vignesh r 
 */  
public class EmployeeDaoImpl implements EmployeeDao {
   
    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        int addressResult = 0;
        List<Address> employeeAddressDetails = new ArrayList<Address>();
        Address employeeAddress;  
        PreparedStatement prepareStatement = connection.prepareStatement("insert into employee (name, desgination, email," 
                                                                                 +"phone_number, salary, dob) values (?, ?, ?, ?, ?, ?)");
        prepareStatement.setString(1, employee.getName());
        prepareStatement.setString(2, employee.getDesgination());
        prepareStatement.setString(3, employee.getEmail());
        prepareStatement.setLong(4, employee.getPhoneNumber());
        prepareStatement.setLong(5, employee.getSalary());
        prepareStatement.setDate(6, employee.getDob());
        int employeeResult = prepareStatement.executeUpdate();
        if (0 != employeeResult) {
            prepareStatement = connection.prepareStatement("select max(id) from employee");
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            int lastInsertEmployeeId = resultSet.getInt(1);
            employeeAddressDetails = employee.getAddress();
            for (int i = 0; i < employeeAddressDetails.size(); i++) {
                employeeAddress = employeeAddressDetails.get(i);
                prepareStatement = connection.prepareStatement("insert into address (employee_id, door_no, street_name," 
                                                                                     + "city, district, state, country, address_mode) values (?, ?, ?, ?, ?, ?, ?, ?)");
                prepareStatement.setInt(1, lastInsertEmployeeId);
                prepareStatement.setString(2, employeeAddress.getDoorNo());
                prepareStatement.setString(3, employeeAddress.getStreetName());
                prepareStatement.setString(4, employeeAddress.getCity());
                prepareStatement.setString(5, employeeAddress.getDistrict());
                prepareStatement.setString(6, employeeAddress.getState());
                prepareStatement.setString(7, employeeAddress.getCountry());
                prepareStatement.setString(8, employeeAddress.getAddressMode());
                addressResult = prepareStatement.executeUpdate();
            } 
        }
        return 0 != addressResult;             
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select id from employee Where id = ?");
        prepareStatement.setInt(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        return resultSet.next();            
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("Delete from employee Where id = ?");
        prepareStatement.setInt(1, id);
        if (1 == prepareStatement.executeUpdate()) {
            prepareStatement = connection.prepareStatement("Delete from address Where employee_id = ?");
            prepareStatement.setInt(1, id);
            return 1 == prepareStatement.executeUpdate();
        }    
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override   
    public Employee getSpecificEmployee(int id) throws SQLException, ClassNotFoundException {
         Connection connection = DatabaseConnection.getInstance().getConnection();
         List<Address> addressDetails = new ArrayList<Address>();
         PreparedStatement prepareStatement = connection.prepareStatement("select * from address where employee_id = ?");
         prepareStatement.setInt(1, id);
         ResultSet addressResultSet = prepareStatement.executeQuery();
         while (addressResultSet.next()) {
             Address address = new Address(addressResultSet.getInt(2),
                                           addressResultSet.getString(3),
                                           addressResultSet.getString(4),
                                           addressResultSet.getString(5),
                                           addressResultSet.getString(6),
                                           addressResultSet.getString(7),
                                           addressResultSet.getString(8),
                                           addressResultSet.getString(9));
                                           addressDetails.add(address);
         }
         prepareStatement = connection.prepareStatement("select * from employee where id = ?");
         prepareStatement.setInt(1, id);
         ResultSet resultSet = prepareStatement.executeQuery();
         resultSet.next();
         Employee employee = new Employee(resultSet.getInt(1),
                                          resultSet.getString(2),
                                          resultSet.getString(3),
                                          resultSet.getString(4),
                                          resultSet.getLong(5),
                                          resultSet.getLong(6),
                                          resultSet.getDate(7),
                                          addressDetails);
         return employee; 	        
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        List<Address> addressDetails = new ArrayList<Address>();
        List<Employee> employees = new ArrayList<Employee>();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from address");
        ResultSet addressResultSet = prepareStatement.executeQuery();
        while (addressResultSet.next()) {
            Address address = new Address(addressResultSet.getInt(2),
                                          addressResultSet.getString(3),
                                          addressResultSet.getString(4),
                                          addressResultSet.getString(5),
                                          addressResultSet.getString(6),
                                          addressResultSet.getString(7),
                                          addressResultSet.getString(8),
                                          addressResultSet.getString(9));
            addressDetails.add(address);
        }
        prepareStatement = connection.prepareStatement("select * from employee");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt(1),
                                             resultSet.getString(2),
                                             resultSet.getString(3),
                                             resultSet.getString(4),
                                             resultSet.getLong(5),
                                             resultSet.getLong(6),
                                             resultSet.getDate(7),
                                             addressDetails);
        employees.add(employee);
        } 	     
        return employees; 
    }
}