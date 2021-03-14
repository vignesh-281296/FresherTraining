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
     * @return boolean value
     */
    public boolean createEmployee(String name, String desgination, String emailId,
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails) throws SQLException, ClassNotFoundException {
        return employeeService.insertEmployee(name, desgination, emailId, phoneNumber, salary, dob, employeeAddressDetails);	
    }

    /**
     * Method to check wheather the employee id is exist or not 
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException {
        return employeeService.isEmpIdExist(id);
    }

    /** Method to delete employee
     * @param id employee id
     * @return boolean value
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        return employeeService.deleteEmployee(id);
    }

    /**
     * Method to get individual employee detail
     * @param id employee id
     * @return individual employee detail
     */
    public String getSpecificEmployee(int id) throws SQLException, ClassNotFoundException {
        return employeeService.getSpecificEmployee(id);
    }

    /**
     * Method to display all employee details
     * @return all employee details
     */
    public List<String> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeService.getAllEmployee();
    }

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
                                  long salary, Date dob, int id) throws SQLException, ClassNotFoundException {
        return employeeService.updateEmployee(name, desgination, email, phoneNumber, salary, dob, id); 
    }

     /**
     * Method to update employee address details
     * @param id employee id
     * @param addressDetails array of address details
     * @return boolean value 
     */
    public boolean updateEmployeeAddress(int id, String[] addressDetails) throws SQLException, ClassNotFoundException {
        return employeeService.updateEmployeeAddress(id, addressDetails);
    }

    /**
     * Method to check wheather the employee address type is exist or not
     * @param id employee id
     * @param addressType type of address
     * @return boolean value
     */
    public boolean isExistAddressType(int id, String addressType) throws SQLException, ClassNotFoundException {
        return employeeService.isExistAddressType(id, addressType);
    }  
}