package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.EmployeeManagementException;

/**
 * It contains the method signature
 *
 * @author vignesh r 
 * @version 1.0 24-03-2021
 */
public interface ProjectService {

    /**
     * It will used to create project
     * @param name project name
     * @param managerName project manager name
     * @param startDate project start date
     * @param endDate project end date
     * @param employee list of employee details
     * @return boolean value
     * @throws EmployeeManagementException 
     */
    public boolean createProject(String name, String managerName, 
            Date startDate, Date endDate) throws EmployeeManagementException;

    /**
     * It is used to validate date
     * @param date
     * @return boolean value
     */
    public boolean validateDob(String date);

    /**
     * It is used to check wheather the project id is exist or not
     * @param id project id
     * @return boolean value
     * @throws EmployeeManagementException 
     */
    public void isProjectExist(int id) throws EmployeeManagementException;

    /**
     * It is used to get individual project details
     * @param id project id
     * @return project details
     * @throws EmployeeManagementException 
     */
    public Project getSpecificProject(int id) throws EmployeeManagementException;

    /**
     * It is used to get all project details
     * @return project details
     * @throws EmployeeManagementException 
     */
    public List<Project> getAllProject() throws EmployeeManagementException;

    /**
     * It is used to delete project
     * @param id project id
     * @return boolean value
     * @throws EmployeeManagementException 
     */
    public void deleteProject(int id) throws EmployeeManagementException;

    /**
     * It is used to get deleted project details
     * @return deleted project details
     */
    //public List<String> getDeletedProjectDetails();

    /**
     * It is used to check wheather the project is delete
     * @param id project id
     * @return boolean value
     */
    //public boolean isProjectDeleted(int id);

    /**
     * It is used to restore project
     * @param id project id
     * @return booleanvalue
     * @throws EmployeeManagementException 
     */
    public void restoreProject(int id) throws EmployeeManagementException;

    /**
     * It is used to update project details
     * @param projectId 
     * @param projectName 
     * @param projectManagerName
     * @param startDate project start date
     * @param endDate project end date
     * @return boolean value
     * @throws EmployeeManagementException 
     */
    public void updateProject(int id, String projectName, String projectManagerName,
    		Date startDate, Date endDate) throws EmployeeManagementException;

    /**
     * It is used to get specific project details
     * @param id project id
     * @return project details
     * @throws EmployeeManagementException 
     */
    public Project getSpecificProjectDetails(int id) throws EmployeeManagementException;

    /**
     * It is used to get all employee details
     * @return employee details
     * @throws EmployeeManagementException 
     */
    public List<Employee> getAllEmployeeDetails() throws EmployeeManagementException;

    /**
     * It is used to check wheather the employee id is exist or not
     * @param employeeId 
     * @return boolean value
     */
   // public boolean isEmployeeExist(int employeeId);

    /**
     * It is used to assign project to employee
     * @param id project id
     * @param employeeIds multiple employee id
     * @throws EmployeeManagementException 
     */
    public void assignProject(int id, List<Integer> employeeIds) throws EmployeeManagementException;

    /**
     * It is used to get assigned project details
     * @param id project id
     * @return project details
     */
   // public List<String> getAssignedProject(int id);

    /**
     * It is used to un assign project
     * @param id project id
     * @param employeeId
     * @return boolean value
     */
    //public boolean unAssignProject(int id, int employeeId);
    
    /**
     * It is used to assigned project details
     * @param id project id
     * @return
     * @throws EmployeeManagementException 
     */
    public List<Employee> getAssignedProjectDetails(int id) throws EmployeeManagementException;
}