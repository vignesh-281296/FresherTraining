package com.ideas2it.employee.service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ideas2it.employee.model.Employee;

/**
 * class for Employee Service
 *
 * @author vignesh r
 * @version1.0 04-03-2021
 */
public class EmployeeService {

    private Map<Integer, Employee> employeeDetails = new HashMap<Integer, Employee>();
    private int empId = 0;

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
    public boolean createEmployee(String name, String city, String email, long phoneNumber, 
                                  long salary, Date dob) {
         Employee employee = new Employee(name, city, email, phoneNumber, salary, dob);
         empId++;
         return (null == employeeDetails.put(empId, employee)) ? true : false;
    }

    /**
     * Method to return employees present in the list
     * @return list of employee details
     */
    public List<String> getAllEmployee() {
        List<String> employees = new ArrayList<String>();
        for (Employee employee : employeeDetails.values()) {
            employees.add(employee.toString());
        }
        return employees;
    }

    /**
     * Method to check if emp id is exist or not
     * @param id emp id
     * @return if id present in collection true or false
     */
    public boolean empIdExist(int id) {
        return (null != employeeDetails.get(id)) ? true : false;
    }

    /**
     * Method to delete employees
     * @param id emp id
     */
    public void deleteEmployee(int id) {
        employeeDetails.remove(id);
    }
    
    /**
     * Method to update name for employee
     * @param id emp id
     * @param name employee name
     */
    public void updateName(int id, String name) {
        Employee employee = employeeDetails.get(id);
        employee.setName(name);
    }

    /**
     * Method to update city for employee
     * @param id emp id
     * @param city employee city
     */
    public void updateCity(int id, String city) {
        Employee employee = employeeDetails.get(id);
        employee.setCity(city);
    }
    
    /**
     * Method to update email-id for employee
     * @param id emp id
     * @param emailId employee email-id
     */   
    public void updateEmail(int id, String emailId) {
        Employee employee = employeeDetails.get(id);
        employee.setEmail(emailId);    
    }
   
    /**
     * Method to update phone number for employee
     * @param id emp id
     * @param phone number employee phone number
     */
    public void updatePhoneNumber(int id, long phoneNumber) {
        Employee employee = employeeDetails.get(id);
        employee.setPhoneNumber(phoneNumber);
    }
   
    /**
     * Method to update salary for employee
     * @param id emp id
     * @param salary employee salary
     */
    public void updateSalary(int id, long salary) {
        Employee employee = employeeDetails.get(id);
        employee.setSalary(salary);
    }
   
    /**
     * Method to update dob for employee
     * @param id emp id
     * @param dob employee dob
     */
    public void updateDob(int id, Date dob) {
        Employee employee = employeeDetails.get(id);
        employee.setDob(dob);
    }
    
    /**
     * Method to validate phone number
     * @param phone number Employee phone number
     */
    public boolean validatePhoneNumber(long phoneNumber) {
        if (Long.toString(phoneNumber).matches("[7-9][0-9]{9}")) {
            return true;    
        } else {
            return false;
          }
    }

    /**
     * Method to validate email-id
     * @param emailId Employee email-id
     */
    public boolean validateEmail(String emailId) {
        if (emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
                              "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return true;    
        } else {
            return false;
          }
    }
}