package com.ideas2it.employeemanagement.employee.controller;

import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;

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
            long phoneNumber, float salary, Date dob, List<String[]> employeeAddressDetails) {
        return employeeService.insertEmployee(name, desgination, emailId, phoneNumber, salary, 
                dob, employeeAddressDetails);	
    }

    /**
     * It is used to  check wheather the employee id is exist or not 
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmpIdExist(int id) {
        return employeeService.isEmpIdExist(id);
    }

    /** It is used to delete employee
     * @param id employee id
     * @return boolean value
     */
    public boolean deleteEmployee(int id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * It is used to get individual employee detail
     * @param id employee id
     * @return individual employee detail
     */
    public String getSpecificEmployee(int id) {
        return employeeService.getSpecificEmployee(id);
    }

    /**
     * It is used to get all employee details
     * @return all employee details
     */
    public List<String> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    /**
     * It is used to update employee details
     * @id employee id
     * @employeeDetails 
     * @return boolean value
     */
    public boolean updateEmployee(int id, String[] employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails); 
    }

    /**
     * It is used to update employee address details
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value 
     */
    public boolean updateEmployeeAddress(int addressId, String[] addressDetails){
        return employeeService.updateEmployeeAddress(addressId, addressDetails);
    }

    /**
     * It is used to check validate phone number
     * @param phone number Employee phone number
     * @return boolean value
     */
    public boolean validatePhoneNumber(long phoneNumber) {
        return employeeService.validatePhoneNumber(phoneNumber);
    }

    /**
     * It is used to check validate email-id
     * @param emailId Employee email-id
     * @return boolean value
     */
    public boolean validateEamil(String emailId) {
        return employeeService.validateEmail(emailId);
    }

    /**
     * It is used to check validate dob
     * @param dob Employee date of birth
     * @return boolean value
     */
    public boolean validateDob(String dob) {
        return employeeService.validateDob(dob);
    }

    /**
     * It is used to  get specific address details
     * @param id employee id
     * @return specific address details 
     */
    public Map<Integer, String> getAddressDetails(int id) {
        return employeeService.getAddressDetails(id);
    }

    /**
     * It is used to  get deleted employee details
     * @return employee details
     */
    public List<String> getDeletedEmployee() {
        return employeeService.getDeletedEmployee();
    } 

    /**
     * It is used to restore the deleted employee details
     * @param id employee id
     * @return boolean value
     */
    public boolean restoreEmployee(int id) {
        return employeeService.restoreEmployee(id);
    }

    /**
     * It is used to add your employee address details
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean addEmployeeAddress(int employeeId, String[] addressDetails) {
        return employeeService.addEmployeeAddress(employeeId, addressDetails);
    }

    /**
     * It is used to check wheather deleted employee id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean checkDeletedEmpId(int id) {
        return employeeService.checkDeletedEmpId(id);
    } 

     /** It is used to specific address details 
     * @param id address id
     * @return boolean value
     */
    public boolean deleteAddress(int id) {
        return employeeService.deleteAddress(id);
    }   
}