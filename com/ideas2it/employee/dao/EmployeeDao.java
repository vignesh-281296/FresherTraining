package com.ideas2it.employee.dao;

import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

/**
 * EmployeeDao is an interface of EmployeeDaoImpl
 */
public interface EmployeeDao {
    
    /**
     * Method to all employee details to insert data in database
     * @param employee it is an Employee details
     * @return boolean value
     */ 
    public boolean insertEmployee(Employee employee) throws SQLException, ClassNotFoundException;

    /**
     * Method to check wheather id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to delete employee details 
     * @param id employee id
     * @return boolean value
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to display specific employee detail
     * @param id employee id
     * @return individual employee detail
     */
    public Employee getSpecificEmployee(int id) throws SQLException, ClassNotFoundException; 
 
    /**
     * Method to get all employee detail
     * @return all employee details
     */
     public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException; 

    /**
     * Method to update employee details
     * @param name employee name
     * @desgination employee desgination
     * @email employee email
     * @phoneNumber employee phone number
     * @salary employee salary
     * @dob employee date of birth
     * @id employee id
     * @return boolean value
     */
    public boolean updateEmployee(String name, String desgination, String email, long phoneNumber,
                                  long salary, Date dob, int id) throws SQLException, ClassNotFoundException; 

    /**
     * Method to update employee address details
     * @return boolean value
     */
    public boolean updateEmployeeAddress(int id, Address address) throws SQLException, ClassNotFoundException;

    /**
     * Method to check wheather the employee address type is exist or not
     * @param id employee id
     * @param addressType type of address
     * @return boolean value
     */
    public boolean isExistAddressType(int id, String addressType) throws SQLException, ClassNotFoundException;  			  
}