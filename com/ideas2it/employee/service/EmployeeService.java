package com.ideas2it.employee.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * It contains the method signature
 *
 * @author vignesh r 
 * @version 1.0 17-03-2021
 */
public interface EmployeeService {

    /**
     * It is used to pass employee details Dao
     * @param name employee name
     * @param desgination employee email-id
     * @param emailId employee email-id
     * @param phoneNumber employee phonenumber
     * @param salary employee salary
     * @param dob employee date of birth
     * @param employeeAddressDetails lists of employee address
     * @return boolean value
     */
    public boolean insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails)
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to check wheather employee id exist or not
     * @param id employeeId
     * @return boolean value
     */
    public boolean isEmpIdExist(int id) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to delete employee based upon employee id
     * @param id employee id
     * @return boolean  
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException;

    /**
     * It is used to get individual employee details
     * @param id employee id
     * @return individual employee details
     */
    public String getSpecificEmployee(int id) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to get all employee
     * @return all employee details  
     */
    public List<String> getAllEmployee() 
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
            long phoneNumber,long salary, Date dob, int id) 
            throws SQLException, ClassNotFoundException;

   /**
     * It is used to update employee address details 
     * @param id employee id
     * @param address employee address details
     * @param addressOption address type option
     * @return boolean value
     */
    public boolean updateEmployeeAddress(int id, String[] addressDetails, int addressOption) 
            throws SQLException, ClassNotFoundException; 

    /**
     * It is used to add employee address details
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean addEmployeeAddress(int employeeId, String[] addressDetails) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to check wheather the employee type is exist or not
     * @param id employee id
     * @param addressType type of address
     * @return boolean value
     */
    public boolean isExistAddressType(int id, String addressType) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to validate phone number
     * @param phone number Employee phone number
     * @return boolean value
     */
    public boolean validatePhoneNumber(long phoneNumber) 
            throws SQLException, ClassNotFoundException;

     /**
     * It is used to validate email-id
     * @param emailId Employee email-id
     * @return boolean value
     */
    public boolean validateEmail(String emailId) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to get specific address details
     * @param id employee id
     * @return address details
     */
    public List<String> getAddressDetails(int id) 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to get delete employee details
     * @return employee details
     */
    public List<String> getDeletedEmployee() 
            throws SQLException, ClassNotFoundException;

    /**
     * It is used to restore the deleted employee details
     * @param id employee id
     * @return boolean value
     */
    public boolean restoreEmployee(int id) 
            throws SQLException, ClassNotFoundException;
}
