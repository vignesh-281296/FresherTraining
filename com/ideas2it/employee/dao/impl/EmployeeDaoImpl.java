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
 * It is used to store and retrives datas to database
 *
 * @author vignesh r
 * @created at 13-03-2021 
 */  
public class EmployeeDaoImpl implements EmployeeDao {
   
    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertEmployee(Employee employee) 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        int addressResult = 0;
        PreparedStatement prepareStatement = connection.prepareStatement
                ("insert into employee (name, desgination, email," 
                + "phone_number, salary, dob) values (?, ?, ?, ?, ?, ?)"); 
        prepareStatement.setString(1, employee.getName());
        prepareStatement.setString(2, employee.getDesgination());
        prepareStatement.setString(3, employee.getEmail());
        prepareStatement.setLong(4, employee.getPhoneNumber());
        prepareStatement.setLong(5, employee.getSalary());
        prepareStatement.setDate(6, employee.getDob());
        int employeeResult = prepareStatement.executeUpdate();
        if (0 != employeeResult) {
            prepareStatement = connection.prepareStatement
                    ("select max(id) from employee");
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            int employeeId = resultSet.getInt(1);
            for (Address address : employee.getAddress()) {
                prepareStatement = connection.prepareStatement
                        ("insert into address (employee_id, door_no, street_name," 
                        + "city, district, state, country, address_mode)" 
                        + "values (?, ?, ?, ?, ?, ?, ?, ?)");
                prepareStatement.setInt(1, employeeId);
                prepareStatement.setString(2, address.getDoorNo());
                prepareStatement.setString(3, address.getStreetName());
                prepareStatement.setString(4, address.getCity());
                prepareStatement.setString(5, address.getDistrict());
                prepareStatement.setString(6, address.getState());
                prepareStatement.setString(7, address.getCountry());
                prepareStatement.setString(8, address.getAddressMode());
                addressResult = prepareStatement.executeUpdate();
            } 
        }
        return 0 != addressResult;             
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isEmpIdExist(int id) 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("select id from employee Where id = ? and is_delete = '0'");
        prepareStatement.setInt(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        return resultSet.next();            
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteEmployee(int id) 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("update employee set is_delete = '1' where id = ?");
        prepareStatement.setInt(1, id);
        prepareStatement.executeUpdate();
        prepareStatement = connection.prepareStatement
                ("update address set is_delete = '1' where employee_id = ?");
        prepareStatement.setInt(1, id);
        int count = prepareStatement.executeUpdate();
        return 0 != count;     
    }

    /**
     * {inheritDoc}
     */
    @Override   
    public Employee getSpecificEmployee(int id) 
            throws SQLException, ClassNotFoundException {
         Connection connection = DatabaseConnection.getInstance().getConnection();
         PreparedStatement prepareStatement = connection.prepareStatement
                 ("select emp.id, emp.name, emp.desgination, emp.email, emp.phone_number, "
                 + "emp.salary, emp.dob, a.employee_id, a.door_no, a.street_name, a.city, "
                 + "a.district, a.state, a.country, a.address_mode from employee as emp "
                 + "Inner join address as a on emp.id = a.employee_id where emp.id = ? "
                 + "and emp.is_delete = '0'");
         prepareStatement.setInt(1, id);
         ResultSet resultSet = prepareStatement.executeQuery();
         if (resultSet.next()) {
             Employee employee = new Employee(resultSet.getInt(1),
                                          resultSet.getString(2),
                                          resultSet.getString(3),
                                          resultSet.getString(4),
                                          resultSet.getLong(5),
                                          resultSet.getLong(6),
                                          resultSet.getDate(7),
                                          null);
             int employeeId = resultSet.getInt(1);
             List<Address> addressDetails = new ArrayList<Address>();
             while (employeeId == resultSet.getInt(1)) {
                 Address address = new Address(resultSet.getInt(8),
                                           resultSet.getString(9),
                                           resultSet.getString(10),
                                           resultSet.getString(11),
                                           resultSet.getString(12),
                                           resultSet.getString(13),
                                           resultSet.getString(14),
                                           resultSet.getString(15));
               addressDetails.add(address);
               if (!resultSet.next()) {
                   break;
               }
             }
             employee.setAddress(addressDetails);
             return employee;
         } else {
             return null;
           } 	        
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployee() 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        List<Employee> employees = new ArrayList<Employee>();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("select emp.id, emp.name, emp.desgination, emp.email," 
                 + "emp.phone_number, emp.salary, emp.dob,"
                 + "a.employee_id, a.door_no, a.street_name, a.city, " 
                 + "a.district, a.state, a.country," 
                 + "a.address_mode from employee as emp "
                 + "inner join address as a on emp.id = a.employee_id "
                 + "where emp.is_delete = '0'");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            List<Address> addressDetails = new ArrayList<Address>();
            Employee employee = new Employee(resultSet.getInt(1),
                                             resultSet.getString(2),
                                             resultSet.getString(3),
                                             resultSet.getString(4),
                                             resultSet.getLong(5),
                                             resultSet.getLong(6),
                                             resultSet.getDate(7), null);
            int employeeId = resultSet.getInt(1);
            while (employeeId == resultSet.getInt(1)) {
                Address address =  new Address(resultSet.getInt(8),
                                               resultSet.getString(9),
                                               resultSet.getString(10),
                                               resultSet.getString(11),
                                               resultSet.getString(12),
                                               resultSet.getString(13),
                                               resultSet.getString(14),
                                               resultSet.getString(15));
                addressDetails.add(address);
                if (resultSet.next()) { 
                    continue; 
                } else { 
                    break; 
                  }
            }
            employee.setAddress(addressDetails);
            employees.add(employee);
        }
        return employees;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployee(String name, String desgination, String email, 
            long phoneNumber,long salary, Date dob, int id) 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("update employee set name = ?, desgination = ?,"
                + "email = ?, phone_number = ?, salary = ?,"
                + "dob = ? where id = ?");
        prepareStatement.setString(1, name);
        prepareStatement.setString(2, desgination);
        prepareStatement.setString(3, email);
        prepareStatement.setLong(4, phoneNumber);
        prepareStatement.setLong(5, salary);
        prepareStatement.setDate(6, dob);
        prepareStatement.setInt(7, id);
        int count = prepareStatement.executeUpdate();
        return 0 != count;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployeeAddress(int id, Address address, int addressOption)
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("select id from address where employee_id = ? "
                + "and is_delete = '0'", ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
        prepareStatement.setInt(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        resultSet.last();
        if (resultSet.getRow() >= addressOption) {
            System.out.println("Enter");
            resultSet.first();
            while (1 < addressOption) {
                resultSet.next();
                addressOption--;
            }
            int addressId = resultSet.getInt(1);
            prepareStatement = connection.prepareStatement
                    ("update address set door_no = ?, street_name = ?,"
                    + "city = ?, district = ?, state = ?, country = ? "
                    + "where id = ?");
            prepareStatement.setString(1, address.getDoorNo());
            prepareStatement.setString(2, address.getStreetName());
            prepareStatement.setString(3, address.getCity());
            prepareStatement.setString(4, address.getDistrict());
            prepareStatement.setString(5, address.getState());
            prepareStatement.setString(6, address.getCountry());
            prepareStatement.setInt(7, addressId);
            prepareStatement.executeUpdate();
            return true;
        } else {
            return false;
            }
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isExistAddressType(int id, String addressType) 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("select count(id) from address where employee_id = ? and " 
                + "address_mode = ? and is_delete = '0'");
        prepareStatement.setInt(1, id);
        prepareStatement.setString(2, addressType);
        ResultSet resultSet = prepareStatement.executeQuery();
        resultSet.next();
        return 0 != resultSet.getInt(1);     
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Address> getAddressDetails(int id) 
            throws SQLException, ClassNotFoundException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        List<Address> addressDetails = new ArrayList<Address>();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("select * from address where employee_id = ? and is_delete = '0'");
        prepareStatement.setInt(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Address address =  new Address(resultSet.getInt(2),
                                           resultSet.getString(3),
                                           resultSet.getString(4),
                                           resultSet.getString(5),
                                           resultSet.getString(6),
                                           resultSet.getString(7),
                                           resultSet.getString(8),
                                           resultSet.getString(9));
            addressDetails.add(address);
        }
        return addressDetails; 
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Employee> getDeletedEmployee() 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        List<Employee> employees = new ArrayList<Employee>();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("select * from employee where is_delete = '1'");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Employee employee =  new Employee(resultSet.getInt(1),
                                               resultSet.getString(2),
                                               resultSet.getString(3),
                                               resultSet.getString(4),
                                               resultSet.getLong(5),
                                               resultSet.getLong(6),
                                               resultSet.getDate(7), null);
            employees.add(employee);    
        }  
        return employees;
    }

     /**
     * {inheritDoc}
     */
    @Override
    public boolean restoreEmployee(int id) 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("update employee set is_delete = '0' where id = ?");
        prepareStatement.setInt(1, id);
        prepareStatement.executeUpdate();
        prepareStatement = connection.prepareStatement
                ("update address set is_delete = '0' where employee_id = ?");
        prepareStatement.setInt(1, id);
        int count = prepareStatement.executeUpdate();
        return 0 != count;    
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean addEmployeeAddress(Address address) 
            throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement
                ("insert into address (employee_id, door_no, street_name," 
                + "city, district, state, country, address_mode)" 
                + "values (?, ?, ?, ?, ?, ?, ?, ?)");
        prepareStatement.setInt(1, address.getEmpId());
        prepareStatement.setString(2, address.getDoorNo());
        prepareStatement.setString(3, address.getStreetName());
        prepareStatement.setString(4, address.getCity());
        prepareStatement.setString(5, address.getDistrict());
        prepareStatement.setString(6, address.getState());
        prepareStatement.setString(7, address.getCountry());
        prepareStatement.setString(8, address.getAddressMode());
        int addressResult = prepareStatement.executeUpdate();
        return 0 != addressResult;
    }   
}