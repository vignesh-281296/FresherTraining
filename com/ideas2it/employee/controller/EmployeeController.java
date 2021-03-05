package com.ideas2it.employee.controller;

import java.util.List;
import java.util.Date;

import com.ideas2it.employee.service.EmployeeService;

/**
 * class for Employee Controller
 *
 * @author vignesh r
 * @version1.0 04-03-2021
 */
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeService();
    
    /**
     * Method to create Employee
     * @param name Employee name
     * @param city Employee city
     * @param email Employee email-id
     * @param phoneNumber Employee phone number
     * @param salary Employee salary
     * @param dob Employee Date of birth
     * @return true for insertion and false insertion failure  
     */
    public boolean createEmployee(String name, String city, String email, long phoneNumber, long salary, Date dob) {
        return employeeService.createEmployee(name, city, email, phoneNumber, salary, dob);
    }

    /**
     * Method to return employees present in the list
     * @return list of employee details
     */
    public List<String> displayAllEmployee() {
        return employeeService.getAllEmployee();
    }

    /**
     * Method to check if emp id is exist or not
     * @param id emp id
     * @return if id present in collection true or false
     */
    public boolean empIdExist(int id) {
        return employeeService.empIdExist(id);
    }

    /**
     * Method to delete employees
     * @param id emp id
     */
    public void deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
    }

    /**
     * Method to update name for employee
     * @param id emp id
     * @param name employee name
     */
    public void updateName(int id, String name) {
        employeeService.updateName(id, name);
    }

    /**
     * Method to update city for employee
     * @param id emp id
     * @param city employee city
     */
    public void updateCity(int id, String city) {
        employeeService.updateCity(id, city);
    }

    /**
     * Method to update email-id for employee
     * @param id emp id
     * @param emailId employee email-id
     */
    public void updateEmail(int id, String emailId){
        employeeService.updateEmail(id, emailId);
    }

    /**
     * Method to update phone number for employee
     * @param id emp id
     * @param phone number employee phone number
     */
    public void updatePhoneNumber(int id, long phoneNumber){
        employeeService.updatePhoneNumber(id, phoneNumber);
    }

    /**
     * Method to update salary for employee
     * @param id emp id
     * @param salary employee salary
     */
    public void updateSalary(int id, long salary){
        employeeService.updateSalary(id, salary);
    }

    /**
     * Method to update dob for employee
     * @param id emp id
     * @param dob employee dob
     */
    public void updateDob(int id, Date dob){
        employeeService.updateDob(id, dob);
    }

    /**
     * Method to validate phone number
     * @param phone number Employee phone number
     */
    public boolean validatePhoneNumber(long phoneNumber) {
        return employeeService.validatePhoneNumber(phoneNumber);
    }

    /**
     * Method to validate email-id
     * @param emailId Employee email-id
     */
    public boolean validateEamil(String emailId) {
        return employeeService.validateEmail(emailId);
    }
}