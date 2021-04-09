package com.ideas2it.employeemanagement.employee.dao;

import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;

/**
 * It contain method signature and perform crud 
 * operation and interact with database
 */
public interface EmployeeDao {
    
    /**
     * It is used to create employee
     * @param employee is an Employee details
     * @return boolean value
     */ 
    public boolean insertEmployee(Employee employee);

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
     */
    public Employee getSpecificEmployeeWithAddressess(int id);

    /**
     * get specific employee details
     * @param id employee id
     * @return employee details
     */
    public Employee getSpecificEmployee(int id);

    /**
     * get specific employee details with projects
     * @param id employee id
     * @return employee details
     */
    public Employee getSpecificEmployeeWithProjects(int id);

    /**
     * get all emplooyee details
     * @return employee details
     */  
    public List<Employee> getAllEmployee();

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