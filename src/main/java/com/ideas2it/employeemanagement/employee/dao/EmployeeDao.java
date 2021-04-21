package com.ideas2it.employeemanagement.employee.dao;

import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.exceptions.CreationFailsException;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.exceptions.FetchException;

/**
 * It contain method signature and perform crud 
 * operation and interact with database
 */
public interface EmployeeDao {
    
    /**
     * It is used to create employee
     * @param employee is an Employee details
     * @return boolean value
     * @throws CreationFailsException 
     * @throws EmployeeManagementException 
     */ 
    public void insertEmployee(Employee employee) throws EmployeeManagementException;

    /**
     * check employee id exist or not 
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmployeeExist(int id);

    /**
     * get specific employee details with addressess
     * @param id employee id
     * @return employee details
     * @throws FetchException 
     * @throws EmployeeManagementException 
     */
    public Employee getSpecificEmployeeWithAddressess(int id) throws EmployeeManagementException;

    /**
     * get specific employee details
     * @param id employee id
     * @return employee details
     * @throws EmployeeManagementException 
     */
    public Employee getSpecificEmployee(int id) throws EmployeeManagementException;

    /**
     * get specific employee details with projects
     * @param id employee id
     * @return employee details
     * @throws EmployeeManagementException 
     */
    public Employee getSpecificEmployeeWithProjects(int id) throws EmployeeManagementException;

    /**
     * get all emplooyee details
     * @return employee details
     * @throws FetchException 
     */  
    public List<Employee> getAllEmployee() throws EmployeeManagementException;

    /**
     * check for deleted employee id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmployeeDeleted(int id);

    /**
     * It is used to update employee details
     * @param employee employee details
     * @return boolean value
     */
    public boolean updateEmployee(Employee employee);
}