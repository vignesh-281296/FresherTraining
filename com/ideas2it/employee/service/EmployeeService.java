package com.ideas2it.employee.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeService contains the method signature
 */
public interface EmployeeService {

    /**
     * Method used to pass employee details into database
     * @param name employee name
     * @param desgination employee email-id
     * @param emailId employee email-id
     * @param phoneNumber employee phonenumber
     * @param salary employee salary
     * @param dob employee date of birth
     * @param employeeAddressDetails lists of employee address
     */
    public boolean insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails)throws SQLException, ClassNotFoundException;

    /**
     * Method to check is employee id exist or not
     @ param id employeeId
     */
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to delete employee based upon emp id
     * @param id employee id 
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to display individual employee details
     * @param id employee id
     */
    public String getSpecificEmployee(int id) throws SQLException, ClassNotFoundException;

    /**
     * Method to display all employee
     */
    public List<String> getAllEmployee() throws SQLException, ClassNotFoundException;
}
