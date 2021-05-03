package com.ideas2it.employeemanagement.employee.service;

import java.util.List;

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
     * @param employee employee object
     * @throws EmployeeManagementException
     */
    public void insertEmployee(Employee employee) throws EmployeeManagementException;

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
     * It is used to restore employee 
     * @param id employee id
     * @return boolean 
     * @throws EmployeeManagementException 
     */
    public void restoreEmployee(int id) throws EmployeeManagementException; 

    /**
     * It is used to get all project details
     * @return all project details
     * @throws EmployeeManagementException 
     */    
    public List<Project> getAllProjectDetails() throws EmployeeManagementException;

    /**
     * It is used to assign employee to project
     * @param empId employee id
     * @param projectIds multiple project id
     * @throws EmployeeManagementException 
     */
    public void assignEmployee(int empId, List<Integer> projectIds) throws EmployeeManagementException;
    
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


	/** 
	 * It is used to update employee 
	 * @param employee employee object 
	 * @throws EmployeeManagementException
	 */
	public void updateEmployee(Employee employee) throws EmployeeManagementException;
}
