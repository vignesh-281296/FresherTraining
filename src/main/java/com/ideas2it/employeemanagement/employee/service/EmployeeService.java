package com.ideas2it.employeemanagement.employee.service;

import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.EmployeeManagementException;

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
     * @param addressDetails lists of employee address
     * @return boolean value
     * @throws EmployeeManagementException 
     */
    public void insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, float salary, Date dob, List<List<String>> addressDetails) throws EmployeeManagementException;

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
     * @throws EmployeeManagementException 
     */
    public void isEmployeeExist(int id) throws EmployeeManagementException;

    /**
     * get individual employee details
     * @param id employee id
     * @return individual employee details
     * @throws EmployeeManagementException 
     */
    public Employee getSpecificEmployee(int id) throws EmployeeManagementException;
 
    /**
     * get all employee details
     * @return employee details
     * @throws EmployeeManagementException 
     */
    public List<Employee> getAllEmployee() throws EmployeeManagementException;

    /**
     * It is used to delete employee
     * @param id employee id
     * @return id employee id
     * @throws EmployeeManagementException 
     */
    public void deleteEmployee(int id) throws EmployeeManagementException;

    /**
     * get deleted employee details
     * @return employee detail
     * @throws EmployeeManagementException 
     */
  //  public List<List<String>> getDeletedEmployeeDetails() throws EmployeeManagementException;

    /**
     * check wheather the deleted employee exist or not
     * @param id employee id
     * @return boolean value
     */
   // public boolean isEmployeeDeleted(int id);

    /**
     * It is used to restore employee 
     * @param id employee id
     * @return boolean 
     * @throws EmployeeManagementException 
     */
    public void restoreEmployee(int id) throws EmployeeManagementException;

    /**
     * It is used to update employee address details 
     * @param id employee id
     * @param name employee name
     * @param desgination employee desgination
     * @param email employee email
     * @param phoneNumber employee phone number
     * @param salary employee salary
     * @param dob employee date of birth
     * @param addressess employee address details
     * @return boolean value
     * @throws EmployeeManagementException 
     */
    public void updateEmployee(int id, String name, String desgination,
			String email, long phoneNumber, float salary, Date dob, List<List<String>>addressess) throws EmployeeManagementException; 

    /**
     * It is used to get employee address details
     * @param id employee id
     * @return employee address details
     * @throws EmployeeManagementException 
     */
  //  public Map<Integer, String>  getEmployeeAddressDetails(int id) throws EmployeeManagementException;

    /**
     * It is used to update employee address
     * @param addressIndex address no
     * @param addressId address id
     * @param addressDetails employee address details
     * @return boolean value
     * @throws EmployeeManagementException 
     */
   // public boolean updateEmployeeAddress(int addressIndex, int addressId, String[] addressDetail, int id) throws EmployeeManagementException;

    /**
     * It is used to get all project details
     * @return all project details
     * @throws EmployeeManagementException 
     */    
    public List<Project> getAllProjectDetails() throws EmployeeManagementException;

    /**
     * check wheather project id is exist or not
     * @param projectId project id
     * @return boolean value
     * @throws NoIdExistException 
     */
   // public void isProjectExist(int projectId) throws NoIdExistException;

    /**
     * It is used to assign employee to project
     * @param empId employee id
     * @param projectIds multiple project id
     * @return boolean value 
     * @throws EmployeeManagementException 
     */
    public boolean assignEmployee(int empId, List<Integer> projectIds) throws EmployeeManagementException;

    /**
     * It is used to add employee address
     * @param employeeId employee id
     * @param addressDetails employee address details
     * @return boolean value
     * @throws EmployeeManagementException 
     */
   // public boolean addEmployeeAddress(int employeeId, String[] addressDetail) throws EmployeeManagementException;

    /**
     * It is used to get assigned employee and project details
     * @param id employee id
     * @return employee details
     * @throws EmployeeManagementException 
     */
   // public List<String> getAssignedEmployee(int id) throws EmployeeManagementException;

    /**
     * It is used to unassign employee
     * @param id employee id 
     * @paraam projectId project id
     * @return boolean value
     */
   // public boolean unAssignEmployee(int id, int projectId);
    
    /**
     * get individual employee details
     * @param id employee id
     * @return employee details
     * @throws EmployeeManagementException 
     */
    public Employee getSpecificEmployeeDetails(int id) throws EmployeeManagementException;
    
    /**
     * It is used to get assigned employee and project details
     * @param id employee id
     * @return employee details
     * @throws EmployeeManagementException 
     */
    public List<Project> getAssignedEmployeeDetails(int id) throws EmployeeManagementException;
    
    /**
     * get all employee details
     * @return employee details
     * @throws EmployeeManagementException
     */
    public List<Employee> getAllEmployeeDetails() throws EmployeeManagementException;
}
