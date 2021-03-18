package com.ideas2it.employee.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

/**
 * It contain method signature
 */
public interface EmployeeDao {
    
    /**
     * It is used to create employee
     * @param employee it is an Employee details
     * @return boolean value
     */ 
    public boolean insertEmployee(Employee employee) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to check wheather id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmpIdExist(int id) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to delete employee details 
     * @param id employee id
     * @return boolean value
     */
    public boolean deleteEmployee(int id) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to get specific employee details
     * @param id employee id
     * @return individual employee detail
     */
    public Employee getSpecificEmployee(int id)
            throws SQLException, ClassNotFoundException; 
 
    /**
     * It is used to get all employee details
     * @return all employee details
     */
    public List<Employee> getAllEmployee() 
            throws SQLException, ClassNotFoundException; 

    /**
     * It is used to update employee details
     * @param name employee name
     * @desgination employee desgination
     * @email employee email
     * @phoneNumber employee phone number
     * @salary employee salary
     * @dob employee date of birth
     * @id employee id
     * @return boolean value
     */
    public boolean updateEmployee(String name, String desgination, String email, 
            long phoneNumber, long salary, Date dob, int id) 
            throws SQLException, ClassNotFoundException; 

    /**
     * It is used to update employee address details 
     * @param id employee id
     * @param address employee address details
     * @param addressOption address type option
     * @return boolean value
     */
    public boolean updateEmployeeAddress(int addressId, Address address) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to add your employee address details
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean addEmployeeAddress(Address address) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to check wheather the employee address type is exist or not
     * @param id employee id
     * @param addressType type of address
     * @return boolean value
     */
    public boolean isExistAddressType(int id, String addressType) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to get specific address details
     * @param id employee id
     * @return address details
     */
     public List<Address> getAddressDetails(int id) 
             throws SQLException, ClassNotFoundException; 

    /**
     * It is used to get deleted employee details
     * @return employee details
     */
    public List<Employee> getDeletedEmployee() 
            throws SQLException, ClassNotFoundException; 

    /**
     * It is used to restore the deleted employee details
     * @param id employee id
     * @return boolean value
     */
    public boolean restoreEmployee(int id) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to check wheather deleted employee id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean checkDeletedEmpId(int id) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to delete address details 
     * @param id address id
     * @return boolean value
     */
    public boolean deleteAddress(int id) 
            throws SQLException, ClassNotFoundException;	
	  
}