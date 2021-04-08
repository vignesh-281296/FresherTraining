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
            long phoneNumber, float salary, Date dob, List<String[]> addressDetails) {
        return employeeService.insertEmployee(name, desgination, emailId, phoneNumber, salary, 
                dob, addressDetails);	
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
     * check wheather employee id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmployeeExist(int id) {
        return employeeService.isEmployeeExist(id);
    }

    /**
     * It is used to get individual employee details
     * @param id employee id
     * @return employee details
     */
    public String getSpecificEmployee(int id) {
        return  employeeService.getSpecificEmployee(id);
    }

    /**
     * It is used to get all employee details
     * @return employee details
     */
    public List<String> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    /**
     * It is used to delete employee details
     * @param id employee id
     * @return boolean value
     */
    public boolean deleteEmployee(int id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * It is used to get deleted employee details
     * @return deleted employee details
     */
    public List<String> getDeletedEmployeeDetails() {
        return employeeService.getDeletedEmployeeDetails();
    }

    /**
     * It is used check deleted employee
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmployeeDeleted(int id) {
        return employeeService.isEmployeeDeleted(id);
    }

    /**
     * It is used to restore employee 
     * @param id employee id
     * @return boolean value
     */
    public boolean restoreEmployee(int id) {
        return employeeService.restoreEmployee(id);
    }

    /**
     * It is used to update employee details
     * @param id employee id
     * @param employeeDetails 
     * @return boolean value
     */
    public boolean updateEmployee(int id, String[] employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails); 
    }

    /**
     * It is used to  get specific address details
     * @param id employee id
     * @return specific address details 
     */
    public Map<Integer, String> getEmployeeAddressDetails(int id) {
        return employeeService.getEmployeeAddressDetails(id);
    }

    /**
     * It is used to update employee address
     * @param addressIndex address no
     * @param addressId
     * @param addressDetail employee address detail
     * @param id employee id
     * @return boolean value
     */
    public boolean updateEmployeeAddress(int addressIndex, int addressId, String[] addressDetail, int id) {
        return employeeService.updateEmployeeAddress(addressIndex, addressId, addressDetail, id);
    }

    /**
     * It is used to get all project details
     * @return project details
     */
    public List<String> getAllProjectDetails() {
        return employeeService.getAllProjectDetails();
    }

    /**
     * It is used to check wheather project id exist or not
     * @param projectId
     * @return boolean value
     */
    public boolean isProjectExist(int projectId) {
        return employeeService.isProjectExist(projectId);
    }

    /**
     * It is used to assign employee to project
     * @param empId employee id
     * @param projectIds multiple project id
     * @return boolean value
     */
    public boolean assignEmployee(int empId, List<Integer> projectIds) {
        return employeeService.assignEmployee(empId, projectIds);
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
     * It is used to assigned employee details
     * @param id employee id
     * @return assigned employee details
     */
    public List<String> getAssignedEmployee(int id) {
        return employeeService.getAssignedEmployee(id);
    }

    /**
     * It is used to unassign employee
     * @param id employee id
     * @param projectId
     * @return boolean value
     */
    public boolean unAssignEmployee(int id, int projectId) {
        return employeeService.unAssignEmployee(id, projectId);
    }
}