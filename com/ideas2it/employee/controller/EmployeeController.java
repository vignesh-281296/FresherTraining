package com.ideas2it.employee.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ideas2it.employee.service.impl.EmployeeServiceImpl;

/**
 * It will send data to employee serivce
 */
public class EmployeeController {

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    /**
     * It is used to  create Employee      
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
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails) 
            throws SQLException, ClassNotFoundException {
        return employeeService.insertEmployee(name, desgination, emailId, phoneNumber, salary, dob, employeeAddressDetails);	
    }

    /**
     * It is used to  check wheather the employee id is exist or not 
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException {
        return employeeService.isEmpIdExist(id);
    }

    /** It is used to delete employee
     * @param id employee id
     * @return boolean value
     */
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        return employeeService.deleteEmployee(id);
    }

    /**
     * It is used to get individual employee detail
     * @param id employee id
     * @return individual employee detail
     */
    public String getSpecificEmployee(int id) throws SQLException, ClassNotFoundException {
        return employeeService.getSpecificEmployee(id);
    }

    /**
     * It is used to get all employee details
     * @return all employee details
     */
    public List<String> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeService.getAllEmployee();
    }

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
    public boolean updateEmployee(String name, String desgination, String email, long phoneNumber,
            long salary, Date dob, int id) throws SQLException, ClassNotFoundException {
        return employeeService.updateEmployee(name, desgination, email, phoneNumber, salary, dob, id); 
    }

    /**
     * It is used to update employee address details
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value 
     */
    public boolean updateEmployeeAddress(int id, String[] addressDetails, int addressOption) 
            throws SQLException, ClassNotFoundException {
        return employeeService.updateEmployeeAddress(id, addressDetails, addressOption);
    }

    /**
     * It is used to  check wheather the employee address type is exist or not
     * @param id employee id
     * @param addressType type of address
     * @return boolean value
     */
    public boolean isExistAddressType(int id, String addressType) 
            throws SQLException, ClassNotFoundException {
        return employeeService.isExistAddressType(id, addressType);
    }

    /**
     * It is used to check validate phone number
     * @param phone number Employee phone number
     * @return boolean value
     */
    public boolean validatePhoneNumber(long phoneNumber) 
            throws SQLException, ClassNotFoundException {
        return employeeService.validatePhoneNumber(phoneNumber);
    }

    /**
     * It is used to check validate email-id
     * @param emailId Employee email-id
     * @return boolean value
     */
    public boolean validateEamil(String emailId) 
            throws SQLException, ClassNotFoundException {
        return employeeService.validateEmail(emailId);
    }

    /**
     * It is used to  get specific address details
     * @param id employee id
     * @return specific address details 
     */
    public List<String> getAddressDetails(int id) 
            throws SQLException, ClassNotFoundException {
        return employeeService.getAddressDetails(id);
    }

    /**
     * It is used to  get deleted employee details
     * @return employee details
     */
    public List<String> getDeletedEmployee() 
            throws SQLException, ClassNotFoundException {
        return employeeService.getDeletedEmployee();
    } 

    /**
     * It is used to restore the deleted employee details
     * @param id employee id
     * @return boolean value
     */
    public boolean restoreEmployee(int id) 
            throws SQLException, ClassNotFoundException {
        return employeeService.restoreEmployee(id);
    }

    /**
     * It is used to add your employee address details
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean addEmployeeAddress(int employeeId, String[] addressDetails) 
            throws SQLException, ClassNotFoundException {
        return employeeService.addEmployeeAddress(employeeId, addressDetails);
    }    
}