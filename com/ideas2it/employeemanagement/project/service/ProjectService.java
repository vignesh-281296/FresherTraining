package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;

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
     * It is used to check wheather the project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectExist(int id);

    /**
     * It is used to get individual project details
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
     * It is used to delete project
     * @param id project id
     * @return boolean value
     */
    public boolean deleteProject(int id);

    /**
     * It is used to get deleted project details
     * @return deleted project details
     */
    public List<String> getDeletedProjectDetails();

    /**
     * It is used to check wheather the project is delete
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectDeleted(int id);

    /**
     * It is used to restore project
     * @param id project id
     * @return booleanvalue
     */
    public boolean restoreProject(int id);

    /**
     * It is used to update project details
     * @param projectId 
     * @param projectDetails
     * @return boolean value
     */
    public boolean updateProject(int projectId, String[] projectDetails);

    /**
     * It is used to get specific project details
     * @param id project id
     * @return project details
     */
    public Project getSpecificProjectDetails(int id);

    /**
     * It is used to get all employee details
     * @return employee details
     */
    public List<String> getAllEmployeeDetails();

    /**
     * It is used to check wheather the employee id is exist or not
     * @param employeeId 
     * @return boolean value
     */
    public boolean isEmployeeExist(int employeeId);

    /**
     * It is used to assign project to employee
     * @param id project id
     * @param employeeIds multiple employee id
     */
    public boolean assignProject(int id, List<Integer> employeeIds);

    /**
     * It is used to get assigned project details
     * @param id project id
     * @return project details
     */
    public List<String> getAssignedProject(int id);

    /**
     * It is used to unassign project
     * @param id project id
     * @param employeeId
     * @return boolean value
     */
    public boolean unAssignProject(int id, int employeeId);
}