package com.ideas2it.employee.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ideas2it.employee.service.impl.EmployeeServiceImpl;

/**
 * EmployeeController send data to employee serivce class
 */
public class EmployeeController {

    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    /**
     * Method send values to EmployeeService to insertEmployee method
     * @param name employee name
     * @param desgination employee position
     * @param emailId employee email-id
     * @param phoneNumber employee phonenumber
     * @param salary employee salary
     * @param dob employee date of birth 
     * @param address list of employee address details
     * @return boolean status
     */
    public boolean createEmployee(String name, String desgination, String emailId,
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails) throws SQLException, ClassNotFoundException {
        return employeeService.insertEmployee(name, desgination, emailId, phoneNumber, salary, dob, employeeAddressDetails);	
    }

    /**
     * Method to check wheather the emp id is exist or not 
     * @param id employee id
     * @return true if emp id exist or false
     */
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException {
        return employeeService.isEmpIdExist(id);
    }

    /** Method to delete employee
     * @param id employee id
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        return employeeService.deleteEmployee(id);
    }

    /**
     * Method to get individual employee details
     * @param id employee id
     */
    public String getSpecificEmployee(int id) throws SQLException, ClassNotFoundException {
        return employeeService.getSpecificEmployee(id);
    }

    /**
     * Method to display all employee details
     * @param id employee id
     */
    public List<String> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeService.getAllEmployee();
    }  
}