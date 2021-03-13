package com.ideas2it.employee.dao;

import java.util.List;
import java.sql.SQLException;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

/**
 * EmployeeDoa is an interface of EmployeeDaoImpl
 */
public interface EmployeeDao {
    
    /**
     * Method insert data from employee class to database
     * @param employee it is an Employee class object
     */ 
    public boolean insertEmployee(Employee employee) throws SQLException, ClassNotFoundException;

    /**
     * Method to check wheather id is exist or not
     * @param id employee id
     */
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to delete employee details 
     * @param id employee id
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to display specific employee
     * @param id employee id
     */
    public Employee getSpecificEmployee(int id) throws SQLException, ClassNotFoundException; 
 
    /**
     * Method to display specific employee
     */
     public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;    			  
}