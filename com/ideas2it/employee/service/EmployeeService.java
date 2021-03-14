package com.ideas2it.employee.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeService contains the method signature
 */
public interface EmployeeService {

    /**
     * Method used to pass employee details Dao
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
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails)throws SQLException, ClassNotFoundException;

    /**
     * Method to check is employee id exist or not
     * @param id employeeId
     * @return boolean value
     */
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to delete employee based upon emp id
     * @param id employee id
     * @return boolean  
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to display individual employee details
     * @param id employee id
     * @return individual employee details
     */
    public String getSpecificEmployee(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to display all employee
     * @return all employee details  
     */
    public List<String> getAllEmployee() throws SQLException, ClassNotFoundException;

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
     * @param id employee id
     * @param addressDetails array of address details
     * @return boolean value  
     */
    public boolean updateEmployeeAddress(int id, String[] addressDetails) throws SQLException, ClassNotFoundException; 

    /**
     * Method to check wheather the employee type is exist or not
     * @param id employee id
     * @param addressType type of address
     * @return boolean value
     */
    public boolean isExistAddressType(int id, String addressType) throws SQLException, ClassNotFoundException; 
}
