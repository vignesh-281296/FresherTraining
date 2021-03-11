package com.ideas2it.employee.service;

import java.sql.Date;
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
     */
    public boolean insertEmployee(String name, String desgination, String emailId,
                              long phoneNumber, long salary, Date dob);

    /**
     * Method used to pass employee address into database
     * @param empId employee id
     * @param doorno employee doorno
     * @param streetName employee street name
     * @param city employee city
     * @param district employee district
     * @param state employee state
     * @param country employee country
     * @param addressMode Employee type of address
     */  
    public boolean insertAddress(String doorNo, String streetName,
                                 String city, String district, String state, 
                                 String country, String addressMode); 

    /**
     * Method to display all employee details
     */
    public List<String> getAllEmployee();

    /**
     * Method to display all employee address details
     */
    public List<String> getAllEmployeeAddress();

    /**
     * Method to check is employee id exist or not
     */
    public boolean isEmpIdExist(int id);

    /**
     * Method to delete employee based upon emp id
     * @param id employee id 
     */
    public boolean deleteEmployee(int id);

    /**
     * Method to display employee based upon employee id
     * @param id employee id
     */
    public String getEmployee(int id);  

    /**
     * Method to display employee address based upon employee id
     * @param id employee id
     * @param addressType type of address
     */
    public String getAddress(int id, String addressType);
}