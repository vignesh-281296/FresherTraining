package com.ideas2it.employee.dao;

import java.util.List;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;

/**
 * EmployeeDoa is an interface of EmployeeDaoImpl
 */
public interface EmployeeDao {
    
    /**
     * Method insert data from employee class to database
     * @param employee it is an Employee class object
     */ 
    public boolean insertEmployee(Employee employee);

    /**
     * Method insert data from address class to database
     * @param address it is an Address class object
     */
    public boolean insertAddress(Address address);

    /**
     * Method to get all employees
     */
    public List<Employee> getAllEmployee();

     /**
     * Method to get all employees address
     */
    public List<Address> getAllEmployeeAddress();

    /**
     * Method to check wheather id is exist or not
     * @param id employee id
     */
    public boolean isEmpIdExist(int id);

    /**
     * Method to delete employee details 
     * @param id employee id
     */
    public boolean deleteEmployee(int id);

    /**
     * Method to update employee details
     * @param id employee id
     * @param employee employee details
     */
    public boolean updateEmployee(int id, Employee employee);

    /**
     * Method to employee details based upon id
     * @param id employee id
     */
    public Employee getEmployee(int id);

    /**
     * Method to employee address details based upon id
     * @param id employee id
     * @param addressType type of address
     */
    public Address getAddress(int id, String addressType);			  
}