package com.ideas2it.employeemanagement.employee.service;

import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Employee;

/**
 * It contains the method signature
 *
 * @author vignesh r 
 * @version 1.0 17-03-2021
 */
public interface EmployeeService {

    /**
     * It is used to pass employee details Dao
     * @param name employee name
     * @param desgination employee email-id
     * @param emailId employee email-id
     * @param phoneNumber employee phonenumber
     * @param salary employee salary
     * @param dob employee date of birth
     * @param employeeAddressDetails lists of employee address
     * @return boolean value
     */
    public boolean insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, float salary, Date dob, List<String[]> addressDetails);

    /**
     * It is used to validate phone number
     * @param phone number Employee phone number
     * @return boolean value
     */
    public boolean validatePhoneNumber(long phoneNumber);

    /**
     * It is used to validate email-id
     * @param emailId Employee email-id
     * @return boolean value
     */
    public boolean validateEmail(String emailId);

    /**
     * It is used to validate date
     * @param dob Employee date of birth
     * @return boolean value
     */
    public boolean validateDob(String dob);

    /**
     * check wheather the employee id exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmployeeExist(int id);

    /**
     * get individual employee details
     * @param id employee id
     * @return individual employee details
     */
    public String getSpecificEmployee(int id);
 
    /**
     * get all employee details
     * @return employee details
     */
    public List<String> getAllEmployee();

    /**
     * It is used to delete employee
     * @param id employee id
     * @return id employee id
     */
    public boolean deleteEmployee(int id);

    /**
     * get deleted employee details
     * @return employee detail
     */
    public List<String> getDeletedEmployeeDetails();

    /**
     * check wheather the deleted employee exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmployeeDeleted(int id);

    /**
     * It is used to restore employee 
     * @param id employee id
     * @return boolean 
     */
    public boolean restoreEmployee(int id);

    /**
     * It is used to update employee address details 
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean updateEmployee(int id, String[] employeeDetails); 

    /**
     * It is used get employee address details
     * @param id employee id
     * @return employee address details
     */
    public Map<Integer, String>  getEmployeeAddressDetails(int id);

    /**
     * It is used to update employee address
     * @param addressIndex address no
     * @param addressId address id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean updateEmployeeAddress(int addressIndex, int addressId, String[] addressDetail, int id);

    /**
     * It is used to get all project details
     * @return all project details
     */    
    public List<String> getAllProjectDetails();

    /**
     * check wheather project id is exist or not
     * @param projectId project id
     * @return boolean value
     */
    public boolean isProjectExist(int projectId);

    /**
     * It is used to assign employee to project
     * @param empId employee id
     * @projectIds multiple project id
     * @return boolean value
     */
    public boolean assignEmployee(int empId, List<Integer> projectIds);

    /**
     * It is used to add employee address
     * @param employeeId employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean addEmployeeAddress(int employeeId, String[] addressDetail);

    /**
     * It is used to get assigned employee and project details
     * @param id employee id
     * @return employee details
     */
    public List<String> getAssignedEmployee(int id);

    /**
     * It is used to un assign employee
     * @param id employee id 
     * @paraam projectId project id
     * @return boolean value
     */
    public boolean unAssignEmployee(int id, int projectId);
}
