package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;

//import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;

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
     */
    public boolean createProject(String name, String managerName, 
            Date startDate, Date endDate);

    /**
     * It is used to validate date
     * @param date
     * @return boolean value
     */
    public boolean validateDob(String date);

    /**
     * It is used to check project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectIdExist(int id);

    /**
     * It is used to get specific project details
     * @param id project id
     * @return project details
     */
    public String getSpecificProject(int id);

    /**
     * It is used to get all project details
     * @return project details
     */
    public List<String> getAllProject();

    /**
     * It is used to delete specific employee
     * @param id project id
     * @return boolean value
     */
    public boolean deleteProject(int id);

     /**
     * It is used to get deleted project details
     * @return deleted project details
     */
    public List<String> getDeletedProject();

    /**
     * It is used to check wheather the deleted
     * project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean checkDeletedProjectId(int id);

    /**
     * It is used to restore the project
     * @param id project id
     * @return boolean value
     */
    public boolean restoreProject(int id);

    /**
     * It is used to update specific project details
     * @param id project id
     * @return boolean value
     */
    public boolean updateProject(int projectId, String[] projectDetails);

    /**
     * It is used to get all employee detais
     * @return employee details
     */
    public List<String> getAllEmployee();

   /**
    * It is used to assign project for employees
    * @param id project id
    * @param employeeIds employee id
    * @return boolean value
    */
    public boolean assignProject(int id, List<Integer> employeeIds);

   /**
    * It is used to check wheather the employee id is exist or not
    * @param empId employee id
    * @return boolean value
    */
    public boolean isEmpIdExist(int employeeId);

    /**
     * It is used to get assigned project details
     * @param id project id
     * @return project detail
     */
    public List<String> getAssignedProject(int id);

    /**
     * It is used to get project details
     * @return project
     */
    public List<Project> getAllProjectDetails();

   /**
    * It is used to get specific project details
    * @param id project id
    * @return project details
    */
    public Project getSpecificProjectDetails(int id);
}