package com.ideas2it.employee.controller;

import java.sql.Date;
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
     * @return It will return employee id from employee service
     */
    public boolean createEmployee(String name, String desgination, String emailId,
                                  long phoneNumber, long salary, Date dob) {
        return employeeService.insertEmployee(name, desgination, emailId, phoneNumber, salary, dob);	
    }

    /**
     * Method send employee addrress values to EmployeeService to insertAddress method
     * @param empId employee id
     * @param doorNo employee door no
     * @param streetName employee street name
     * @param city employee city
     * @param district employee district 
     * @param state employee state
     * @param country employee country
     * @param addressMode employee type of address
     * @return It will return employee id from employee service
     */
    public boolean createAddress(String doorNo, String streetName,
                                 String city, String district, String state, 
                                 String country, String addressMode) {
        return employeeService.insertAddress(doorNo, streetName, city, district, 
                                             state, country, addressMode);	
    }

    /**
     * Method to get all employee details
     */
    public List<String> getAllEmployee() {
        return employeeService.getAllEmployee();
    } 
	
    /**
     * Method to get all employee address details
     */
    public List<String> getAllEmployeeAddress() {
        return employeeService.getAllEmployeeAddress();
    }	

    /**
     * Method to check wheather the emp id is exist or not
     * @return true if emp id exist or false
     */
    public boolean isEmpIdExist(int id) {
        return employeeService.isEmpIdExist(id);
    }

    /** Method to delete employee
     * @param id employee id
     */
    public boolean deleteEmployee(int id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * Method to display employee detail based upon employee id
     */
    public String getEmployee(int id) {
        return employeeService.getEmployee(id);     
    }

    /**
     * Method to display particular employee address based upon 
     * employee id
     * @param id employee id
     * @param addressType type of address
     */
    public String getAddress(int id, String addressType) {
        return employeeService.getAddress(id, addressType);
    }
}