package com.ideas2it.employee.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.sessionfactory.DatabaseConnection;

/**
 * It is used to store and retrives datas to database
 *
 * @author vignesh r
 * @created at 13-03-2021 
 */  
public class EmployeeDaoImpl implements EmployeeDao {
   private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
   
    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertEmployee(Employee employee) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("insert into employee (name, desgination, email," 
                    + "phone_number, salary, dob, is_delete) values (?, ?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS); 
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getDesgination());
            prepareStatement.setString(3, employee.getEmail());
            prepareStatement.setLong(4, employee.getPhoneNumber());
            prepareStatement.setFloat(5, employee.getSalary());
            prepareStatement.setDate(6, employee.getDob());
            prepareStatement.setBoolean(7, true);
            int employeeResult = prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            
            resultSet.next();
            
            int employeeId = resultSet.getInt(1);
            List<Address> employeeAddress = employee.getAddress();
            if ((1 == employeeResult) && insertAddress(employeeAddress, connection, employeeId)) {
                connection.commit();
                count = true;
            } else {
                connection.rollback();
                count = false;
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }      
        return count;           
    }

    private boolean insertAddress(List<Address> employeeAddress, Connection con, int employeeId) {
        int count = 0;
        try {
            Connection connection = con; 
            PreparedStatement prepareStatement = connection.prepareStatement
                   ("insert into address (employee_id, door_no, street_name," 
                   + "city, district, state, country, address_mode, is_delete)" 
                   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (Address address : employeeAddress) {
                prepareStatement.setInt(1, employeeId);
                prepareStatement.setString(2, address.getDoorNo());
                prepareStatement.setString(3, address.getStreetName());
                prepareStatement.setString(4, address.getCity());
                prepareStatement.setString(5, address.getDistrict());
                prepareStatement.setString(6, address.getState());
                prepareStatement.setString(7, address.getCountry());
                prepareStatement.setString(8, address.getAddressMode());
                prepareStatement.setBoolean(9, true);
                prepareStatement.addBatch();
            }
            int rowAffected[] = prepareStatement.executeBatch();
            count = rowAffected.length;
            } catch(SQLException e) {
                e.printStackTrace();
            }   
            return 0 != count; 
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isEmpIdExist(int id) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select id from employee Where id = ? and is_delete = true");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            count = resultSet.next();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return count;            
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteEmployee(int id) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update employee set is_delete = false where id = ?");
            prepareStatement.setInt(1, id);
            int employeeDetails = prepareStatement.executeUpdate();
            prepareStatement = connection.prepareStatement
                    ("update address set is_delete = false where employee_id = ?");
            prepareStatement.setInt(1, id);
            int addressDelete = prepareStatement.executeUpdate();
            System.out.println(employeeDetails);
            System.out.println(addressDelete);
            if (0 == employeeDetails && 0 == addressDelete) {
                connection.rollback();
                count = false;
            } else {
                connection.commit();
                count = true;
            }
            connection.close(); 
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return count;     
    }

    /**
     * {inheritDoc}
     */
    @Override   
    public Employee getSpecificEmployee(int id) {
         Employee employee = null;
         try {
             Connection connection = databaseConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement
                     ("select emp.id, emp.name, emp.desgination, emp.email, emp.phone_number, "
                     + "emp.salary, emp.dob, a.id, a.employee_id, a.door_no, a.street_name, a.city, "
                     + "a.district, a.state, a.country, a.address_mode from employee as emp "
                     + "left join address as a on emp.id = a.employee_id and a.is_delete = true "
                     + "where emp.id = ? and emp.is_delete = true");
             prepareStatement.setInt(1, id);
             ResultSet resultSet = prepareStatement.executeQuery();
             if (resultSet.next()) {
                 employee = new Employee(resultSet.getInt(1),
                         resultSet.getString(2), resultSet.getString(3),
                         resultSet.getString(4), resultSet.getLong(5),
                         resultSet.getFloat(6), resultSet.getDate(7));
                 int employeeId = resultSet.getInt(1);
                 List<Address> addressDetails = new ArrayList<Address>();
                 while (employeeId == resultSet.getInt(1)) {
                     if (0 != resultSet.getInt(8)) {
                         Address address = new Address(resultSet.getInt(8),
                                 resultSet.getInt(9), resultSet.getString(10),
                                 resultSet.getString(11), resultSet.getString(12),
                                 resultSet.getString(13), resultSet.getString(14),
                                 resultSet.getString(15), resultSet.getString(16));
                        addressDetails.add(address);
                     }
                     if (!resultSet.next()) {
                         break;
                     }
                 }
                 employee.setAddress(addressDetails);
                 connection.close();
             }
         } catch(SQLException e) {
             e.printStackTrace();
        }
        return employee;  	        
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select emp.id, emp.name, emp.desgination, emp.email," 
                    + "emp.phone_number, emp.salary, emp.dob, a.id, "
                    + "a.employee_id, a.door_no, a.street_name, a.city, " 
                    + "a.district, a.state, a.country," 
                    + "a.address_mode from employee as emp "
                    + "left join address as a on emp.id = a.employee_id "
                    + "and a.is_delete = true where emp.is_delete = true");
            ResultSet resultSet = prepareStatement.executeQuery();
            boolean flag = true;
            if(resultSet.next()) { 
                while (flag) {
                    List<Address> addressDetails = new ArrayList<Address>();
                    Employee employee = new Employee(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getLong(5),
                            resultSet.getFloat(6), resultSet.getDate(7));
                    int employeeId = resultSet.getInt(1);
                    while (employeeId == resultSet.getInt(1)) {
                        if (0 != resultSet.getInt(8)) {
                            Address address = new Address(resultSet.getInt(8),
                                    resultSet.getInt(9), resultSet.getString(10),
                                    resultSet.getString(11), resultSet.getString(12),
                                    resultSet.getString(13), resultSet.getString(14),
                                    resultSet.getString(15), resultSet.getString(16));
                            addressDetails.add(address);
                        }
                        if (!resultSet.next()) {
                            flag = false;
                            break;
                        }
                    }
                    employee.setAddress(addressDetails);
                    employees.add(employee);
                }
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }  
        return employees;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployee(int id, Employee employee) {
        int count = 0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update employee set name = ?, desgination = ?, "
                    + "email = ?, phone_number = ?, salary = ?, "
                    + "dob = ? where id = ?");
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getDesgination());
            prepareStatement.setString(3, employee.getEmail());
            prepareStatement.setLong(4, employee.getPhoneNumber());
            prepareStatement.setFloat(5, employee.getSalary());
            prepareStatement.setDate(6, employee.getDob());
            prepareStatement.setInt(7, id);
            count = prepareStatement.executeUpdate();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }   
        return 0 != count;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployeeAddress(int addressId, Address address) {
        int count = 0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
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
            count = prepareStatement.executeUpdate();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }  
        return 0 != count;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Employee> getDeletedEmployee() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                ("select * from employee where is_delete = false");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee =  new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getFloat(6),
                        resultSet.getDate(7));
                employees.add(employee);    
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }    
        return employees;
    }

     /**
     * {inheritDoc}
     */
    @Override
    public boolean restoreEmployee(int id) {
        int count = 0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update employee set is_delete = true where id = ?");
            prepareStatement.setInt(1, id);
            count = prepareStatement.executeUpdate();
            if (1 == count) {
                count = 1;
                prepareStatement = connection.prepareStatement
                        ("update address set is_delete = true where employee_id = ?");
                prepareStatement.setInt(1, id);
                prepareStatement.executeUpdate();
                connection.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return 0 != count;    
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean addEmployeeAddress(Address address) {
        int addressResult = 0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("insert into address (employee_id, door_no, street_name," 
                    + "city, district, state, country, address_mode, is_delete)" 
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            prepareStatement.setInt(1, address.getEmpId());
            prepareStatement.setString(2, address.getDoorNo());
            prepareStatement.setString(3, address.getStreetName());
            prepareStatement.setString(4, address.getCity());
            prepareStatement.setString(5, address.getDistrict());
            prepareStatement.setString(6, address.getState());
            prepareStatement.setString(7, address.getCountry());
            prepareStatement.setString(8, address.getAddressMode());
            prepareStatement.setBoolean(9, true);
            addressResult = prepareStatement.executeUpdate();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }  
        return 0 != addressResult;
    }
    
    /**
     * {inheritDoc}
     */
    @Override
    public boolean checkDeletedEmpId(int id) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select id from employee Where id = ? and is_delete = false");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            count = resultSet.next();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }  
        return count;            
    }

    /**
     * {inheritDoc}
     */
    @Override   
    public List<Address> getAddressDetails(int id) {
        List<Address> addressDetails =  new ArrayList<Address>();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select * from address where employee_id = ? and is_delete = true");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Address address =  new Address(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9));
                addressDetails.add(address);
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return addressDetails;  	        
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteAddress(int id) {
        int count = 0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update address set is_delete = false where id = ?");
            prepareStatement.setInt(1, id);
            count = prepareStatement.executeUpdate();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return 0 != count;     
    }

    /**
     * {inheritDoc}
     */
    @Override   
    public Employee getEmployee(int id) {
        Employee employee = null;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select * from employee where id = ? and is_delete = true");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            employee =  new Employee(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getLong(5),
                    resultSet.getFloat(6),
                    resultSet.getDate(7));
            connection.close();           
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return employee;  	        
    } 
}